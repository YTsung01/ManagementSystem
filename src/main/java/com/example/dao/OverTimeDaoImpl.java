package com.example.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.CheckIn;
import com.example.entity.EmpBook;
import com.example.entity.OverTime;

@Repository
public class OverTimeDaoImpl implements OverTimeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	FormDao formDao;
	
	// formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason
	RowMapper<OverTime> rowMapper = (ResultSet rs, int rowNum) -> {
		
		OverTime overTime = new OverTime();
		overTime.setFormId(rs.getString("formId"));
		overTime.setStartTime(rs.getDate("startTime"));
		overTime.setEndTime(rs.getDate("endTime"));
		overTime.setApplyHour(rs.getInt("applyHour"));
		overTime.setDayOrHoilday(rs.getInt("dayOrHoilday"));
		overTime.setReason(rs.getString("reason"));

		EmpBook empBook = formDao.findEmpBookByFormId(rs.getString("formId")).get();
		overTime.setEmpBook(empBook);
		
		return overTime;
	};


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int addOverTime(OverTime overTime) {
		String sql = "insert into overtime(formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason)  values(?, ?, ?, ?, ?, ?,?)";
		return jdbcTemplate.update(sql, overTime.getFormId(), overTime.getStartTime(), overTime.getEndTime(),
				overTime.getApplyHour(), overTime.getOvertimeType(), overTime.getDayOrHoilday(), overTime.getReason());

	}

	// 2. 依據empId查詢使用者 (??還需要存在嗎?)
	@Override
	public Optional<EmpBook> findEmpByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	// 3. 依據empId查詢加班資料
	@Override
	public List<OverTime> findAllOverTimeByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ? ORDER BY f.applyDate DESC";
		return jdbcTemplate.query(sql, rowMapper, empId);
	}

	// 4. 依據empId查詢已經審核過的加班資料
	@Override
	public List<OverTime> findCheckoutOverTimeFormByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ? and o.verifyState = 1 ORDER BY f.applyDate DESC";
		return jdbcTemplate.query(sql, rowMapper, empId);
	}

	// 5. 依據formid修改加班(注意!! 不能修改已經審核過的申請單)
	@Override
	public int updateOverTimeByFormId(String formId, OverTime overTime) {
		String sql = "UPDATE overTime SET startTime = ?, endTime = ?, applyHour = ?, overtimeType = ?, dayOrHoilday = ?, reason = ? WHERE formId = ? and verifyState = 2";
		return jdbcTemplate.update(sql, overTime.getStartTime(), overTime.getEndTime(), overTime.getApplyHour(),
				overTime.getOvertimeType(), overTime.getDayOrHoilday(), overTime.getReason(), formId);
	}

	// 6. 依照FormId取消加班申請
	@Override
	public int cancelOverTimeByFormId(String formId) {
		String sql = "delete from overtime where formId = ?";
		int rowcount = jdbcTemplate.update(sql, formId);
		return rowcount;
	}

	// 7. 依據DeptNo查詢部門的加班資料
	// select emp.empName, emp.empDepartment, emp.empDeptno, f.formId, f.type, o.*
	// from empbook emp , form f, overtime o where f.applier = emp.empId and
	// f.formId = o.formId and emp.empDeptno=1;
	@Override
	public List<OverTime> findAllOverTimeByDeptNo(Integer empDeptno) {
		String sql = "select emp.empName, emp.empDepartment, emp.empDeptno, f.formId, f.type, o.* "
				+ "from empbook emp , form f, overtime o "
				+ "where f.applier = emp.empId and f.formId = o.formId and emp.empDeptno= ?  ORDER BY f.applyDate DESC";
		return jdbcTemplate.query(sql, rowMapper, empDeptno);
	}

	// 8. 依照empId查詢尚未審核的加班資料
	@Override
	public List<OverTime> findNonCheckoutOverTimeFormByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ? and o.verifyState = 2 ORDER BY f.applyDate DESC";
		return jdbcTemplate.query(sql, rowMapper, empId);
	}

	// 9. 查詢已經員工加班紀錄(根據起始日期與員工ID)
	/**
	 * SELECT emp.empName, f.formId, f.type, o.* FROM empbook emp, form f, overtime
	 * o WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = 101 and
	 * o.verifyState = 2 and startTime BETWEEN '2024-01-23 14:40:41' AND '2024-01-25
	 * 15:40:41';;
	 */
	@Override
	public List<OverTime> findAllOverTimeByEmpIdAndStartDateAndEndDate(Integer empId, Date startDate, Date endDate) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* FROM empbook emp, form f, overtime o WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = ?  and startTime BETWEEN  ? AND ?;";
		return jdbcTemplate.query(sql, rowMapper, empId, startDate, endDate);
	}

	// 10. 依據formId查找加班資料
	@Override
	public Optional<OverTime> findOverTimeByFormId(String formId) {
		String sql = "SELECT emp.empName, f.formId, f.type, o.* " + "FROM empbook emp, form f, overtime o "
				+ "WHERE f.applier = emp.empId AND f.formId = o.formId and o.formId = ?";
		try {
			OverTime overTime = jdbcTemplate.queryForObject(sql, rowMapper, formId);
			return Optional.of(overTime);
		} catch (Exception e) {
			return Optional.empty();

		}
	}

	// 11.依照formId 同意加班狀態 verifyState = 1
	@Override
	public int passOverTimeByFormId(String formId) {
		String sql = "UPDATE overTime SET  verifyState = 1 WHERE formId = ? ";
		return jdbcTemplate.update(sql, formId);
	}
	//UPDATE overtime SET  verifyState = 0 , checkReason = 'test' WHERE formId = '63c0a02a-692e-41a5-a934-434fd51919ed'  ;
	// 12.依照formId 不同意加班狀態 verifyState = 0
	@Override
	public int falseOverTimeByFormId(String formId, String checkReason) {
		String sql = "UPDATE overtime SET  verifyState = 0 , checkReason = ? WHERE formId = ?  ";
		return jdbcTemplate.update(sql, checkReason , formId);
	}

}
