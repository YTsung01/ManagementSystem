package com.example.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.AttachementDao;
import com.example.dao.CheckInDaoImpl;
import com.example.dao.FormDao;
import com.example.entity.Attachement;
import com.example.entity.CheckIn;
import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.service.FormServiceImpl;

//@Controller
@RequestMapping("/hello")
public class HelloController {

	//@Autowired
	FormServiceImpl formServiceImpl;

	//@Autowired
	AttachementDao attachementDao;
	
	//@Autowired
	FormDao formDao;
	
	//@Autowired
	CheckInDaoImpl checkInDaoImpl;

	/**
	 * http://localhost:8080/ManagementSystem/app/hello
	 */
	@GetMapping
	@ResponseBody
	public Form form() throws ParseException {
		return formServiceImpl.addForm();
	}

	/**
	 * http://localhost:8080/ManagementSystem/app/hello/attachment
	 */
	@GetMapping("/attachment")
	@ResponseBody
	public int[] attachement() throws ParseException {
		List<Attachement> attachements = Arrays.asList(
				new Attachement("0ee91d5b-d4ad-42dd-bc90-ea32d7bda4d2", "大量測試1.pdf", new Date(), new Date()),
				new Attachement("0ee91d5b-d4ad-42dd-bc90-ea32d7bda4d2", "大量測試2.pdf", new Date(), new Date()));
		int[] addBatchResult = attachementDao.addBatchAttachements(attachements);
		System.out.println("大量新增結果 " + Arrays.toString(addBatchResult));
		return addBatchResult;
	}
	
	/**
	 * Optional<EmpBook> findEmpBookByFormId(String formId)
	 * http://localhost:8080/ManagementSystem/app/hello/findEmpBookByFormId
	 */
	@GetMapping("/findEmpBookByFormId")
	@ResponseBody
	public EmpBook findEmpBookByFormId() throws ParseException {
		return formDao.findEmpBookByFormId("0ee91d5b-d4ad-42dd-bc90-ea32d7bda4d2").get();
	}
	
	
	/**
	 * List<CheckIn> findAllCheckInByEmpIdAndStartDateAndEndDate(Integer empId, Date startDate, Date endDate)
	 * http://localhost:8080/ManagementSystem/app/hello/findCheckIn?empId=101&startDate=2024-01-18%2015:40:41&endDate=2024-01-19%2015:40:41
	 * http://localhost:8080/ManagementSystem/app/hello/findCheckIn?empId=101&startDate=2024-01-20%2015:40:41&endDate=2024-01-21%2015:40:41
	 * 
	 * @DateTimeFormat: 從 字串 轉 Date類型 的 格式(pattern)定義 (yyyy-MM-dd HH:mm:ss)
	 */
	@GetMapping("/findCheckIn")
	@ResponseBody
	public List<CheckIn> findAllCheckInByEmpIdAndStartDateAndEndDate(
			@RequestParam("empId") Integer empId,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate
	) throws ParseException {
		//return checkInDaoImpl.findAllCheckInByEmpIdAndStartDateAndEndDate(checkInSearch.getEmpId(), checkInSearch.getStartDate(), checkInSearch.getEndDate());
		return checkInDaoImpl.findAllCheckInByEmpIdAndStartDateAndEndDate(empId, startDate, endDate);
	}
	

}
