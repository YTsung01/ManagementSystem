package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.entity.EmpBook;
import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;

public interface OverTimeDao {

	//加班申請
	int addOverTime(OverTime overTime);
	
	//依據empId查詢使用者
	Optional<Employee>findEmpById(Integer empId);
	
	//依據empId查詢加班資料
	List<OverTime> findOverTimeByEmpId(Integer empId);
	
	//依據empId查詢已經審核過的加班資料
	List<OverTime> findCheckoutOverTimeHourByEmpId(Integer empId);
	
	//修改加班(注意!! 不能修改已經審核過的申請單)
	int updateOverTimeById(Integer empId, OverTime overTime);
	
	//取消加班
	int cancelOverTimeById(Integer overTimeFormId);

	// 依據DeptNo查詢加班資料(主管可看到所有人的)
	List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno);
}
