package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.EmpBook;
import com.example.model.entity.Employee;
import com.example.model.entity.Service;

@Repository
public class EmpBookDaoImpl implements EmpBookDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	1. 查詢所有員工(多筆)
	@Override
	public List<EmpBook> findAllEmpBooks() {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmpBook.class));
	}
	
//	2. 根據員工名稱查找員工(登入用-單筆)
	@Override
	public Optional<Employee> findEmployeeByEmployeeName(String empname) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empName = ?";
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


//	3. 根據員工ID查找員工(單筆)
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
	

//	4. 根據部門ID查找所有員工(多筆)
	@Override
	public List<EmpBook> findEmpBooksByEmpDeptNo(Integer empDeptno) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator FROM empbook where empDeptno = ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmpBook.class),empDeptno);
	}

//	5. 根據部門ID及levelId查找主管(單筆)
	@Override
	public Optional<EmpBook> findEmpBooksByEmpDeptNoAndLevelId(Integer empDeptno, Integer levelId) {
		String sql = "SELECT empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator "
				+ "FROM empbook where empDeptno = ? && levelId=? ";
		try {
			EmpBook empBook = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpBook.class), empDeptno,levelId);
			return Optional.of(empBook);
		} catch (Exception e) {
			return Optional.empty();
		}
	}




}
