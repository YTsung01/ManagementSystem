package com.example.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.model.dao.oldCheckInDao;
import com.example.model.entity.oldCheckIn;
import com.example.model.entity.oldEmployee;


@Controller
@RequestMapping("/checkin")
public class CheckInController {
	
	@Autowired
	private oldCheckInDao checkInDao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	
	/**

	 * 打卡紀錄
	 +-------+---------+---------------+--------+---------------------+
	 | empId | empName | empDepartment | empJob |      CheckInTime    |
	 +-------+---------+---------------+--------+---------------------+
	 |  101  |  Solar  |      sale     |Engineer|'2023-12-12 16:12:39'|
	 |  102  | MoonByul|      sale     |Engineer|'2023-12-12 16:12:39'|
	 |  103  |  WheeIn |      sale     |Engineer|'2023-12-12 16:12:39'|
	 |  104  |  Hwasa  |      sale     |Engineer|'2023-12-12 16:12:39'|
	 +-------+---------+---------------+--------+---------------------+
	*/
	
	
	// 打卡首頁
	@GetMapping(value = { "/" })
		public String checkinPage(Model model, HttpSession session) {
			oldEmployee employee = (oldEmployee)session.getAttribute("employee");
			Integer empId = employee.getEmpId();
			model.addAttribute("checkInList",checkInDao.findAllCheckInByEmpId(empId));
			return "emp/CheckIn";
		}
		
		
	//新增打卡資料	
	@PostMapping(value = {"/add/{empId}"} , produces = "text/plain;charset=utf-8")
		//@ResponseBody
		public String addcheckIn(@RequestParam Map<String, Object> formMap, Model model, HttpSession session  ) throws ParseException {
			
			//取得登入者資料
			oldEmployee employee = (oldEmployee)session.getAttribute("employee");
			
			
			
			// 將表單參數逐一注入到 checkInList 物件中
			oldCheckIn checkInList= new oldCheckIn();
			checkInList.setEmpId(employee.getEmpId());
			checkInList.setEmpName(employee.getEmpName());
			checkInList.setEmpDepartment(employee.getEmpDepartment());
			checkInList.setEmpJob(employee.getEmpJob());
			
			//新增打卡時間
			 
		    try {
	        	Date checkInTime = sdf.parse(formMap.get("checkInTime") + "");
	        	checkInList.setCheckInTime(checkInTime);
	            
	            Date checkOutTime = sdf.parse(formMap.get("overTimeEnd") + "");
	            checkInList.setCheckOutTime(checkOutTime);
	            	
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		    //取得今日時間
			
		    Date todayDate = sdf.parse(sdf.format(new Date()));
		    checkInList.setTodayDate(todayDate);
	        model.addAttribute("todayDate",todayDate);
			
	        return formMap + "<hr>" + checkInList + "";
			/*/ 新增打卡紀錄 (rowcount 資料表異動筆數)
			try {
				int rowcount = checkInDao.addCheckIn(checkInList);
				if(rowcount == 0) {
					return "打卡失敗";
				} else {
					return "打卡成功";
				}
			} catch (Exception e) {
				return "打卡失敗: " + (e.getMessage().contains("Duplicate entry") ? "已打卡" : e.getMessage());
			}*/
		
		}
		
	 
	

		

}