package com.example.model.dao;

import java.util.List;
import java.util.Optional;



import com.example.model.entity.OverTimeTypeData;
import com.example.model.entity.OverTimeTypeForDayData;



public interface OverTimeTypeDataDAO {
	
	List<OverTimeTypeData> findAllOverTimeTypeDatas();
	Optional<OverTimeTypeData> getOverTimeTypeDataById(Integer id);
	
	List<OverTimeTypeForDayData> findAllOverTimeTypeForSalaryDatas();
	Optional<OverTimeTypeForDayData> getOverTimeTypeForSalaryDataById(Integer id);

}