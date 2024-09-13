package com.tarim.backend.tarimApp.dataAccess.abstracts;

import com.tarim.backend.tarimApp.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

   Optional<User> findByVerificationCode(String code);
}
