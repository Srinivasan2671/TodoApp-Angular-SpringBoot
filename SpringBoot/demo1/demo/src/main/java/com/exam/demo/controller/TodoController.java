package com.exam.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.model.Todo;
import com.exam.demo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:4300")  // Angular dev server
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/user/{userId}")
    public List<Todo> getAllTodos(@PathVariable String userId) {
        return todoService.readAllService(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.readByIdService(id);
        return todo.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        System.out.println("Received Todo: " + todo);
        
        if (todo.getUserId() == null || todo.getUserId().trim().isEmpty()) {
            System.out.println("Error: userId is NULL or empty!");
            return ResponseEntity.badRequest().body(Map.of("error", "User ID is required"));
        }

        try {
            Todo savedTodo = todoService.createService(todo);
            System.out.println("Todo saved: " + savedTodo);
            return ResponseEntity.ok(savedTodo);
        } catch (Exception e) {
            System.err.println("Error creating todo: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        try {
            Optional<Todo> existingTodo = todoService.readByIdService(id);
            
            if (!existingTodo.isPresent()) {
                return ResponseEntity.status(404).body(Map.of("error", "Todo not found with ID: " + id));
            }
            
            Todo updatedTodo = todoService.updateService(id, todo);
            return ResponseEntity.ok(updatedTodo);

        } catch (Exception e) {
            System.err.println("Error updating todo: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to update Todo: " + e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (!todoService.readByIdService(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        todoService.deleteService(id);
        return ResponseEntity.ok().build();
    }

}
