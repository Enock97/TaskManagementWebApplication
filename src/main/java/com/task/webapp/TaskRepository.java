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

    public void saveTask(Task task) {
        String sql = "INSERT INTO Task (name,description) VALUES(?,?)";
        db.update(sql,task.getName(),task.getDescription());
    }

    public List<Task> getTasks() {
        String sql = "SELECT * FROM Task";
        List<Task> allTasks = db.query(sql,new BeanPropertyRowMapper(Task.class));
        return allTasks;
    }

    public Task getTaskById(int id) {
        String sql = "SELECT * FROM Task WHERE id = ?";
        List<Task> tasks = db.query(sql, new BeanPropertyRowMapper<>(Task.class), id);
        return tasks.isEmpty() ? null : tasks.get(0);
    }

    public void updateTask(Task task) {
        String sql = "UPDATE Task SET name = ?, description = ? WHERE id = ?";
        db.update(sql, task.getName(), task.getDescription(), task.getId());
    }

    public void deleteTaskById(int id) {
        String sql = "DELETE FROM Task WHERE id = ?";
        db.update(sql, id);
    }

    public void deleteAllTasks () {
        String sql = "DELETE FROM Task";
        db.update(sql);
    }
}
