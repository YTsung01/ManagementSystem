package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;
import com.example.model.entity.Service;

@Repository
public class OverTimeDaoImpl implements OverTimeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	

	//加班申請
	@Override
	public int addOverTime(OverTime overTime) {
		String sql = "insert into overtime(formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason, verifyState, checkReason) values(? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		return jdbcTemplate.update(sql,overTime.getFormId(),overTime.getStartTime(),overTime.getEndTime(),overTime.getApplyHour(),
				overTime.getOvertimeType(),overTime.getDayOrHoilday(),overTime.getReason(),overTime.getVerifyState());	
	}

	//依據empId查詢使用者
	@Override
	public Optional<Employee> findEmpById(Integer empId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	//依據empId查詢加班資料
	@Override
	public List<OverTime> findOverTimeByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	//依據empId查詢已經審核過的加班資料
	@Override
	public List<OverTime> findCheckoutOverTimeHourByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOverTimeById(Integer empId, OverTime overTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancelOverTimeById(Integer overTimeFormId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno) {
		// TODO Auto-generated method stub
		return null;
	}




}
