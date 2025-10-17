package com.learning.records.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Version
    private Integer version;
    String title;
    String status;


    public  Task(String title, String status) {
        this.title = title;
        this.status = status;
    }

}