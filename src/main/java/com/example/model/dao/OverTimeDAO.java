package com.example.model.dao;

import java.util.List;

import com.example.model.entity.CheckIn;
import com.example.model.entity.OverTime;
import com.example.model.entity.Employee;


public interface OverTimeDAO {
	
	
	//加班申請
	int addOverTime(OverTime overTime , Employee employee);
	
	
	//查詢目前加班的累積時數
	List<OverTime> findOverTimeHourByEmpId(Integer empId);
	
	
	//加班查詢
	List<OverTime> findOverTimeByEmpId(Integer empId);
	
	//修改加班(注意!! 不能修改已經審核過的申請單)
	int updateOverTimeById(Integer empId);
	
	
	// 查看所有加班紀錄(主管可看到所有人的)
	List<OverTime> findAllCheckIn();
	
	//取消加班
	int cancelOverTimeById(Integer overTimeFormId);
	

}
