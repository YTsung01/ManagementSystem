package com.example.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;

public class OverTimeDAOMySQL implements OverTimeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OverTimeDAO overTimeDAO;

	@Override
	public int addOverTime(OverTime overTime , Employee employee) {
		String sql = "insert into overTimeList(empId, empName, empDepartment, verifyState, overTimeDate, overTimeType, overTimeTypeForSalary, "
				+ "overTimeReason) values(?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,employee.getEmpId(), employee.getEmpName(), employee.getEmpDepartment(), overTime.getVerifyState(),overTime.getOverTimeDate()
									, overTime.getOverTimeType(), overTime.getOverTimeTypeForSalary(), overTime.getOverTimeReason() );
	}

	@Override
	public List<OverTime> findOverTimeHourByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OverTime> findOverTimeByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOverTimeById(Integer empId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OverTime> findAllCheckIn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cancelOverTimeById(Integer overTimeFormId) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	

}
