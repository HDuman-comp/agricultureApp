package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarim.backend.tarimApp.business.concretes.abstracts.ITaskDayService;
import com.tarim.backend.tarimApp.business.dto.request.TaskDetail.CreateTaskDetailRequest;
import com.tarim.backend.tarimApp.business.dto.request.TaskDetail.UpdateTaskDetailRequest;
import com.tarim.backend.tarimApp.business.dto.response.taskDetail.GetAllTaskDetailResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/taskDays")
public class TaskDayController {

    private ITaskDayService taskDetailService;

    @GetMapping("/getAll")
    public List<GetAllTaskDetailResponse> getAll(){
        return this.taskDetailService.getAll();
    }

    @PostMapping("/add")
    public void add (CreateTaskDetailRequest createTaskDetailRequest){
        this.taskDetailService.add(createTaskDetailRequest);
    }

    @PutMapping("/update")
        public void update(UpdateTaskDetailRequest updateTaskDetailRequest){
            this.taskDetailService.update(updateTaskDetailRequest);
        }
    
    @DeleteMapping("/delete/{id}")
    public void delete(int id){
        this.taskDetailService.delete(id);
    }

    @PutMapping("updateTaskDayIsCompleted/{taskDayId}")
    public void updateTaskDayCompleted ( int taskDayId){
        this.taskDetailService.updateTaskDayCompleted(taskDayId);
    }
}
