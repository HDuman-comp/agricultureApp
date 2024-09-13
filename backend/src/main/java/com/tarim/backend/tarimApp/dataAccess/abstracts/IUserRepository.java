package com.tarim.backend.tarimApp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarim.backend.tarimApp.core.entities.User;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
}
