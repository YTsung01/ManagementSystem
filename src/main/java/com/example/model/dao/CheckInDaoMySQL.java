package com.example.model.dao;

import java.util.List;

import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.CheckIn;

//test


@Repository("CheckInDao") // 自行定義名稱
public class CheckInDaoMySQL implements CheckInDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CheckInDao checkInDao;

	@Override
	public int addCheckIn(CheckIn checkIn) {
		
		String sql = "insert into CheckInList(empId,empName,empDepartment,empJob,checkInTime) values(?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,checkIn.getEmpId(),checkIn.getEmpName(),checkIn.getEmpDepartment(),
				checkIn.getEmpJob(),checkIn.getCheckInTime());
	
	}

	@Override
	public List<CheckIn> findAllCheckIn() {
		String sql = "SELECT empId,empName,empDepartment,empJob,checkInTime FROM CheckInList";
		List<CheckIn> checkIns= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class));
		return checkIns;
		

	}
	
	
	

}