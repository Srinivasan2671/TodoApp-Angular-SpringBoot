package com.exam.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.demo.dal.TodoDAL;
import com.exam.demo.model.Todo;

@Service
public class TodoService {
    
    @Autowired
    private TodoDAL todoDAL;

    public List<Todo> readAllService(String userId) {
        return todoDAL.readAllUserId(userId);
    }

    public Optional<Todo> readByIdService(Long id) {
        return todoDAL.readById(id);
    }

    public Todo createService(Todo todo) {
        return todoDAL.create(todo);
    }

    public Todo updateService(Long id, Todo todo){
        return todoDAL.update(id, todo);
    }

    public void deleteService(Long id) {
        todoDAL.delete(id);
    }
}
