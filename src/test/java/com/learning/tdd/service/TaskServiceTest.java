package com.learning.tdd.service;

import com.learning.tdd.model.Task;
import com.learning.tdd.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
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
    void testGetTaskById() {
        //arrange
        Task task =  new Task(1L, "New Task", "In  Progress");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        //act & assert
        assertEquals(task, taskService.getTaskById(1L));

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllTask(){
        //arrange
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "To do"),
                new Task(2L, "Task 2", "In progress"),
                new Task(3L, "Task 3", "Done")
        );
        when(taskRepository.findAll()).thenReturn(tasks);

        //act
        List<Task> resultTask = taskService.getAllTasks();

        //assert
        assertNotNull(resultTask);
        assertEquals(tasks.size(), resultTask.size());
        assertEquals(tasks.getFirst().getId(), resultTask.getFirst().getId());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById_TaskNotFound(){
        //arrange
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act & assert
        assertThrowsExactly(IllegalArgumentException.class, () -> taskService.getTaskById(1L));
        verify(taskRepository, times(1)).findById(1L);
    }


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
