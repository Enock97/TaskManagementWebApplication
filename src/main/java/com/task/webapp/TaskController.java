package com.task.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskRepository rep;

    // Create a new task
    @PostMapping("/postTask")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        try {
            rep.saveTask(task);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in saving task");
        }
    }

    // Retrieve all tasks
    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return rep.getTasks();
    }

    // Retrieve a task by ID
    @GetMapping("/getSingleTask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = rep.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a task by ID
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        Task existingTask = rep.getTaskById(id);

        if (existingTask != null) {
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            rep.updateTask(existingTask);
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a task by ID
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        Task existingTask = rep.getTaskById(id);

        if (existingTask != null) {
            rep.deleteTaskById(id);
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

