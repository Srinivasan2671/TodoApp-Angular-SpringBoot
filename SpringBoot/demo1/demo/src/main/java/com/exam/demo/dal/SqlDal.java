package com.exam.demo.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exam.demo.model.Todo;

@Repository
public class SqlDal implements TodoDAL{

    @Autowired
    private TodoRepository tRepository;

    @Override
    public List<Todo> readAllUserId(String userId){
        return tRepository.findByUserId(userId);
    }

    @Override
    public Optional<Todo> readById(Long id){
        return tRepository.findById(id);
    }

    @Override
    public Todo create(Todo todo){
        return tRepository.save(todo);
    }

    @Override
    public Todo update(Long id, Todo todo){
        Optional<Todo> existingTodoOpt = tRepository.findById(id);
        if(existingTodoOpt.isPresent()){
            Todo existingTodo = existingTodoOpt.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setDone(todo.isDone());
            return tRepository.save(existingTodo);
        }else{
            throw new RuntimeException("Todo not found with ID: "+ id);
        }
    }

    @Override
    public void delete(Long id){
        tRepository.deleteById(id);
    }

    
}
