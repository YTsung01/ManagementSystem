package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.EmpBook;
import com.example.entity.OverTime;

@Repository
public class OverTimeDaoImpl implements OverTimeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//1. 新增加班申請
	//insert into overtime(formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason)values('a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a','2024-01-19 17:00','2024-01-19 19:00',TIMESTAMPDIFF(HOUR,startTime,endTime),1, 1, '工作太多');
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int addOverTime(OverTime overTime) {
		String sql = "insert into overtime(formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason)  values(?, ?, ?, ?, ?, ?,?)";
		return jdbcTemplate.update(sql,overTime.getFormId(),overTime.getStartTime(),overTime.getEndTime(),overTime.getApplyHour(),
				overTime.getOvertimeType(),overTime.getDayOrHoilday(),overTime.getReason());	
	
	}
	
	//2. 依據empId查詢使用者 (??還需要存在嗎?)
	@Override
	public Optional<EmpBook> findEmpByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	//3. 依據empId查詢加班資料
	@Override
	public List<OverTime> findOverTimeByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empId);
	}
	//4. 依據empId查詢已經審核過的加班資料
	@Override
	public List<OverTime> findCheckoutOverTimeFormByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ? and o.verifyState = 1";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empId);
	}
	//5. 依據formid修改加班(注意!! 不能修改已經審核過的申請單)
	@Override
	public int updateOverTimeByFormId(String formId,OverTime overTime) {
	    String sql = "UPDATE overTime SET startTime = ?, endTime = ?, applyHour = ?, overtimeType = ?, dayOrHoilday = ?, reason = ? WHERE formId = ? and verifyState = 2";
	    return jdbcTemplate.update(sql, overTime.getStartTime(), overTime.getEndTime(), overTime.getApplyHour(), overTime.getOvertimeType(), overTime.getDayOrHoilday(), overTime.getReason(), formId);
	}
	//6. 依照FormId取消加班申請
	@Override
	public int cancelOverTimeByFormId(String formId) {
		String sql = "delete from overtime where formId = ?";
		int rowcount = jdbcTemplate.update(sql, formId);
		return rowcount;
	}
	//7. 依據DeptNo查詢部門的加班資料
	//select emp.empName, emp.empDepartment, emp.empDeptno, f.formId, f.type, o.* from empbook emp , form f, overtime o where f.applier = emp.empId and f.formId = o.formId and emp.empDeptno=1;
	@Override
	public List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno) {
		String sql = "select emp.empName, emp.empDepartment, emp.empDeptno, f.formId, f.type, o.* "
				+ "from empbook emp , form f, overtime o "
				+ "where f.applier = emp.empId and f.formId = o.formId and emp.empDeptno= ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empDeptno);
	}
	
	//8. 依照empId查詢尚未審核的加班資料
	@Override
	public List<OverTime> findNonCheckoutOverTimeFormByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ? and o.verifyState = 2";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OverTime.class), empId);
	}

}
