package com.example.model.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.oldOverTimeTypeData;
import com.example.model.entity.oldOverTimeTypeForDayData;

@Repository
public class oldOverTimeTypeDataDAOMySQL implements oldOverTimeTypeDataDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String sqlAll = "SELECT overTimeTypeId as id, overTimeType as name FROM managementsystem.overtimelistbasedata where groupName = ?";
	private final String sqlSingle = "SELECT overTimeTypeId as id, overTimeType as name FROM managementsystem.overtimelistbasedata where groupName = ? and overTimeTypeId = ?";
	
	@Override
	public List<oldOverTimeTypeData> findAllOverTimeTypeDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(oldOverTimeTypeData.class), "OverTimeType");
		
	}

	@Override
	public Optional<oldOverTimeTypeData> getOverTimeTypeDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(oldOverTimeTypeData.class), "OverTimeType", id));

	}

	@Override
	public List<oldOverTimeTypeForDayData> findAllOverTimeTypeForSalaryDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(oldOverTimeTypeForDayData.class), "OverTimeTypeForDay");

	}

	@Override
	public Optional<oldOverTimeTypeForDayData> getOverTimeTypeForSalaryDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(oldOverTimeTypeForDayData.class), "OverTimeTypeForDay", id));
	}
	}
	