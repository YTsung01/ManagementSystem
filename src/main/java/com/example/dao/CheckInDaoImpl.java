package com.example.dao;

import java.util.List;

import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.CheckIn;


@Repository("CheckInDao") // 自行定義名稱
public class CheckInDaoImpl implements CheckInDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CheckInDao checkInDao;

	@Override
	public int addCheckIn(CheckIn checkIn) {
		
		String sql = "insert into CheckInList(empId,empName,empSex,empDepartment,empJob,checkInTime) values(?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,checkIn.getEmpId(),checkIn.getEmpName(),checkIn.getEmpSex(),checkIn.getEmpDepartment(),
				checkIn.getEmpJob(),checkIn.getCheckInTime());
	
	}

	@Override
	public List<CheckIn> findAllCheckIns() {
		String sql = "SELECT empId,empName,empSex,empDepartment,empJob,checkInTime FROM CheckInList";
		List<CheckIn> checkIns= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CheckIn.class));
		return checkIns;
		
		
	}
	
	

}
