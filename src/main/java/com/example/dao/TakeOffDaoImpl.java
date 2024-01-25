package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.EmpBook;

import com.example.entity.TakeOff;

@Repository
public class TakeOffDaoImpl implements TakeOffDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//1. 新增請假申請
	@Override
	public int addTakeOff(TakeOff takeOff) {
		String sql = "insert into takeOff(formId, agent, takeoffType, startTime, endTime, reason, takeoffDay, takeoffHour)  values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,takeOff.getFormId(),takeOff.getAgent(),takeOff.getTakeoffType(),takeOff.getStartTime(),takeOff.getEndTime(),takeOff.getReason(),
				takeOff.getTakeoffDay(),takeOff.getTakeoffHour());	
	}

	//2. 依據empId查詢使用者
	@Override
	public Optional<EmpBook> findEmpByEmpId(Integer empId) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empId = ?";
		try {
			EmpBook empBook = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpBook.class), empId);
			return Optional.of(empBook);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	//3. 依據empId查詢請假資料
	@Override
	public List<TakeOff> findTakeOffByEmpId(Integer empId) {
		String sql = "select emp.empName, f.formId, f.type, t.* from empbook emp , form f, takeoff t "
				+ "where f.applier = emp.empId and f.formId = t.formId AND emp.empId = ?;";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TakeOff.class), empId);
	}

	//4. 依據empId查詢已經審核過的加班資料
	@Override
	public List<TakeOff> findCheckoutTakeOffByEmpId(Integer empId) {
		String sql = "SELECT emp.empName, f.formId, f.type, t.* " + "FROM empbook emp, form f, takeoff t "
				+ "WHERE f.applier = emp.empId AND f.formId = t.formId AND emp.empId = ? and t.verifyState = 1";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TakeOff.class), empId);
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
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TakeOff.class), empDeptno);
	}

}

