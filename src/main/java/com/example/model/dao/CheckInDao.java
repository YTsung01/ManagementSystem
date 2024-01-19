package com.example.model.dao;


import java.util.List;

import com.example.model.entity.CheckIn;



public interface CheckInDao {
	
	
	
	//新增上班時間
	
	int addCheckIn(CheckIn checkIn);
	
	//新增下班時間
	//test
	int addCheckOut(CheckIn checkIn);
	
	// 依據empId查詢自己的打卡紀錄
	List<CheckIn> findAllCheckInByEmpId(Integer empId);
	
	// 依據empDeptno查詢部門的打卡紀錄
	List<CheckIn> findAllCheckInByDeptNo(Integer empDeptno);
		

	

}
