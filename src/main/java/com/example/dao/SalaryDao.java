package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.entity.Salary;

public interface SalaryDao {

	List<Salary> findAllSalarys();
	
	Optional<Salary> findSalaryByEmpId(Integer empId);
	
	Optional<Salary> findSalaryByEmpIdAndSalaryDate(Integer empId, String salaryDate);
	
	Optional<Salary> findSalaryBySalaryId(Integer salaryId);
	
	int addSalary(Salary salary);
}
