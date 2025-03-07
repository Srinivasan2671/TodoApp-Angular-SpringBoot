package com.exam.demo.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.exam.demo.model.Todo;

@Repository
public interface TodoDAL {
    public List<Todo> readAllUserId(String userId);

    public Optional<Todo> readById(Long id);

    public Todo create(Todo todo);

    public Todo update(Long id, Todo todo);

    public void delete(Long id);
}
