package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.Task.CreateTaskRequest;
import com.tarim.backend.tarimApp.business.dto.request.Task.UpdateTaskRequest;
import com.tarim.backend.tarimApp.business.dto.response.task.GetAllTaskResponse;
import com.tarim.backend.tarimApp.business.dto.response.task.GetTodayProcessesResponse;


public interface ITaskService {
    List<GetAllTaskResponse> getAll();
    void add(CreateTaskRequest createTaskRequest);
    void update(UpdateTaskRequest updateTaskRequest);
    void delete(int id);

    List<GetTodayProcessesResponse> getTodayProcessesForUser (int userId);
}
