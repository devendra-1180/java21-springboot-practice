package com.learning.tdd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String status;
    @Version
    private Integer version;

    public  Task(String title, String status) {
        this.title = title;
        this.status = status;
    }
    public Task(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }


}