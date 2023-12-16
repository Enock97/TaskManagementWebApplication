package com.task.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskRepository rep;

    @PostMapping("/save")
    public void saveTask(Task task){
        rep.saveTask(task);
    }
    @GetMapping("/getAll")
    public List<Task> getAll(){
        return rep.getTasks();
    }

    @GetMapping("getOneTask/{id}")
    public Task getTaskById(@PathVariable int id) {
        return rep.getTaskById(id);
    }

    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable int id, Task updatedTask) {
        Task existingTask = rep.getTaskById(id);

        if (existingTask != null) {
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            rep.updateTask(existingTask);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable int id) {
        Task existingTask = rep.getTaskById(id);

        if (existingTask != null) {
            rep.deleteTaskById(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        rep.deleteAllTasks();
    }

}
