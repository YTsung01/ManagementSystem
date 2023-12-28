package com.example.controller;

import java.util.Base64;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.dao.CheckInDao;







@Controller
@RequestMapping("/checkin")
public class checkInController {
	
	@Autowired
	private CheckInDao checkInDao;
	
	/**

	 * 打卡紀錄
	 +-------+---------+--------+---------------+--------+---------------------+
	 | empId | empName | empSex | empDepartment | empJob |      CheckInTime    |
	 +-------+---------+--------+---------------+--------+---------------------+
	 |  101  |  Solar  | Female |      sale     |Engineer|'2023-12-12 16:12:39'|
	 |  102  | MoonByul|  male  |      sale     |Engineer|'2023-12-12 16:12:39'|
	 |  103  |  WheeIn | Female |      sale     |Engineer|'2023-12-12 16:12:39'|
	 |  104  |  Hwasa  |  male  |      sale     |Engineer|'2023-12-12 16:12:39'|
	 +-------+---------+--------+---------------+--------+---------------------+
	*/
	
	
	// 打卡首頁
		@GetMapping(value = {"/checkin", "/", "/checkin/"})
		public String checkinPage(HttpSession session) {
			return "emp/CheckIn";
		}
		
		

}
