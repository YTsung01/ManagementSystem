package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.entity.EmpBook;
import com.example.model.entity.Employee;

public interface EmpBookDao {

//	1. 查詢所有員工(多筆)
	List<EmpBook> findAllEmpBooks();
	
//	2. 根據員工名稱查找員工(登入用-單筆)
	Optional<Employee> findEmployeeByEmployeeName(String empname);
	
//	3. 根據員工ID查找員工(單筆)
	Optional<EmpBook> findEmpBookByEmpId(Integer empId);
	
//	4. 根據部門ID查找所有員工(多筆)
	List<EmpBook> findEmpBooksByEmpDeptNo(Integer empDeptno);

//	5. 根據部門ID及levelId查找主管(單筆)
	Optional<EmpBook> findEmpBooksByEmpDeptNoAndLevelId(Integer empDeptno,Integer levelId);
}
