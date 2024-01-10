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
import com.example.model.entity.CheckIn;









@Controller
@RequestMapping("/overtime")
public class OverTimeController {
	
	@Autowired
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
	
	
	// 加班申請
	@GetMapping(value = { "/request" })
		public String overtimeRequestPage(Model model) {
			List<CheckIn> checkIn =checkInDao.findAllCheckIn();
			model.addAttribute("checkIn",checkIn);
			return "emp/OvertimeRequest";
		}
	
	// 加班查詢
	@GetMapping(value = { "/search" })
		public String overtimeSearchPage(Model model) {
			List<CheckIn> checkIn =checkInDao.findAllCheckIn();
			model.addAttribute("checkIn",checkIn);
			return "emp/OvertimeSearch";
		}
	
	// 加班管理
	@GetMapping(value = { "/check" })
		public String overtimeCheckPage(Model model) {
			List<CheckIn> checkIn =checkInDao.findAllCheckIn();
			model.addAttribute("checkIn",checkIn);
			return "boss/OvertimeCheck";
		}
	

		
		
		

}