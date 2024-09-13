package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.TaskDetail.CreateTaskDetailRequest;
import com.tarim.backend.tarimApp.business.dto.request.TaskDetail.UpdateTaskDetailRequest;
import com.tarim.backend.tarimApp.business.dto.response.taskDetail.GetAllTaskDetailResponse;

public interface ITaskDayService {
    
    List<GetAllTaskDetailResponse> getAll();
    void add (CreateTaskDetailRequest createTaskDetailRequest);
    void update(UpdateTaskDetailRequest updateTaskDetailRequest);
    void delete(int id);

    void updateTaskDayCompleted (int taskDayId);
}
