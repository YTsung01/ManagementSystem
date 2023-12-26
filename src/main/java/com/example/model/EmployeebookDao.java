package com.example.model;

import java.util.List;


public interface EmployeebookDao {
	void create(Employeebook employeebook);
	List<Employeebook> readAll();

}
