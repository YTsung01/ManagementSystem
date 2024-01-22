package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.FormDao;
import com.example.dao.OverTimeDao;
import com.example.entity.Form;
import com.example.entity.OverTime;

@Service
public class FormServiceImpl {

	@Autowired
	FormDao formDao;
	
	@Autowired
	OverTimeDao overTimeDao;
	
	@Transactional
	public Form addForm() throws ParseException {
		
		String uuid = UUID.randomUUID().toString();
		
		Form form = new Form();
		form.setApplier(101);
		form.setFormId(uuid);
		form.setType(2);
		form.setApplyDate(new Date());

		formDao.addForm(form);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		OverTime overTime = new OverTime();
		overTime.setFormId(uuid);
		overTime.setStartTime(sdf.parse("2024-01-25 17:00"));
		overTime.setEndTime(sdf.parse("2024-01-25 20:30"));
		overTime.setApplyHour(4);
		overTime.setReason("羽球");
		overTime.setDayOrHoilday(1);
		overTime.setOvertimeType(1);
		overTimeDao.addOverTime(overTime);
		
		return form;
		
	}

}
