package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.example.model.entity.oldEmployee;



public interface oldEmployeeDao {
//	員工-employee:
//	1. 查詢所有員工(多筆)
	List<oldEmployee> findAllEmployees();
	
	
//	2. 新增員工
	void addEmployee(oldEmployee employee);
	
	
//	3. 修改密碼
	Boolean updatePassword(Integer empId, String newPassword);
	
//	4. 根據員工名稱查找員工(登入用-單筆)
	Optional<oldEmployee> findEmployeeByEmployeeName(String empname);
	
//	5. 根據員工ID查找員工(單筆)
	Optional<oldEmployee> findEmployeeByEmployeeId(Integer empId);
	
//	6. 根據部門ID查找主管(單筆)
	Optional<oldEmployee> findSupervisorByempDeptno(Integer empDeptno);
	

}
