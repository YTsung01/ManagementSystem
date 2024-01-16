package com.example.model.dao;

import java.util.List;
import java.util.Optional;


import com.example.model.entity.OverTime;
import com.example.model.entity.Employee;


public interface OverTimeTypeDataDAO {
	
	
	//加班申請
	int addOverTime(OverTime overTime);
	
	
	//依據empId查詢使用者
	Optional<Employee>findEmpById(Integer empId);
	
	
	/*
	//依據empId查詢目前加班的累積時數
	List<OverTime> findOverTimeHourByEmpId(Integer empId);
	*/
	
	
	//修改加班(注意!! 不能修改已經審核過的申請單)
	int updateOverTimeById(Integer empId, OverTime overTime);
	
	//取消加班
	int cancelOverTimeById(Integer overTimeFormId);
	
	//加班查詢
	List<OverTime> findOverTimeByEmpId(Integer empId);
	
	// 查看所有加班紀錄
	//List<OverTime> findAllOverTime();
		
	// 查看所有加班紀錄(主管可看到所有人的)
	List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno);
	
	
	

}
