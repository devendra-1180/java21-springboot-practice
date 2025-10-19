package com.learning.tdd.service;

import com.learning.tdd.model.Task;
import com.learning.tdd.repository.TaskRepository;

import java.util.List;

public record TaskService(TaskRepository taskRepository) {

    public Task updateTaskStatus(Long Id, String taskStatus) {
        final Task taskToUpdate = getTaskById(Id);
        taskToUpdate.setStatus(taskStatus);
        return taskRepository.save(taskToUpdate);
    }

    public Task getTaskById(Long Id) {
        return taskRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }
}

