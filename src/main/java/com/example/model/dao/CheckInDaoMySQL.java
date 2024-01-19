package com.example.model.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.CheckIn;


@Repository("CheckInDao") // 自行定義名稱
public class CheckInDaoMySQL implements CheckInDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增上班時間
	@Override
	public int addCheckIn(CheckIn checkIn) {
		
		String sql = "insert into CheckInList(empId,empName,empDepartment,empDeptno,empJob,checkInTime) values(?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,checkIn.getEmpId(),checkIn.getEmpName(),checkIn.getEmpDepartment(),checkIn.getEmpDetno(),
				checkIn.getEmpJob(),checkIn.getCheckInTime());
	
	}
	//新增下班時間
	@Override
	public int addCheckOut(CheckIn checkIn) {
		String sql = "insert into CheckInList(empId,empName,empDepartment,empDeptno,empJob,checkInTime) values(?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,checkIn.getEmpId(),checkIn.getEmpName(),checkIn.getEmpDepartment(),checkIn.getEmpDetno(),
				checkIn.getEmpJob(),checkIn.getCheckOutTime());
	}
	// 依據empId查詢自己目前的打卡紀錄
	@Override
	public List<CheckIn> findAllCheckInByEmpId(Integer empId) {
		
		String sql = "select empId,empName,empDepartment,empDeptno,empJob,checkInTime,checkOutTime from CheckInList where empId = ?";
		List<CheckIn> checkIns= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class), empId);
		return checkIns;
		

	}
	
	// 依據empDeptno查詢目前部門的打卡紀錄
	@Override
	public List<CheckIn> findAllCheckInByDeptNo(Integer empDeptno) {
		String sql = "select empId,empName,empDepartment,empDeptno,empJob,checkInTime,checkOutTime FROM CheckInList where empId = ?";
		List<CheckIn> CheckIn= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class), empDeptno);
		return CheckIn;
	}
	
	
	

}