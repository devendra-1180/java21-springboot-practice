package com.learning.tdd;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.tdd.collection.TaskController;
import com.learning.tdd.model.Task;
import com.learning.tdd.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    /** @WebMvcTest(TaskController.class) → loads only your web layer (controller + Jackson mapper, not the full Spring context).
    @MockBean TaskService → injects a Mockito mock instead of the real service bean.

    MockMvc → simulates an HTTP request (like curl or Postman).

    when(...).thenReturn(...) → defines what your mocked service should return.

    jsonPath(...) → verifies JSON fields from the response.***/

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTask() throws Exception {
        //arrange
        Task task = new Task("Controller task", "To do");
        when(taskService.saveTask(any(Task.class))).thenReturn(task);

        //act
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(task.getTitle()));
    }

    @Test
    void testCreateTask_WithInvalidInput() throws Exception {
        //arrange
        Task task = new Task("", "To do");
        when(taskService.saveTask(any(Task.class))).thenReturn(task);

        //act
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task))).andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllTasks() throws Exception {
        //arrange
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "To do"),
                new Task(2L, "Task 2", "In progress"),
                new Task(3L, "Task 3", "Done")
        );
        when(taskService.getAllTasks()).thenReturn(tasks);

        //act
        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"))
                .andExpect(jsonPath("$[1].status").value("In progress"));
    }

}
