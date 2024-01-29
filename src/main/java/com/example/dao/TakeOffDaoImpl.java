package com.example.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.EmpBook;

import com.example.entity.TakeOff;

@Repository
public class TakeOffDaoImpl implements TakeOffDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	FormDao formDao;
	
	// formId, agent, takeoffType, startTime, endTime, reason, verifyState, checkReason, takeoffDay, takeoffHour                                                 
	RowMapper<TakeOff> rowMapper = (ResultSet rs, int rowNum) -> {
		
		TakeOff takeOff = new TakeOff();
		takeOff.setFormId(rs.getString("formId"));
		takeOff.setAgent(rs.getInt("agent"));
		takeOff.setTakeoffType(rs.getInt("takeoffType"));
		takeOff.setStartTime(rs.getTimestamp("startTime"));
		takeOff.setEndTime(rs.getTimestamp("endTime"));
		takeOff.setReason(rs.getString("reason"));
		takeOff.setVerifyState(rs.getInt("verifyState"));
		takeOff.setCheckReason(rs.getString("checkReason"));
		takeOff.setTakeoffDay(rs.getInt("takeoffDay"));
		takeOff.setTakeoffHour(rs.getInt("takeoffHour"));
		
		EmpBook empBook = formDao.findEmpBookByFormId(rs.getString("formId")).get();
		takeOff.setEmpBook(empBook);
		
		return takeOff;
	};

	
	//1. 新增請假申請
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int addTakeOff(TakeOff takeOff) {
		String sql = "insert into takeOff(formId, agent, takeoffType, startTime, endTime, reason, takeoffDay, takeoffHour)  values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,takeOff.getFormId(),takeOff.getAgent(),takeOff.getTakeoffType(),takeOff.getStartTime(),takeOff.getEndTime(),takeOff.getReason(),
				takeOff.getTakeoffDay(),takeOff.getTakeoffHour());	
	}

	
	//2. 依據empId查詢使用者
	@Override
	public Optional<EmpBook> findEmpByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	/*
	@Override
	public Optional<EmpBook> findEmpByEmpId(Integer empId) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empId = ?";
		try {
			EmpBook empBook = jdbcTemplate.queryForObject(sql, rowMapper, empId);
			return Optional.of(empBook);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	*/

	//3. 依據empId查詢請假資料
	@Override
	public List<TakeOff> findTakeOffByEmpId(Integer empId) {
		String sql = "select emp.empName, f.formId, f.type, f.applyDate, t.* from empbook emp , form f, takeoff t "
				+ "where f.applier = emp.empId and f.formId = t.formId AND emp.empId = ? ORDER BY f.applyDate DESC;";
		return jdbcTemplate.query(sql, rowMapper, empId);
	}

	//4. 依據empId查詢已經審核過的加班資料
	@Override
	public List<TakeOff> findCheckoutTakeOffByEmpId(Integer empId) {
		String sql = "SELECT emp.empName,f.applyDate, f.formId, f.type, t.* " + "FROM empbook emp, form f, takeoff t "
				+ "WHERE f.applier = emp.empId AND f.formId = t.formId AND emp.empId = ? and t.verifyState = 1  ORDER BY f.applyDate DESC";
		return jdbcTemplate.query(sql, rowMapper, empId);
	}

	//5. 依據formId修改請假(注意!! 不能修改已經審核過的申請單)
	@Override
	public int updateTakeOffByEmpId(String formId, TakeOff takeOff) {
		 String sql = "UPDATE takeOff SET  agent=?, takeoffType=?, startTime=?, endTime=?, reason=?, checkReason=?, takeoffDay=?, takeoffHour=?";
		    return jdbcTemplate.update(sql,takeOff.getAgent(),takeOff.getTakeoffType(),takeOff.getStartTime(),takeOff.getEndTime(),takeOff.getReason(),
					takeOff.getCheckReason(),takeOff.getTakeoffDay(),takeOff.getTakeoffHour());
	}

	//6. 依照FormId取消請假申請
	@Override
	public int cancelTakeOffByFormId(String formId) {
		String sql = "delete from takeoff where formId = ?";
		int rowcount = jdbcTemplate.update(sql, formId);
		return rowcount;
	}

	//7. 依據DeptNo查詢部門的請假資料
	@Override
	public List<TakeOff> findAllTakeOffByDeptNo(Integer empDeptno) {
		String sql = "select emp.empName, emp.empDepartment, emp.empDeptno, f.formId, f.type, t.* "
				+ "from empbook emp , form f, takeoff t "
				+ "where f.applier = emp.empId and f.formId = t.formId and emp.empDeptno= ?";
		return jdbcTemplate.query(sql, rowMapper, empDeptno);
	}

	//8. 依照empId查詢尚未審核的請假資料
	@Override
	public List<TakeOff> findNonCheckoutTakeOffFormByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, t.* " + "FROM empbook emp, form f, takeoff t "
				+ "WHERE f.applier = emp.empId AND f.formId = t.formId AND emp.empId = ? and t.verifyState = 2";
		return jdbcTemplate.query(sql,rowMapper, empId);
	}

	/**
	 * SELECT emp.empName, f.formId, f.type, o.* FROM empbook emp, form f, overtime
	 * o WHERE f.applier = emp.empId AND f.formId = o.formId AND emp.empId = 101 and
	 * o.verifyState = 2 and startTime BETWEEN '2024-01-23 14:40:41' AND '2024-01-25
	 * 15:40:41';;
	 */
	
	// 9. 查詢員工請假紀錄(根據起始日期與員工ID)
	@Override
	public List<TakeOff> findAllTakeOffByEmpIdAndStartDateAndEndDate(Integer empId, Date startDate, Date endDate) {
		String sql = "SELECT emp.empName, f.formId, f.type, t.* FROM empbook emp, form f, takeoff t WHERE f.applier = emp.empId AND f.formId = t.formId AND emp.empId = ?  and startTime BETWEEN  ? AND ?;";
		return jdbcTemplate.query(sql, rowMapper, empId, startDate, endDate);
	}


	// 10. 依照formId查詢請假表單
	@Override
	public Optional<TakeOff> findTakeOffByFormId(String formId) {
		String sql = "SELECT emp.empName, f.formId, f.type, t.* "
				+ "FROM empbook emp, form f, takeoff "
				+ "WHERE f.applier = emp.empId AND f.formId = t.formId and t.formId = ?";
		try {
			TakeOff takeOff = jdbcTemplate.queryForObject(sql, rowMapper, formId);
			return Optional.of(takeOff);
		} catch (Exception e) {
			return Optional.empty();

		}
	}

	// 11.依照formId 同意請假狀態 verifyState = 1
	@Override
	public int passTakeOffByFormId(String formId) {
		String sql = "UPDATE takeoff SET  verifyState = 1 WHERE formId = ? ";
		return jdbcTemplate.update(sql, formId);
	}

	// 12.依照formId 不同意請假狀態 verifyState = 0
	@Override
	public int falseTakeOffByFormId(String formId, String checkReason) {
		String sql = "UPDATE takeoff SET  verifyState = 0 , checkReason = ? WHERE formId = ?  ";
		return jdbcTemplate.update(sql, checkReason , formId);
	}


	

}