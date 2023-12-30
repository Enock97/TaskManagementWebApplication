package com.task.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate db;

    public void saveTask(Task task) { //Saves a task into the DB
        String sql = "INSERT INTO Task (name, description, is_completed) VALUES (?, ?, ?)";
        db.update(sql, task.getName(), task.getDescription(), false); // false sets is_completed to FALSE
    }

    public List<Task> getTasks() { //Retrieves all tasks from the DB that aren't completed
        String sql = "SELECT * FROM Task WHERE is_completed = FALSE";
        List<Task> tasks = db.query(sql, new BeanPropertyRowMapper<>(Task.class));
        System.out.println("Retrieved tasks: " + tasks); // Log the retrieved tasks
        return tasks;
    }

    public Task getTaskById(int id) { //Retrieves task from the DB by its ID
        String sql = "SELECT * FROM Task WHERE id = ?";
        List<Task> tasks = db.query(sql, new BeanPropertyRowMapper<>(Task.class), id);
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    public void updateTask(Task task) { //Updates a task in the DB
        String sql = "UPDATE Task SET name = ?, description = ?, is_completed = ? WHERE id = ?";
        db.update(sql, task.getName(), task.getDescription(), task.isCompleted(), task.getId());
    }

    public void deleteTaskById(int id) { //Deletes a task from the DB by its ID
        String sql = "DELETE FROM Task WHERE id = ?";
        db.update(sql, id);
    }
    /*
    public void deleteAllTasks () {
        String sql = "DELETE FROM Task";
        db.update(sql);
    }*/

    public List<Task> getCompletedTasks() { //Retrieves all tasks from the DB that are completed
        String sql = "SELECT * FROM Task WHERE is_completed = TRUE";
        return db.query(sql,new BeanPropertyRowMapper(Task.class));
    }
}
