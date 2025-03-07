package com.exam.demo.dal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.demo.model.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(String userId);
}

