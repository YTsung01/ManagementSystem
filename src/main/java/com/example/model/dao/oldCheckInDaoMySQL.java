package com.example.model.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.oldCheckIn;


@Repository("CheckInDao") // 自行定義名稱
public class oldCheckInDaoMySQL implements oldCheckInDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增上班時間
	@Override
	public int addCheckIn(oldCheckIn checkIn) {
		
		String sql = "insert into CheckInList(empId,empName,empDepartment,empDeptno,empJob,checkInTime) values(?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,checkIn.getEmpId(),checkIn.getEmpName(),checkIn.getEmpDepartment(),checkIn.getEmpDetno(),
				checkIn.getEmpJob(),checkIn.getCheckInTime());
	
	}
	//新增下班時間
	@Override
	public int addCheckOut(oldCheckIn checkIn) {
		String sql = "insert into CheckInList(empId,empName,empDepartment,empDeptno,empJob,checkInTime) values(?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,checkIn.getEmpId(),checkIn.getEmpName(),checkIn.getEmpDepartment(),checkIn.getEmpDetno(),
				checkIn.getEmpJob(),checkIn.getCheckOutTime());
	}
	// 依據empId查詢自己目前的打卡紀錄
	@Override
	public List<oldCheckIn> findAllCheckInByEmpId(Integer empId) {
		
		String sql = "select empId,empName,empDepartment,empDeptno,empJob,checkInTime,checkOutTime from CheckInList where empId = ?";
		List<oldCheckIn> checkIns= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(oldCheckIn.class), empId);
		return checkIns;
		

	}
	
	// 依據empDeptno查詢目前部門的打卡紀錄
	@Override
	public List<oldCheckIn> findAllCheckInByDeptNo(Integer empDeptno) {
		String sql = "select empId,empName,empDepartment,empDeptno,empJob,checkInTime,checkOutTime FROM CheckInList where empId = ?";
		List<oldCheckIn> CheckIn= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(oldCheckIn.class), empDeptno);
		return CheckIn;
	}
	
	
	

}