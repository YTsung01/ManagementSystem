package com.example.model.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.OverTimeTypeData;
import com.example.model.entity.OverTimeTypeForSalaryData;

@Repository
public class OverTimeTypeDataDAOMySQL implements OverTimeTypeDataDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String sqlAll = "SELECT overTimeTypeId as id, overTimeType as name FROM managementsystem.overTimeTypeData where groupName = ?";
	private final String sqlSingle = "SELECT overTimeTypeId as id, overTimeType as name FROM managementsystem.overTimeTypeData where groupName = ? and overTimeTypeId = ?";
	
	@Override
	public List<OverTimeTypeData> findAllOverTimeTypeDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(OverTimeTypeData.class), "OverTimeTypeData");
		
	}

	@Override
	public Optional<OverTimeTypeData> getOverTimeTypeDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(OverTimeTypeData.class), "OverTimeTypeData", id));

	}

	@Override
	public List<OverTimeTypeForSalaryData> findAllOverTimeTypeForSalaryDatas() {
		return jdbcTemplate.query(sqlAll, new BeanPropertyRowMapper<>(OverTimeTypeForSalaryData.class), "OverTimeTypeForSalaryData");

	}

	@Override
	public Optional<OverTimeTypeForSalaryData> getOverTimeTypeForSalaryDataById(Integer id) {
		return Optional.of(jdbcTemplate.queryForObject(sqlSingle, new BeanPropertyRowMapper<>(OverTimeTypeForSalaryData.class), "OverTimeTypeForSalaryData", id));
	}
	}
	