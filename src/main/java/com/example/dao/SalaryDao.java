package com.example.dao;

import java.util.List;
import java.util.Optional;
import com.example.entity.Salary;

public interface SalaryDao {

	//1. 查詢所有員工的薪水資料(多筆)
	List<Salary> findAllSalarys();
	
	//2. 依據EmpId查詢此員工的薪水資料(單筆)
	Optional<Salary> findSalaryByEmpId(Integer empId);
	
	//3. 依據EmpId和發薪月份查找員工的薪水資料(單筆)
	Optional<Salary> findSalaryByEmpIdAndSalaryDate(Integer empId, String salaryDate);
	
	//4. 依據薪水單號查找員工的薪水資料(單筆)
	Optional<Salary> findSalaryBySalaryId(Integer salaryId);
	
	//5. 新增薪水資料
	int addSalary(Salary salary);
}
