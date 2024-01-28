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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.entity.Salary;

@Repository
public class FormDaoImpl implements FormDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * insert into form(formId,type, applier, applyDate) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b',1,101,'2024-01-18');
	 * */
	// 1. 新增表單以及附件
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int addForm(Form form) {
		String sql = "insert into form(formId, type, applier, applyDate) values(?,?,?,?)";
		return jdbcTemplate.update(sql,form.getFormId(),form.getType(),form.getApplier(),form.getApplyDate());
		
	}
	
	/*
	 * select formId, type, applier, applyDate from form;
	 * */
	//2. 依據formId查找表單(單筆)
	@Override
	public Optional<Form> findFormByFormId(String formId) {
		String sql = "SELECT formId, type, applier, applyDate from form where formId = ?";
		try {
			Form form = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Form.class), formId);
			return Optional.of(form);
		} catch (Exception e) {
			return Optional.empty();
		}
	
	}
	/*
	 * select emp.empName, f.formId, f.type, o.* ,t.* from empbook emp , form f, overtime o,takeoff t 
		where f.applier = emp.empId;
	 * */
	//3. 依據empId查找其所有表單(多筆)
	@Override
	public List<Form> findAllFormsByEmpId(Integer empId) {
		String sql = "select emp.empName, f.formId, f.type, o.* ,t.* from empbook emp , form f, overtime o,takeoff t "
				+ "where f.applier = emp.empId and emp.empId=?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Form.class),empId);
	}
	//4.依據EmpId和申請類型Type查找表單(多筆)
	@Override
	public List<Form> findFormByEmpIdAndType(Integer applier, Integer type) {
		String sql = "SELECT formId, type, applier, applyDate from form where applier = ? and type =?";
		return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Form.class), applier, type);
			 
	}
	//5. 查找所有表單
	@Override
	public List<Form> findAllForms() {
		String sql = "select formId, type, applier, applyDate from form"; 
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Form.class));
	}

	@Override
	public Optional<EmpBook> findEmpBookByFormId(String formId) {
		String sql = "select e.empId, e.empName, e.empPassword, e.empSex, e.empDepartment, e.empDeptno, e.empJob, e.levelId, e.hireDate, e.salary, e.overTimeLeftHour, e.overTimeTotalHour, e.takeoffTotalHours, e.empAcator from form f, empbook e where f.applier = e.empId and f.formId = ?";
		try {
			EmpBook empBook = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpBook.class), formId);
			return Optional.of(empBook);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	//6. 依據formID刪除表單
	@Override
	public int cancelFormByFormId(String formId) {
		String sql = "delete from form where formId = ?";
		int rowcount = jdbcTemplate.update(sql, formId);
		return rowcount;
	}
	
	
}
