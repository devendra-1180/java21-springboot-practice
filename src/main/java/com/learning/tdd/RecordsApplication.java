package com.learning.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.tdd.model.Employee;

@SpringBootApplication
public class RecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordsApplication.class, args);
	}
	
	
	void createRecord() {
		Employee emp = new Employee(1, "Dev" , 34);
		
	}
	

}
