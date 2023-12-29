package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.example.model.entity.Employee;



public interface EmployeeDao {
//	員工-employee:
//	1. 查詢所有員工(多筆)
	List<Employee> findAllEmployees();
	
	
//	2. 新增員工
	void addEmployee(Employee employee);
	
	
//	3. 修改密碼
	Boolean updatePassword(Integer empId, String newPassword);
	
//	4. 根據員工名稱查找員工(登入用-單筆)
	Optional<Employee> findEmployeeByEmployeeName(String empname);
	
//	5. 根據員工ID查找員工(單筆)
	Optional<Employee> findEmployeeByEmployeeId(Integer empId);
	
//	6. 根據部門ID查找主管(單筆)
	Optional<Employee> findSupervisorByempDeptno(Integer empDeptno);
	

}
