package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.entity.Salary;

@Repository
public class SalaryDaoImpl implements SalaryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Salary> findAllSalarys() {
		String sql = "SELECT id, empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate, createDate FROM salary";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Salary.class));
	}

	@Override
	public Optional<Salary> findSalaryByEmpId(Integer empId) {
		String sql = "SELECT id, empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate, createDate FROM salary where empId = ?";
		try {
			Salary salary = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Salary.class), empId);
			return Optional.of(salary);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	/**
	 * SELECT id, empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate, createDate FROM managementsystem.salary where empId=101 AND DATE_FORMAT(salaryDate, "%Y-%m") = '2024-01';
	 */
	@Override
	public Optional<Salary> findSalaryByEmpIdAndSalaryDate(Integer empId, String salaryDate) {
		String sql = "SELECT id, empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate, createDate FROM managementsystem.salary where empId=? AND DATE_FORMAT(salaryDate, \"%Y-%m\") = ?";
		try {
			Salary salary = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Salary.class), empId,salaryDate);
			return Optional.of(salary);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	/**
	 * insert into salary (empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate) values(101, 35000, 2000, 5000, 38000, '2024-01-31')
	 */
	@Override
	public int addSalary(Salary salary) {
		String sql = "insert into salary (empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount,salaryDate) values(?,?,?,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int rowsAffected = jdbcTemplate.update(
				(Connection connection) -> {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, salary.getEmpId());
					ps.setInt(2, salary.getBasicAmonut());
					ps.setInt(3, salary.getTakeoffAmount());
					ps.setInt(4, salary.getOvertimeAmount());
					ps.setInt(5, salary.getTotalAmount());
					ps.setString(6, salary.getSalaryDate());
					return ps;
				}, keyHolder);

		if (rowsAffected > 0) {
			salary.setId(keyHolder.getKey().intValue());
		}
		
		return rowsAffected;

	}

	@Override
	public Optional<Salary> findSalaryBySalaryId(Integer salaryId) {
		String sql = "SELECT id, empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate, createDate FROM salary where id = ?";
		try {
			Salary salary = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Salary.class), salaryId);
			return Optional.of(salary);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

}
