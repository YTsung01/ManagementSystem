package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.Employee;

import com.example.model.entity.Service;

@Repository
public class EmployeeDaoMySQL implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmployeeDao employeeDao;

//	1. 查詢所有員工(多筆)
	@Override
	public List<Employee> findAllEmployees() {
		String sql = "select empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary from empBook";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}

//	2. 新增員工
	@Override
	public void addEmployee(Employee employee) {
		String sql = "insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getEmpId(), employee.getEmpName(), employee.getEmppassword(),
				employee.getEmpSex(), employee.getEmpDepartment(), employee.getEmpDeptno(), employee.getEmpJob(),
				employee.getLevelId(), employee.getHireDate(), employee.getSalary());
	}

//	3. 修改密碼
	@Override
	public Boolean updatePassword(Integer empId, String newPassword) {
		String sql = "update empBook set emppassword = ? where empId = ?";
		int rowcount = jdbcTemplate.update(sql, newPassword, empId);
		return rowcount > 0;
	}

//	4. 根據員工名稱查找員工(登入用-單筆)
	@Override
	public Optional<Employee> findEmployeeByEmployeeName(String empname) {
		String sql = "select empId,empPassword,empName,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary "
				+ "from empBook where empName = ?";
		try {
			Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), empname);
			// 查找使用者可以使用的服務(授權)
			String sql2 = "SELECT serviceId,serviceLocation,serviceSubject,serviceName,serviceUrl,levelId,sort FROM managementsystem.service where levelId <= (select levelId from empbook where empName = ?) order by sort";
			List<Service> services = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Service.class),
					employee.getEmpName());
			employee.setServices(services);
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

//	5. 根據員工ID查找員工(單筆)
	@Override
	public Optional<Employee> findEmployeeByEmployeeId(Integer empId) {
		String sql = "select empId,emppassword,empName,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary "
				+ "from empBook where empId = ?";
		try {
			Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), empId);
			// 查找使用者可以使用的服務(授權)
			String sql2 = "SELECT serviceId,serviceLocation,serviceSubject,serviceName,serviceUrl,levelId,sort FROM service where levelId <= (select levelId from empbook where EmpId = ?) order by sort";
			List<Service> services = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Service.class),
					employee.getEmpId());
			employee.setServices(services);
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

}
