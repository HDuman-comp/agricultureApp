package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tarim.backend.tarimApp.business.concretes.abstracts.ITaskService;
import com.tarim.backend.tarimApp.business.dto.request.Task.CreateTaskRequest;
import com.tarim.backend.tarimApp.business.dto.request.Task.UpdateTaskRequest;
import com.tarim.backend.tarimApp.business.dto.response.task.GetAllTaskResponse;
import com.tarim.backend.tarimApp.business.dto.response.task.GetTodayProcessesResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private ITaskService taskService;

    @GetMapping("/getAll")    
    public List<GetAllTaskResponse> getAll(){
        return this.taskService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody CreateTaskRequest createTaskRequest){
        this.taskService.add(createTaskRequest);
    }

    @PutMapping("/update")
    public void uupdate (UpdateTaskRequest updateTaskRequest){
        this.taskService.update(updateTaskRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(int id){
        this.taskService.delete(id);
    }

    @GetMapping("/getProcessesInParcelToday/{userId}")
    public List<GetTodayProcessesResponse> getTodayProcessesResponses (int userId){
        return this.taskService.getTodayProcessesForUser(userId);
    }


    
}
