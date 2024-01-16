package com.example.model.dao.spform;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.entity.spform.AgentData;
import com.example.model.entity.spform.LeaveDayType;


@Repository
public class DataDaoMySQL implements dataDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String sqlAll = "SELECT itemId as id, itemName as name FROM web.basedata where groupName = ?";
	private final String sqlSingle = "SELECT itemId as id, itemName as name FROM web.basedata where groupName = ? and itemId = ?";
	

	@Override
	public List<AgentData> findAllAgentDatas() {
		
		return null;
	}

	@Override
	public Optional<AgentData> getAgentDataById(Integer id) {
		return Optional.empty();
		
	}

	@Override
	public List<LeaveDayType> findAllLeaveDayTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<LeaveDayType> getLeaveDayTypeById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	
	
	

}
