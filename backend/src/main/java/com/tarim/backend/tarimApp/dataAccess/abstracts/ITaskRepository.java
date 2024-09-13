package com.tarim.backend.tarimApp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarim.backend.tarimApp.core.entities.Task;

public interface ITaskRepository  extends JpaRepository<Task,Integer>{
    List<Task> findByParcelId(int parcelId);
    
}
