package com.example.model.dao;


import java.util.List;

import com.example.model.entity.oldCheckIn;



public interface oldCheckInDao {
	
	
	
	//新增上班時間
	
	int addCheckIn(oldCheckIn checkIn);
	
	//新增下班時間
	//test
	int addCheckOut(oldCheckIn checkIn);
	
	// 依據empId查詢自己的打卡紀錄
	List<oldCheckIn> findAllCheckInByEmpId(Integer empId);
	
	// 依據empDeptno查詢部門的打卡紀錄
	List<oldCheckIn> findAllCheckInByDeptNo(Integer empDeptno);
		

	

}
