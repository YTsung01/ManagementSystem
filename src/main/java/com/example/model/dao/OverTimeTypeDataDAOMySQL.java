package com.example.model.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.OverTimeTypeData;
import com.example.model.entity.OverTimeTypeForDayData;

@Repository
public class OverTimeTypeDataDAOMySQL implements OverTimeTypeDataDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String sqlAll = "SELECT overTimeTypeId as id, overTimeType as name FROM managementsystem.overtimelistbasedata where groupName = ?";
	private final String sqlSingle = "SELECT overTimeTypeId as id, overTimeType as name FROM managementsystem.overtimelistbasedata where groupName = ? and overTimeTypeId = ?";
	
	@Override
	public List<OverTimeTypeData> findAllOverTimeTypeDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(OverTimeTypeData.class), "OverTimeType");
		
	}

	@Override
	public Optional<OverTimeTypeData> getOverTimeTypeDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(OverTimeTypeData.class), "OverTimeType", id));

	}

	@Override
	public List<OverTimeTypeForDayData> findAllOverTimeTypeForSalaryDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(OverTimeTypeForDayData.class), "OverTimeTypeForDay");

	}

	@Override
	public Optional<OverTimeTypeForDayData> getOverTimeTypeForSalaryDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(OverTimeTypeForDayData.class), "OverTimeTypeForDay", id));
	}
	}
	