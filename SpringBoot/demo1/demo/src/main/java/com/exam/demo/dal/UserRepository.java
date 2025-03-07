package com.exam.demo.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserIdAndPassword(String userId, String password);
    Optional<User> findByUserId(String userId);
}
