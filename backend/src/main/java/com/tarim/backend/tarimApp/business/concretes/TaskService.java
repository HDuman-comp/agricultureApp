package com.tarim.backend.tarimApp.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.ITaskService;
import com.tarim.backend.tarimApp.business.dto.request.Task.CreateTaskRequest;
import com.tarim.backend.tarimApp.business.dto.request.Task.UpdateTaskRequest;
import com.tarim.backend.tarimApp.business.dto.response.task.GetAllTaskResponse;
import com.tarim.backend.tarimApp.business.dto.response.task.GetTodayProcessesResponse;
import com.tarim.backend.tarimApp.business.dto.response.task.VariableGetTodayProcesses;
import com.tarim.backend.tarimApp.business.dto.response.task.VariableGetTodayProcessesDay;
import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.Parcel;
import com.tarim.backend.tarimApp.core.entities.Task;
import com.tarim.backend.tarimApp.core.entities.User;
import com.tarim.backend.tarimApp.dataAccess.abstracts.ITaskRepository;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IUserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TaskService implements ITaskService {

    private ITaskRepository taskRepository;
    private ModelMapperService modelMapperService;
    private IUserRepository userRepository;

    @Override
    public List<GetAllTaskResponse> getAll() {
        List<Task> tasks = this.taskRepository.findAll();
        List<GetAllTaskResponse> getAllTaskResponses = tasks.stream()
        .map(task -> this.modelMapperService.forResponse()
        .map(task, GetAllTaskResponse.class)).collect(Collectors.toList());

        return getAllTaskResponses;

    }

    @Override
    public void add (CreateTaskRequest createTaskRequest){
        Task task = this.modelMapperService.forRequest().map(createTaskRequest, Task.class);
        this.taskRepository.save(task);

    }

    @Override
    public void update(UpdateTaskRequest updateTaskRequest){
        Task task = this.modelMapperService.forRequest().map(updateTaskRequest, Task.class);
        this.taskRepository.save(task);
    }

    @Override
    public void delete(int id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public List<GetTodayProcessesResponse> getTodayProcessesForUser(int userId) {

        LocalDate today = LocalDate.now();

        

        User user = this.userRepository.findById(userId)
            .orElseThrow(() -> new BusinessException("User not found with id: " + userId));
        
        List<Parcel> parcels = user.getParcels().stream()
            .filter(parcel -> parcel.isStatus() == true)
            .collect(Collectors.toList());

        Map<String, List<Task>> tasksByParcel = parcels.stream()
            .flatMap(parcel -> this.taskRepository.findByParcelId(parcel.getId()).stream())
            .filter(task -> task.getDays().stream()
                .anyMatch(taskDay -> {
                    LocalDate startDate = task.getParcel().getProduct().getStartDate();
                    return startDate.plusDays(taskDay.getDay() - 1).equals(today);
                }))
            .collect(Collectors.groupingBy(task -> task.getParcel().getParcelNumber()));

        List<GetTodayProcessesResponse> taskDtos = tasksByParcel.entrySet().stream()
            .map(entry -> {
                String parcelNumber = entry.getKey();
                
                
                List<VariableGetTodayProcesses> tasks = entry.getValue().stream()
                    .map(task -> {
                        VariableGetTodayProcesses taskDTO = this.modelMapperService.forResponse().map(task, VariableGetTodayProcesses.class);
                        //taskDTO.setProcessName(task.getProductProcessName().getName());
                        //int currentDay = (int) ChronoUnit.DAYS.between(task.getParcel().getProduct().getStartDate(), today);
                        LocalDate startDate = task.getParcel().getProduct().getStartDate();
                        List<VariableGetTodayProcessesDay> todayTaskDays = task.getDays().stream()
                            .filter(taskDay -> startDate.plusDays(taskDay.getDay() - 1).equals(today))
                            .map(taskDay -> this.modelMapperService.forResponse().map(taskDay, VariableGetTodayProcessesDay.class))
                            .collect(Collectors.toList());
                        taskDTO.setTaskDays(todayTaskDays);
                        return taskDTO;
                    })
                    .collect(Collectors.toList());

                 LocalDate startDate = entry.getValue().get(0).getParcel().getProduct().getStartDate();
                 int currentDay = (int) ChronoUnit.DAYS.between(startDate, today);   
                return new GetTodayProcessesResponse(today,currentDay,parcelNumber, tasks);
            })
            .collect(Collectors.toList());

        return taskDtos;
    }
    
}
