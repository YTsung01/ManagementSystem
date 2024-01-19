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
	//4.
	@Override
	public Optional<Form> findFormByEmpIdAndType(Integer applier, Integer type) {
		String sql = "SELECT formId, type, applier, applyDate from form where applier = ? and type =?";
		try {
			Form form = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Form.class), applier, type);
			return Optional.of(form);
		} catch (Exception e) {
			return Optional.empty();
		}
		
		
	}
	//5. 查找所有表單
	@Override
	public List<Form> findAllForms() {
		String sql = "select formId, type, applier, applyDate from form"; 
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Form.class));
	}

}
