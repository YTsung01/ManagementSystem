package com.example.model.dao;

import java.util.List;
import java.util.Optional;



import com.example.model.entity.oldOverTimeTypeData;
import com.example.model.entity.oldOverTimeTypeForDayData;




public interface oldOverTimeTypeDataDAO {
	
	List<oldOverTimeTypeData> findAllOverTimeTypeDatas();
	Optional<oldOverTimeTypeData> getOverTimeTypeDataById(Integer id);
	
	List<oldOverTimeTypeForDayData> findAllOverTimeTypeForSalaryDatas();
	Optional<oldOverTimeTypeForDayData> getOverTimeTypeForSalaryDataById(Integer id);

}