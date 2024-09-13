package com.tarim.backend.tarimApp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.ITaskDayService;
import com.tarim.backend.tarimApp.business.dto.request.TaskDetail.CreateTaskDetailRequest;
import com.tarim.backend.tarimApp.business.dto.request.TaskDetail.UpdateTaskDetailRequest;
import com.tarim.backend.tarimApp.business.dto.response.taskDetail.GetAllTaskDetailResponse;
import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.TaskDay;
import com.tarim.backend.tarimApp.dataAccess.abstracts.ITaskDayRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TaskDayService  implements ITaskDayService{

    
    private ITaskDayRepository taskDetailRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllTaskDetailResponse> getAll() {
        List<TaskDay> taskDetails = this.taskDetailRepository.findAll();
        List<GetAllTaskDetailResponse> gettAllTaskDetailResponses = taskDetails.stream()
        .map(taskDetail -> this.modelMapperService.forResponse()
        .map(taskDetail, GetAllTaskDetailResponse.class)).collect(Collectors.toList());
        return gettAllTaskDetailResponses;
    }

    @Override
    public void add (CreateTaskDetailRequest createTaskDetailRequest){
        TaskDay taskDetail=this.modelMapperService.forRequest().map(createTaskDetailRequest, TaskDay.class);
        this.taskDetailRepository.save(taskDetail);
    }

    @Override
    public void update(UpdateTaskDetailRequest updateTaskDetailRequest){
        TaskDay taskDetail=this.modelMapperService.forRequest().map(updateTaskDetailRequest, TaskDay.class);
        this.taskDetailRepository.save(taskDetail);
    }

    @Override
    public void delete(int id){
        this.taskDetailRepository.deleteById(id);
    }

    @Override
    public void updateTaskDayCompleted(int taskDayId) {

        Optional<TaskDay> optionalTaskDay = this.taskDetailRepository.findById(taskDayId);

        if(optionalTaskDay.isPresent()){
            TaskDay taskDay = optionalTaskDay.get();

            taskDay.setCompleted(!taskDay.isCompleted());
            this.taskDetailRepository.save(taskDay);
        }
        else{
            throw new BusinessException("TaskDay not found with id: " + taskDayId);
        }

    }

    


    
}
