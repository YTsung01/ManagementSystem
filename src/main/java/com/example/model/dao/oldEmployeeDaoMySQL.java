package com.example.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.oldEmployee;

import com.example.model.entity.oldService;

@Repository
public class oldEmployeeDaoMySQL implements oldEmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private oldEmployeeDao employeeDao;

//	1. 查詢所有員工(多筆)
	@Override
	public List<oldEmployee> findAllEmployees() {
		String sql = "select empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary from empBook";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(oldEmployee.class));
	}

//	2. 新增員工
	@Override
	public void addEmployee(oldEmployee employee) {
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
	public Optional<oldEmployee> findEmployeeByEmployeeName(String empname) {
		String sql = "select empId,empPassword,empName,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary "
				+ "from empBook where empName = ?";
		try {
			oldEmployee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(oldEmployee.class), empname);
			// 查找使用者可以使用的服務(授權)
			String sql2 = "SELECT serviceId,serviceLocation,serviceSubject,serviceName,serviceUrl,levelId,sort FROM managementsystem.service where levelId <= (select levelId from empbook where empName = ?) order by sort";
			List<oldService> services = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(oldService.class),
					employee.getEmpName());
			employee.setServices(services);
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

//	5. 根據員工ID查找員工(單筆)
	@Override
	public Optional<oldEmployee> findEmployeeByEmployeeId(Integer empId) {
		String sql = "select empId,emppassword,empName,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary "
				+ "from empBook where empId = ?";
		try {
			oldEmployee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(oldEmployee.class), empId);
			// 查找使用者可以使用的服務(授權)
			String sql2 = "SELECT serviceId,serviceLocation,serviceSubject,serviceName,serviceUrl,levelId,sort FROM service where levelId <= (select levelId from empbook where EmpId = ?) order by sort";
			List<oldService> services = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(oldService.class),
					employee.getEmpId());
			employee.setServices(services);
			return Optional.ofNullable(employee);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

//	6. 根據部門ID查找主管(單筆)
	@Override
	public Optional<oldEmployee> findSupervisorByempDeptno(Integer empDeptno) {
		String sql = "select empId,empName,empDeptno,levelId "
				+ "from empBook where empDeptno = ? && levelId = ?";

		return Optional.empty();
	}
	
	

}
