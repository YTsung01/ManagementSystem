package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.EmpBook;

@Repository
public class EmpBookDaoImpl implements EmpBookDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<EmpBook> findAllEmpBooks() {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmpBook.class));
	}

	@Override
	public Optional<EmpBook> findEmpBookByEmpId(Integer empId) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empId = ?";
		try {
			EmpBook empBook = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpBook.class), empId);
			return Optional.of(empBook);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public List<EmpBook> findEmpBooksByEmpDeptNo(Integer empDeptno) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empDeptno = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmpBook.class),empDeptno);
	}

	@Override
	public Optional<EmpBook> findEmpBookByEmpDeptNoAndLevelId(Integer empDeptno) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empDeptno = ? and levelId=2";
		try {
			EmpBook empBook = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpBook.class), empDeptno);
			return Optional.of(empBook);
		} catch (Exception e) {
			return Optional.empty();
		}
		}
}
