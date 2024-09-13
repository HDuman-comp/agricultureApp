package com.tarim.backend.tarimApp.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IParcelService;
import com.tarim.backend.tarimApp.business.dto.request.Parcel.UpdateParcelDetailRequest;
import com.tarim.backend.tarimApp.business.dto.request.Parcel.UpdateParcelRequest;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelByDateResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetParcelDetailResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetTodayTaskForParcel;
import com.tarim.backend.tarimApp.business.dto.response.parcel.VariableTodayTask;
import com.tarim.backend.tarimApp.business.dto.response.parcel.VariableTodayTaskDay;
import com.tarim.backend.tarimApp.business.dto.response.parcel.VariableTodayTaskImageParcel;
import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.business.rules.ParcelBusinessRules;
import com.tarim.backend.tarimApp.core.entities.Parcel;
import com.tarim.backend.tarimApp.core.entities.ParcelImage;
import com.tarim.backend.tarimApp.core.entities.Task;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IParcelImageRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IParcelRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.ITaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;





@Service
@AllArgsConstructor
public class ParcelService implements IParcelService {

    private IParcelRepository parcelRepository;
    private ModelMapperService modelMapperService;
    private ParcelBusinessRules parcelBusinessRules;
    private IParcelImageRepository parcelImageRepository;
    private ITaskRepository taskRepository;
    
   

    

    @Override
    public List<GetAllParcelResponse> getAll() {

        List<Parcel> parcels = parcelRepository.findAll();
        List<GetAllParcelResponse> getAllParcelResponses = parcels.stream()
        .map(parcel ->this.modelMapperService.forResponse()
        .map(parcel, GetAllParcelResponse.class)).collect(Collectors.toList());
        return getAllParcelResponses;
    }

    @Override
    public void add(){
        //this.parcelBusinessRules.checkIfParcelNumberExists(createParcelRequest.getParcelNumber());
        
        Parcel parcel = new Parcel();
        Parcel savedParcel = this.parcelRepository.save(parcel);
        savedParcel.setParcelNumber("Parsel " + savedParcel.getId());
        this.parcelRepository.save(savedParcel);
    }

    @Override
    public void update (UpdateParcelRequest updateParcelRequest){
        Parcel parcel= this.modelMapperService.forRequest().map(updateParcelRequest, Parcel.class);
        parcelRepository.save(parcel);
        
    }

    @Override
    public void delete (int id ){
        parcelRepository.deleteById(id);
    }

    /* 
    @Override
    public List<GetAllParcelByDateResponse> GetAllParcelByDate() {

        List<Parcel> parcels = this.parcelRepository.findAll();
        List<GetAllParcelByDateResponse> getAllParcelByDateResponses = parcels.stream()
        .map(parcel -> {
            
            
            List<ProductStatus> productStatuses= parcel.getProduct().getProductStatuses();
            ProductStatus productStatus = null;
            for (ProductStatus status : productStatuses) {
                if (status.getProduct().getId() == parcel.getProduct().getId()) {
                    productStatus = status;
                break;
                }
            }

            return modelMapperService.parcelToParcelByDate(parcel,productStatus.getProductStage().getName());

        }).collect(Collectors.toList());

        return getAllParcelByDateResponses;
  
    }

    */

    @Override
    public List<GetAllParcelByDateResponse> GetAllParcelByDate(LocalDate date) {

        List<Parcel> parcels = this.parcelRepository.findByStatusTrue();
        List<GetAllParcelByDateResponse> getAllParcelByDateResponses = parcels.stream()
        .map(parcel ->{

            //String name = parcel.getProduct().getProductStatuses().get(0).getProductStage().getName();

            String name = parcel.getProduct().getProductStatuses().getProductStage().getName();

            int time = (int) ChronoUnit.DAYS.between(date,parcel.getProduct().getStartDate());

            List<ParcelImage> parcelImages = parcel.getParcelImages();
            parcelImages.sort(Comparator.comparing(ParcelImage :: getDate));

            ParcelImage filter = parcelImages.stream()
            .filter(image -> image.getDate().isEqual(date)).findFirst()
            .orElse(null);

            if(filter != null){
                
                return this.modelMapperService.parcelToParcelByDate2(parcel, name,filter.getImageUrl(), time, filter.getId());
            }
            else {
                ParcelImage temp= new ParcelImage();
                temp.setDate(date);
                temp.setParcel(parcel);
                this.parcelImageRepository.save(temp);
                
                return this.modelMapperService.parcelToParcelByDate2(parcel, name, temp.getImageUrl(), time, temp.getId());

            }
               
        }).collect(Collectors.toList());

        return getAllParcelByDateResponses;

        

        



        /* 
        List<Parcel> parcels = parcelRepository.findAll();
        List<GetAllParcelByDateResponse> getAllParcelByDateResponses = parcels.stream()
        .map(parcel-> this.modelMapperService.forResponse()
        .map(parcel, GetAllParcelByDateResponse.class)).collect(Collectors.toList());
        return getAllParcelByDateResponses;
        */
  
    }

