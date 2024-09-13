package com.tarim.backend.tarimApp.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductService;
import com.tarim.backend.tarimApp.business.dto.request.Product.CreateProductRequest;
import com.tarim.backend.tarimApp.business.dto.request.Product.CreateProductWithProcessRequest;
import com.tarim.backend.tarimApp.business.dto.request.Product.CreateVariableProWithProcess;
import com.tarim.backend.tarimApp.business.dto.request.Product.UpdateProductRequest;
import com.tarim.backend.tarimApp.business.dto.response.product.FindActiveProductResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductPageResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductWithProcessResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetProductDetailResponse;
import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.business.exception.FileResponseException;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.business.minio.MinioService;
import com.tarim.backend.tarimApp.business.rules.ProductBusinessRules;
import com.tarim.backend.tarimApp.core.entities.Product;
import com.tarim.backend.tarimApp.core.entities.ProductProcess;
import com.tarim.backend.tarimApp.core.entities.ProductProcessName;
import com.tarim.backend.tarimApp.core.payload.FileResponse;
import com.tarim.backend.tarimApp.core.utilities.FileTypeUtils;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductProcessNameRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;




@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private IProductRepository productRepository;
    private ModelMapperService modelMapperService;
    private ProductBusinessRules productBusinessRules;
    private IProductProcessNameRepository productProcessNameRepository;
    private MinioService minioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    
    @Override
    public List<GetAllProductResponse> getAll() {

        List<Product> products =this.productRepository.findAll();
        List<GetAllProductResponse> gettAllProductResponses = products.stream()
        .map(product ->this.modelMapperService.forResponse()
        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());
        return gettAllProductResponses;

    }

    

    @Override
    public void add(CreateProductRequest createProductRequest) {
        this.productBusinessRules.checkIfParcelNumberExists(createProductRequest.getProductName());
        Product product = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
        this.productRepository.save(product);
    }

    @Transactional
    public void addProduct(CreateProductWithProcessRequest createProductWithProcessRequest) {

        Product product = this.modelMapperService.forRequest().map(createProductWithProcessRequest, Product.class);
        List<ProductProcess> productProcesses = new ArrayList<>(); // Yeni bir liste oluşturuyoruz
        
        for (CreateVariableProWithProcess processRequest : createProductWithProcessRequest.getProductProcesses()) {
            ProductProcessName productProcessName = productProcessNameRepository.findById(processRequest.getProductProcessNameId())
                    .orElseThrow(() -> new RuntimeException("ProductProcessName bulunamadı"));
           
            ProductProcess productProcess = new ProductProcess();
            productProcess.setProduct(product);
            productProcess.setProductProcessName(productProcessName);
            productProcess.setDays(processRequest.getDays());
            productProcesses.add(productProcess);
        }

        product.setProductProcesses(productProcesses);    
        //this.productRepository.save(product);
        this.addProductIsActive(product);
    
    }
    




    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        Product existingProduct = productRepository.findById(updateProductRequest.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        modelMapperService.update(updateProductRequest, existingProduct);

        productRepository.save(existingProduct);
    }

    @Override
    public void delete (int id){
        this.productRepository.deleteById(id);
    }


    @Override
    public List<GetAllProductPageResponse> getAllProductPage() {

        List<Product> products = this.productRepository.findAll();
        List<GetAllProductPageResponse> getAllProductPageResponses = products.stream()
        .map(product-> this.modelMapperService.forResponse()
        .map(product, GetAllProductPageResponse.class)).collect(Collectors.toList());

        return getAllProductPageResponses;

    }


    @Override
    public List<GetAllProductWithProcessResponse> getAllProductWithProcess() {
        List<Product> products = this.productRepository.findAll();
        List<GetAllProductWithProcessResponse> getAllProductWithProcessResponses = products.stream()
        .map(product -> this.modelMapperService.forResponse()
        .map(product, GetAllProductWithProcessResponse.class)).collect(Collectors.toList());

        
        return getAllProductWithProcessResponses;

    }

    @Override
    public GetProductDetailResponse getProductDetail(int id) {

        Optional<Product> product = this.productRepository.findById(id);

        if(product.isPresent()){

            GetProductDetailResponse getProductDetailResponse = this.modelMapperService.forResponse()
            .map(product, GetProductDetailResponse.class);
    
            getProductDetailResponse.setEndDate(getProductDetailResponse.getStartDate().plusDays(getProductDetailResponse.getProductGrowingTime()));
    
            getProductDetailResponse.getProductProcesses().forEach(process -> {
                process.setTotalDay(process.getDays().size());
            });

            return getProductDetailResponse;

        }

        else{

            throw new BusinessException("There is no product with id number.");
        }

 
    }



    @Override
    public String addProductImage(MultipartFile multipartFile) {

        String fileType = FileTypeUtils.getFileType(multipartFile);

        if (fileType != null) {
            FileResponse fileResponse = minioService.putObject(multipartFile, "product", fileType);
            return fileResponse.getUrl();
        }

        throw new FileResponseException("File cannot be upload.");

    }

    




    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateProductIsActive() {

        LocalDate today = LocalDate.now();

        List<Product> products = this.productRepository.findAll();
        products.forEach(product -> {

            LocalDate checkDate = product.getStartDate().plusDays(product.getGrowingTime());


            if(product.getDemandEndDate().isBefore(today) || product.getDemandStartDate().isAfter(today)){
                product.setActive(false);
                
            }
            else{
                product.setActive(true);
            }

            if(checkDate.isBefore(today)){
                product.getParcels().forEach(parcel -> {
                    parcel.setProduct(null);
                    parcel.setStatus(false);
                });
            }



        }
        
        );

        productRepository.saveAll(products);

        LOGGER.info("Ürün durumları güncellendi: {} ürün işlendi.", products.size());


    }




    @Override
    public void addProductIsActive(Product product) {

        LocalDate today = LocalDate.now();

        if(product.getDemandEndDate().isBefore(today) || product.getDemandStartDate().isAfter(today)){
            product.setActive(false);
            
        }
        else{
            product.setActive(true);
        }


        productRepository.save(product);

        LOGGER.info("{} durumu güncellendi.", product.getProductName());
    }




    @Override
    public List<FindActiveProductResponse> findActiveProduct() {

        List<Product> products = this.productRepository.findByIsActiveTrue();

        List<FindActiveProductResponse> findActiveProductResponses = products.stream()
        .map(activeProduct -> this.modelMapperService.forResponse()
        .map(activeProduct, FindActiveProductResponse.class)).collect(Collectors.toList());

        return findActiveProductResponses;

    }











    
}
