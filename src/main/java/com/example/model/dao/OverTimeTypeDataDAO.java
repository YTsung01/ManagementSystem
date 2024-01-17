package com.example.model.dao;

import java.util.List;
import java.util.Optional;



import com.example.model.entity.OverTimeTypeData;
import com.example.model.entity.OverTimeTypeForSalaryData;



public interface OverTimeTypeDataDAO {
	
	List<OverTimeTypeData> findAllOverTimeTypeDatas();
	Optional<OverTimeTypeData> getOverTimeTypeDataById(Integer id);
	
	List<OverTimeTypeForSalaryData> findAllOverTimeTypeForSalaryDatas();
	Optional<OverTimeTypeForSalaryData> getOverTimeTypeForSalaryDataById(Integer id);

}