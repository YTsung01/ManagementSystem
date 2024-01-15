package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.entity.CheckIn;
import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;

public class OverTimeDAOMySQL implements OverTimeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OverTimeDAO overTimeDAO;

	//加班申請
	@Override
	public int addOverTime(OverTime overTime ) {
		String sql = "insert into overTimeList(overTimeFormId, empId, empName, empDepartment, empDeptno, empJob, overTimeDate, overTimeHour, "
				+ "overTimeType, overTimeTypeForDay, overTimeReason, verifyState, overTimeCheckReason) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,overTime.getOverTimeFormId(),overTime.getEmpId(),overTime.getEmpName(),overTime.getEmpDepartment(),
									overTime.getEmpDeptno(),overTime.getEmpJob(),overTime.getOverTimeDate(),overTime.getOverTimeHour(),overTime.getOverTimeType(),
									overTime.getOverTimeTypeForDay(),overTime.getOverTimeReason(),overTime.getVerifyState(),overTime.getOverTimeCheckReason());
	}
	
	//依據empId查詢使用者
	@Override
	public Optional<Employee> findEmpById(Integer empId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	
	//將加班資訊注入overTimeList

	public void enrichOverTimeListWithDetails(OverTime overTime) {
		findEmpById(overTime.getEmpId()).ifPresent(overTime :: setEmployee);
	}

	
	//依據empId查詢目前加班的累積時數
	@Override
	public List<OverTime> findOverTimeHourByEmpId(Integer empId) {
		String sql = "select empId, overTimeHour, verifyState from overTimeList where empId=? and verifyState=1";
		
	}

	//加班查詢
	@Override
	public List<OverTime> findOverTimeByEmpId(Integer empId) {
			String sql = "select empId, OverTimeFormId, verifystate from overTimeList where empId = ?";
			List<OverTime> OverTimes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empId);
			OverTimes.forEach(this::enrichOverTimesWithDetails);
			return OverTimes;
		
	}

	//修改加班(注意!! 不能修改已經審核過的申請單)
	@Override
	public int updateOverTimeById(Integer empId, OverTime overTime) {
		String sql ="update overTimeList set  overTimeDate=?, overTimeHour=?, overTimeType=?, overTimeTypeForDay=?, overTimeReason=?";
		return jdbcTemplate.update(sql,overTime.getOverTimeDate(), overTime.getOverTimeHour(),overTime.getOverTimeType(),
									overTime.getOverTimeTypeForDay(),overTime.getOverTimeReason());
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
	public List<OverTime> findAllOverTime() {
		String sql = "Sselect overTimeFormId, empId, empName, empDepartment, empDeptno, empJob, overTimeDate, overTimeHour, \"\r\n"
				+ "				+ \"overTimeType, overTimeTypeForDay, overTimeReason, verifyState, overTimeCheckReason from overTimeList where empDeptno=?";
		List<OverTime> overTimes= jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class));
		return overTimes;
	}

	


	
	

}
