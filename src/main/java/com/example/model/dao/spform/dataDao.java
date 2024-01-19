package com.example.model.dao.spform;

import java.util.List;
import java.util.Optional;

import com.example.model.entity.spform.AgentData;
import com.example.model.entity.spform.LeaveDayType;


public interface dataDao {
	
	List<AgentData> findAllAgentDatas(); //代理人清單
	Optional<AgentData> getAgentDataById(Integer id);
	
	List<LeaveDayType> findAllLeaveDayTypes();//請假類別
	Optional<LeaveDayType> getLeaveDayTypeById(Integer id);
}
