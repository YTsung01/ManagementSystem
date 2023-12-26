package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.Employee;



@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmployeeDao employeeDao;

//	1. 查詢所有使用者(多筆)
	@Override
	public List<Employee> findAllEmployees() {
		String sql = "select empId,empName,password,empSex,empDepartment,empJob from empBook";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}
//	2. 新增使用者
	@Override
	public void addEmployee(Employee employee) {
		String sql = "insert into empBook(empId,empName,password,empSex,empDepartment,empJob) values(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getEmpId(), employee.getEmpName(), employee.getEmppassword(),
									employee.getEmpSex(),employee.getEmpDepartment(), employee.getEmpJob());
		
	}

//	3. 修改密碼
	@Override
	public Boolean updatePassword(Integer empId, String newPassword) {
		String sql = "update empBook set password = ? where empId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, empId);
		return rowcount > 0;
	}

//	4. 根據員工名稱查找使用者(登入用-單筆)
	@Override
	public Optional<Employee> findEmployeeByEmployeeName(String empname) {
		String sql = "select empId,password,empSex,empDepartment,empJob from empBook where empName = ?";
		try {
			Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), empname);
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

//	5. 根據使用者ID查找使用者(單筆)
	@Override
	public Optional<Employee> findEmployeeByEmployeeId(Integer empId) {
		String sql = "select empName,password,empSex,empDepartment,empJob from empBook where empId = ?";
		try {
			Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), empId);
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	
	
	
	
	

}
