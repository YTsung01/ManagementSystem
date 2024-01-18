package com.example.model.dao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.CheckIn;
import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;

@Repository
public class OverTimeDAOMySQL implements OverTimeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	//加班申請
	@Override
	public int addOverTime(OverTime overTime ) {
		String sql = "insert into overTimeList(overTimeFormId, overTimeDate, empId, empName, empDepartment, empDeptno, empJob, overTimeStart, overTimeEnd, overTimeHour, "
				+ "overTimeLeftHour, overTimeTypeId, overTimeTypeForDayId, overTimeReason, verifyState, overTimeCheckReason) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,overTime.getOverTimeFormId(),overTime.getOverTimeDate(),overTime.getEmpId(),overTime.getEmpName(),overTime.getEmpDepartment(),overTime.getEmpDeptno(),
				overTime.getEmpJob(),overTime.getOverTimeStart(),overTime.getOverTimeEnd(),overTime.getOverTimeHour(),overTime.getOverTimeLeftHour(),overTime.getOverTimeTypeId(),
				overTime.getOverTimeTypeForDayId(),overTime.getOverTimeCheckReason(),overTime.getVerifyState(),overTime.getOverTimeCheckReason());
	}
	*/
	//加班申請
	@Override
	public int addOverTime(OverTime overTime ) {
		String sql = "insert into overTimeList(overTimeDate, empId, empName, empDepartment, empDeptno, empJob, overTimeStart, overTimeEnd, overTimeHour, "
				+ "overTimeLeftHour, overTimeTypeId, overTimeTypeForDayId, overTimeReason,verifyState, overTimeCheckReason) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		return jdbcTemplate.update(sql,overTime.getOverTimeDate(),overTime.getEmpId(),overTime.getEmpName(),overTime.getEmpDepartment(),
				overTime.getEmpDeptno(),overTime.getEmpJob(), overTime.getOverTimeStart(),overTime.getOverTimeEnd(),overTime.getOverTimeHour(),overTime.getOverTimeLeftHour(), overTime.getOverTimeTypeId(),
				overTime.getOverTimeTypeForDayId(),overTime.getOverTimeReason(), overTime.getVerifyState(),overTime.getOverTimeCheckReason());	
	}
	
	
	
	
	
	//依據empId查詢使用者
	@Override
	public Optional<Employee> findEmpById(Integer empId) {
		String sql = "select overTimeFormId, overTimeDate, empId, empName, empDepartment, empDeptno, empJob, overTimeStart, overTimeEnd, overTimeHour, "
				+ "overTimeLeftHour, overTimeTypeId, overTimeTypeForDayId, overTimeReason, verifyState, overTimeCheckReason from overTimeList where empId = ?";
	
		try {
			Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class),empId);
			return Optional.ofNullable(employee);
		}catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}




	//將加班資訊注入overTimeList
	public void enrichOverTimeListWithDetails(OverTime overTime) {
		findEmpById(overTime.getEmpId()).ifPresent(overTime :: setEmployee);
	}

	
	//依據empId查詢目前加班的累積時數
	@Override
	public Optional<OverTime> findOverTimeHourByEmpId(Integer empId) {
		String sql = "SELECT SUM(overTimeHour) FROM managementsystem.overTimeList WHERE empId = ? && verifyState = 1";
		OverTime overTime= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(OverTime.class), empId);
		return Optional.ofNullable(overTime);
	}
	
	

	//加班查詢
	@Override
	public List<OverTime> findOverTimeByEmpId(Integer empId) {
		String sql = "select empId, OverTimeFormId, verifystate from overTimeList where empId = ?";
		List<OverTime> OverTimes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empId);
		OverTimes.forEach(this::enrichOverTimeListWithDetails);
		return OverTimes;
	}

	//修改加班(注意!! 不能修改已經審核過的申請單)
	@Override
	public int updateOverTimeById(Integer empId, OverTime overTime) {
		String sql ="update overTimeList set  overTimeStart=?, overTimeEnd=?, overTimeTypeId=?, overTimeTypeForDayId=?, overTimeReason=?, verifyState=?, overTimeCheckReason=?";
		return jdbcTemplate.update(sql,overTime.getOverTimeStart(),overTime.getOverTimeEnd(),overTime.getOverTimeTypeId(),overTime.getOverTimeTypeForDayId(),
				overTime.getOverTimeReason(),overTime.getVerifyState(),overTime.getOverTimeCheckReason());
	}
	
	//取消加班
		@Override
		public int cancelOverTimeById(Integer overTimeFormId) {
			String sql ="delete from overTimeList where overTimeFormId=?";
					int rowcount=jdbcTemplate.update(sql,overTimeFormId);
					return rowcount;
		}

	// 依據部門編號 查看所有加班紀錄(主管可看到自己部門所有人的)
	@Override
	public List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno) {
		String sql = "select overTimeFormId, overTimeDate, empId, empName, empDepartment, empDeptno, empJob, overTimeStart, "
				+ "overTimeEnd, overTimeHour, overTimeLeftHour, overTimeTypeId, overTimeTypeForDayId, overTimeReason,"
				+ " verifyState, overTimeCheckReason from overTimeList where empDeptno=?";
		List<OverTime> overTimes= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empDeptno);
		return overTimes;
	}
	

}