    @Override
    public GetParcelDetailResponse getParcelDetail(int id) {
        Optional<Parcel> parcel = parcelRepository.findById(id);

        // Ensure the parcel exists before proceeding
        if (parcel.isEmpty()) {
            throw new EntityNotFoundException("Parcel not found");
        }

        GetParcelDetailResponse getParcelDetailResponse = modelMapperService.forResponse().map(parcel.get(), GetParcelDetailResponse.class);

        // Set current day if product exists
        if(parcel.get().getProduct() != null){
            long betweenDay = ChronoUnit.DAYS.between(parcel.get().getProduct().getStartDate(), LocalDate.now());
            getParcelDetailResponse.setCurrentDay(betweenDay);
        }

        // Map the userId from Parcel entity to GetParcelDetailResponse
        if (parcel.get().getUser() != null) {
            getParcelDetailResponse.setUserId(parcel.get().getUser().getId());
        }

        return getParcelDetailResponse;
    }


    @Transactional
    public void updateParcelDetailRequest(UpdateParcelDetailRequest updateParcelDetailRequest) {

        Optional<Parcel> optionalParcel = this.parcelRepository.findById(updateParcelDetailRequest.getId());

        if(optionalParcel.isPresent()){
            Parcel parcel = optionalParcel.get();

            if(!updateParcelDetailRequest.isStatus()){

                
                List<Task> tasks = parcel.getTasks();

                for(Task task : tasks){
                    this.taskRepository.deleteById(task.getId());
                }

                parcel = this.modelMapperService.forRequest().map(updateParcelDetailRequest, Parcel.class);
                                    
                this.parcelRepository.save(parcel);
            }
        }


    }

    @Override
    public GetTodayTaskForParcel getTodayTaskForParcels(int parcelId) {

        Optional<Parcel> optionalParcel = this.parcelRepository.findById(parcelId);
        LocalDate today = LocalDate.now();

        if(optionalParcel.isPresent()){
            Parcel parcel = optionalParcel.get();

            if(parcel.getProduct() != null){
                List<Task> tasks = parcel.getTasks().stream()
                .filter(task -> task.getDays().stream()
                    .anyMatch(taskDay -> task.getParcel().getProduct().getStartDate().plusDays(taskDay.getDay() - 1).equals(today)))
                .collect(Collectors.toList());
    
                List<VariableTodayTask> variableTodayTasks = tasks.stream()
                    .map(task -> {
                        List<VariableTodayTaskDay> variableTodayTaskDays = task.getDays().stream()
                            .filter(taskDay -> parcel.getProduct().getStartDate().plusDays(taskDay.getDay()-1).equals(today))
                            .map(taskDay -> this.modelMapperService.forResponse().map(taskDay, VariableTodayTaskDay.class))
                            .collect(Collectors.toList());
                            
                        VariableTodayTask variableTodayTask = this.modelMapperService.forResponse()
                            .map(task, VariableTodayTask.class);
                        variableTodayTask.setTaskDays(variableTodayTaskDays);
                        return variableTodayTask;
    
                    }).collect(Collectors.toList());
    
                GetTodayTaskForParcel getTodayTaskForParcel = this.modelMapperService.forResponse()
                    .map(parcel, GetTodayTaskForParcel.class);
                    
                getTodayTaskForParcel.setTasks(variableTodayTasks);
    
                Optional<ParcelImage> optionalParcelImage = parcel.getParcelImages().stream()
                    .filter(image -> image.getDate().equals(today))
                    .findFirst();
                if(optionalParcelImage.isPresent()){
                    ParcelImage parcelImage = optionalParcelImage.get();
                    VariableTodayTaskImageParcel imageUrl = this.modelMapperService.forResponse()
                        .map(parcelImage, VariableTodayTaskImageParcel.class);
                    getTodayTaskForParcel.setParcelImages(imageUrl);
                }  
                else{
                    getTodayTaskForParcel.setParcelImages(null);
                }
                
                LocalDate startDate = parcel.getProduct().getStartDate();
                int currentDay =(int) ChronoUnit.DAYS.between(startDate, today);
                 
                getTodayTaskForParcel.getProduct().setCurrentDay(currentDay);
                
                return getTodayTaskForParcel;
            }

            else{

                return this.modelMapperService.forResponse().map(parcel, GetTodayTaskForParcel.class);
            }
            





        }
        else{

            throw new BusinessException("Parcel not found with id: "+ parcelId);
        }

    }



}

    

