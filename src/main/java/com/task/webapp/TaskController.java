package com.task.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller class to manage Task operations using CRUD and REST-API methodology
@RestController
public class TaskController {

    @Autowired
    TaskRepository rep; //Fetches data from the repository using the variable 'rep'

    @PostMapping("/postTask")
    // Endpoint to create a new task. It expects a Task object in the request body.
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        try {
            // Saves the task using the repository. If successful, returns "OK" response.
            rep.saveTask(task);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            // If an exception occurs, returns an internal server error with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in saving task");
        }
    }

    @GetMapping("/getAllTasks")
    // Endpoint to retrieve all tasks.
    // It doesn't require any request parameters.
    public List<Task> getAllTasks() {
        // Fetches all tasks from the repository and returns them.
        return rep.getTasks();
    }

    @GetMapping("/getSingleTask/{id}")
    // Endpoint to retrieve a single task by its ID.
    // The task ID is provided as a path variable.
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        // Fetches the task by its ID from the repository.
        Task task = rep.getTaskById(id);
        if (task != null) {
            // If the task is found, returns it with an OK response.
            return ResponseEntity.ok(task);
        } else {
            // If the task is not found, returns a not found (404) response.
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateTask/{id}")
    // Endpoint to update a task by its ID.
    // It expects the task ID as a path variable and the updated task information in the request body.
    public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        // Fetches the existing task by its ID from the repository.
        Task existingTask = rep.getTaskById(id);

        if (existingTask != null) {
            // Updates the task's name and description with the new values.
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            // Saves the updated task.
            rep.updateTask(existingTask);
            // Returns an OK response upon successful update.
            return ResponseEntity.ok("OK");
        } else {
            // If the task is not found, returns a not found (404) response.
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    // Endpoint to delete a task by its ID.
    // The task ID is provided as a path variable.
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        // Fetches the task by its ID from the repository.
        Task existingTask = rep.getTaskById(id);

        if (existingTask != null) {
            // Deletes the task if it exists.
            rep.deleteTaskById(id);
            // Returns an OK response upon successful deletion.
            return ResponseEntity.ok("OK");
        } else {
            // If the task is not found, returns a not found (404) response.
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/completeTask/{id}")
    // Endpoint to mark a task as completed by its ID.
    // The task ID is provided as a path variable.
    public ResponseEntity<String> completeTask(@PathVariable int id) {
        // Fetches the task by its ID from the repository.
        Task existingTask = rep.getTaskById(id);
        if (existingTask != null) {
            // Marks the task as completed.
            existingTask.setCompleted(true);
            // Saves the updated task.
            rep.updateTask(existingTask);
            // Returns an OK response with a custom message.
            return ResponseEntity.ok("Task completed");
        } else {
            // If the task is not found, returns a not found (404) response.
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getCompletedTasks")
    // Endpoint to retrieve all completed tasks.
    // It doesn't require any request parameters.
    public List<Task> getCompletedTasks() {
        // Fetches all completed tasks from the repository and returns them.
        return rep.getCompletedTasks();
    }

}

