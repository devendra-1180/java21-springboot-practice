package com.learning.records.repositorytest;

import com.learning.records.model.Task;
import com.learning.records.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void saveTask(){
        //arrange
        Task task = new Task("First Task", "Todo");

        //act
        Task savedTask= taskRepository.save(task);

        //assert
        assertNotNull(savedTask);
        assertEquals("First Task", savedTask.getTitle());
    }

    @Test
    void getAllTasks(){
        Task task1 = new Task("Task 1", "on going");
        assertNotNull(taskRepository.save(task1));

        Task task2 = new Task("Task 2", "on going");
        assertNotNull(taskRepository.save(task2));


        //act
        List<Task> allTasks = taskRepository.findAll();

        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());
    }

    @Test
    void deleteTask(){
        //arrange
        Task task = new Task("Second Task","Done" );

        //act
        Task savedTask= taskRepository.save(task);
        assertNotNull(savedTask);

        taskRepository.delete(task);

        Optional<Task> deletedTask =  taskRepository.findById(savedTask.getId());

        //assert
        assertFalse(deletedTask.isPresent());
    }
}
