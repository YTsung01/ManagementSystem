package com.example.dao;

import java.util.List;
import java.util.Optional;
import com.example.entity.EmpBook;

public interface EmpBookDao {

	List<EmpBook> findAllEmpBooks();
	
	Optional<EmpBook> findEmpBookByEmpId(Integer empId);

	List<EmpBook> findEmpBooksByEmpDeptNo(Integer empDeptno);
	
	Optional<EmpBook> findEmpBookByEmpDeptNoAndLevelId(Integer empDeptno);
	
	//新增加班剩餘時數時數
	int addOverTimeLeftHourByEmpId(Integer empId, Integer overTimeLeftHour);
	
	
}
