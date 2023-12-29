package com.example.dao;

import java.util.List;

import bean.CheckIn;

public interface CheckInDao {
	
	
	
	//打卡上班
	
	int addCheckIn(CheckIn checkIn);
	
	//打卡下班
	
	// 查看所有預訂打卡紀錄
	List<CheckIn> findAllCheckIns();
	

}
