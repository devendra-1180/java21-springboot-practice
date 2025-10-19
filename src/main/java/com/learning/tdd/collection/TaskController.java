package com.learning.tdd.collection;

import com.learning.tdd.model.Task;
import com.learning.tdd.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        //constructor injection is faster than field injection, reason not depend on Reflection API
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.saveTask(task));
    }
}
