package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.example.model.entity.Employee;



public interface EmployeeDao {
//	使用者-User:
//	1. 查詢所有使用者(多筆)
	List<Employee> findAllEmployees();
	
	
//	2. 新增使用者
	void addEmployee(Employee employee);
	
	
//	3. 修改密碼
	Boolean updatePassword(Integer empId, String newPassword);
	
//	4. 根據使用者名稱查找使用者(登入用-單筆)
	Optional<Employee> findEmployeeByEmployeeName(String empname);
	
//	5. 根據使用者ID查找使用者(單筆)
	Optional<Employee> findEmployeeByEmployeeId(Integer empId);
	

}
