package com.learning.tdd.service;

import com.learning.tdd.model.Task;
import com.learning.tdd.repository.TaskRepository;

public record TaskService(TaskRepository taskRepository) {

    public Task updateTaskStatus(Long Id, String taskStatus) {
        Task taskToUpdate = taskRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        taskToUpdate.setStatus(taskStatus);
        return taskRepository.save(taskToUpdate);
    }
}

