package com.tarim.backend.tarimApp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tarim.backend.tarimApp.business.dto.request.User.EditUserInfoRequest;
import com.tarim.backend.tarimApp.business.dto.response.user.GetUserInfoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IUserService;
import com.tarim.backend.tarimApp.business.dto.request.User.AddProductsToParcelRequest;
import com.tarim.backend.tarimApp.business.dto.response.user.GetAllParcelForUserResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.GetAllUserResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.ParcelsOwnedByUserResponse;
import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.Parcel;
import com.tarim.backend.tarimApp.core.entities.Product;
import com.tarim.backend.tarimApp.core.entities.Task;
import com.tarim.backend.tarimApp.core.entities.TaskDay;
import com.tarim.backend.tarimApp.core.entities.User;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IParcelRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.ITaskDayRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.ITaskRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private ModelMapperService modelMapperService;
    private IProductRepository productRepository;
    private IParcelRepository parcelRepository;
    private ITaskRepository taskRepository;
    private ITaskDayRepository taskDayRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GetAllUserResponse> getAll() {

        List<User> users = this.userRepository.findAll();
        List<GetAllUserResponse> getAllUserResponses = users.stream()
        .map(user -> this.modelMapperService.forResponse()
        .map(user, GetAllUserResponse.class)).collect(Collectors.toList());

        return getAllUserResponses;
        

    }


    @Override
    public ParcelsOwnedByUserResponse getParcelsOwnedByUser(int userId) {

        Optional<User> user = this.userRepository.findById(userId);

        ParcelsOwnedByUserResponse parcelsOwnedByUserResponse = this.modelMapperService.forResponse()
        .map(user, ParcelsOwnedByUserResponse.class);

        

        return parcelsOwnedByUserResponse;
       
    }

    @Override
    public void EditUserInfo(User user, EditUserInfoRequest request) {
        modelMapper.getConfiguration().setPropertyCondition(context ->
                context.getSource() != null && !"".equals(context.getSource()));

        // Şifreyi manuel olarak encode edip, kullanıcı nesnesine ayarlıyoruz
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Şifreyi tekrar güncellemeden diğer alanları modelMapper ile güncelle
        request.setPassword(null);  // Şifreyi null yapıyoruz, böylece map edilmez
        modelMapper.map(request, user);

        userRepository.save(user);
    }


    @Override
    public void addProductsToParcel(AddProductsToParcelRequest request) {

        Optional<User> optionalUser = this.userRepository.findById(request.getId());

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            List <Parcel> parcels = user.getParcels();

            Optional<Parcel> optionalParcel = parcels.stream()
                .filter(parcel -> parcel.getProduct() == null)
                .findFirst();

            if(optionalParcel.isPresent()){
                Parcel parcel = optionalParcel.get();
                Product product = this.productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new BusinessException("Product not found with id :  " + request.getProductId()));

                parcel.setProduct(product);
                
                parcel.setStatus(true);
                this.parcelRepository.save(parcel);

                List<Task> tasks = product.getProductProcesses().stream()
                    .map(process ->{
                        Task task = new Task();
                        task.setParcel(parcel);
                        task.setProductProcessName(process.getProductProcessName());
                        this.taskRepository.save(task);

                        List<TaskDay> taskDays = process.getDays().stream()
                            .map(day ->{
                                TaskDay taskDay = new TaskDay();
                                taskDay.setTask(task);
                                taskDay.setDay(day);
                                taskDay.setCompleted(false);
                                return taskDay;
                            }).collect(Collectors.toList());
                        this.taskDayRepository.saveAll(taskDays);
                        
                        return task;
                        
                    }) .collect(Collectors.toList());

                this.taskRepository.saveAll(tasks);    

                
            }
            else{
                throw new BusinessException("No parcel found with product null");
            }    

        }

        else{
            throw new BusinessException("User not found with id : " + request.getId());
        }

       
    }

    @Override
    public List<GetAllParcelForUserResponse> getAllParcelForUser (int userId) {

        System.out.println("Fetching user with ID: " + userId); 

        Optional<User> optionalUser = this.userRepository.findById(userId);



        if(optionalUser.isPresent()){
            System.out.println("User Name" + optionalUser.get().getFirstname());

            User user = optionalUser.get();

            List<GetAllParcelForUserResponse> parcels = user.getParcels().stream()
                .map(parcel -> this.modelMapperService.forResponse()
                .map(parcel, GetAllParcelForUserResponse.class)).collect(Collectors.toList());
            
            return parcels;    

        }

        else{
            throw new BusinessException("User not found with id: "+ userId);
        }


    }



    @Override
    public GetUserInfoResponse getUserInfo(int userId) {

        Optional<User> user = this.userRepository.findById(userId);

        return this.modelMapperService.forResponse().map(user, GetUserInfoResponse.class);


    }



}
