package com.example.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.dao.CheckInDao;

import bean.CheckIn;







@Controller
@RequestMapping("/checkin")
public class CheckInController {
	
	@Autowired
	@Qualifier("checkin")
	private CheckInDao checkInDao;
	
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
		@GetMapping ("/")
		public String checkinPage(Model model) {
			List<CheckIn> checkIn =checkInDao.findAllCheckIn();
			model.addAttribute("checkIn",checkIn);
			return "emp/CheckIn";
		}
		
		
		/** 1.打卡上班：
		 * 路徑：/bookingMySQL/bookRoom
		 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
		 * 返回：預訂成功(會得到預約號碼 bookingId)或失敗的消息
		 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/bookRoom?roomId=101&name=Tom&date=2023-12-04
		 * @throws ParseException 
		*/
		
		/*
		@RequestMapping(value = {"/checkinpage"} ,method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/plain;charset=utf-8")
		@ResponseBody
		public String checkIn(@RequestParam( name="empId") Integer empId,
							  @RequestParam(name="empName") String empName,
							  @RequestParam(name="empDepartment") String empDepartment,
							  @RequestParam(name="empJob") String empJob,
							  @RequestParam(name="date") String date) throws ParseException {
			
			// 將表單參數逐一注入到 checkInList 物件中
			CheckIn checkInList= new CheckIn();
			checkInList.setEmpId(empId);
			checkInList.setEmpName(empName);
			checkInList.setEmpDepartment(empDepartment);
			checkInList.setEmpJob(empJob);
			checkInList.setCheckInTime(date);
					
			
		
			// 新增預約資料紀錄 (rowcount 資料表異動筆數)
			try {
				int rowcount = checkInDao.addCheckIn(checkInList);
				if(rowcount == 0) {
					return "打卡失敗";
				} else {
					return "打卡成功";
				}
			} catch (Exception e) {
				return "打卡失敗: " + (e.getMessage().contains("Duplicate entry") ? "已打卡" : e.getMessage());
			}
		
		}
		*/
	
		
		

}