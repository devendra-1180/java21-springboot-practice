package com.learning.tdd.service;

import com.learning.tdd.model.Task;
import com.learning.tdd.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    void testUpdateTask(){
        //arrange
        Task task = new Task(1L, "First Task", "To do");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        //act
        Task updateTask = taskService.updateTaskStatus(1L, "In Progress");

        //assert
        assertNotNull(updateTask);
        assertEquals("In Progress", updateTask.getStatus());

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));

    }
}
