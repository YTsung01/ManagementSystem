package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.dao.CheckInDao;





@Controller
@RequestMapping("/CheckIn")
public class CheckInController {
	
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

}
