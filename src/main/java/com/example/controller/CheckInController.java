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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.dao.CheckInDao;
import com.example.entity.CheckIn;
import com.example.entity.EmpBook;


@Controller
@RequestMapping("/checkin")
public class CheckInController {
	
	@Autowired
	private CheckInDao checkInDao;
	
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
		EmpBook empBook = (EmpBook)session.getAttribute("employee");
			Integer empId = empBook.getEmpId();
			model.addAttribute("checkInList",checkInDao.findAllCheckInByEmpId(empId));
			return "emp/CheckIn";
		}
		
		
	//新增打卡資料	
	@PostMapping(value = {"/add/{empId}"} , produces = "text/plain;charset=utf-8")
		//@ResponseBody
		public String addcheckIn(@ModelAttribute CheckIn checkIn, Model model, HttpSession session  ) throws ParseException {
			
			// 取得登入者資料
			EmpBook empBook = (EmpBook) session.getAttribute("employee");

			
			checkIn.setEmpId(empBook.getEmpId());
			checkInDao.addCheckIn(checkIn);
			model.addAttribute("checkIn", checkIn);
			model.addAttribute("checkInTime", checkIn.getCheckInTime());
			model.addAttribute("checkOutTime", checkIn.getCheckOutTime());

			// 新增打卡時間

			return checkIn + "";
			/*
			 * / 新增打卡紀錄 (rowcount 資料表異動筆數) try { int rowcount =
			 * checkInDao.addCheckIn(checkInList); if(rowcount == 0) { return "打卡失敗"; } else
			 * { return "打卡成功"; } } catch (Exception e) { return "打卡失敗: " +
			 * (e.getMessage().contains("Duplicate entry") ? "已打卡" : e.getMessage()); }
			 */
		}
}