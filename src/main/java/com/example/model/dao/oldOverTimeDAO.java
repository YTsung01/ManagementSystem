package com.example.model.dao;

import java.util.List;
import java.util.Optional;


import com.example.model.entity.oldOverTime;
import com.example.model.entity.oldEmployee;


public interface oldOverTimeDAO {
	
	
	//加班申請
	int addOverTime(oldOverTime overTime);
	
	
	//依據empId查詢使用者
	Optional<oldEmployee>findEmpById(Integer empId);
	

	//依據empId查詢已經審核過的加班資料
		
	List<oldOverTime> findCheckoutOverTimeHourByEmpId(Integer empId);
	
	//修改加班(注意!! 不能修改已經審核過的申請單)
	int updateOverTimeById(Integer empId, oldOverTime overTime);
	
	//取消加班
	int cancelOverTimeById(Integer overTimeFormId);
	
	//加班查詢
	List<oldOverTime> findOverTimeByEmpId(Integer empId);
	
	
	// 查看所有加班紀錄
	//List<OverTime> findAllOverTime();
		
	// 查看所有加班紀錄(主管可看到所有人的)
	List<oldOverTime> findAllOverTimeByDeptNo(Integer empDeptno);
	
	
	

}
