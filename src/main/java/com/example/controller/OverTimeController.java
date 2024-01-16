package com.example.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.dao.OverTimeDAO;
import com.example.model.entity.CheckIn;
import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;






@Controller
@RequestMapping("/overtime")
public class OverTimeController {
	
	@Autowired
	private OverTimeDAO overTimeDAO;
	
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
		public String checkinPage(Model model) {
			return "emp/OvertimeRequest";
		}
	
	// 加班申請
	@GetMapping(value = { "/request" })
		public String overtimeRequestPage(Model model, OverTime overTime, HttpSession session) {
			// 取得登入者的資訊
			Employee employee = (Employee)session.getAttribute("employee");
			System.out.println("overTime = " + overTime);
			overTimeDAO.addOverTime(overTime);
			Integer deptNo= employee.getEmpDeptno();
			model.addAttribute("overTimes",overTimeDAO.findAllOverTimeByDeptNo(deptNo));
			return "emp/OvertimeRequest";
		}
	
	// 加班查詢
	@GetMapping(value = "/search/{empId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
		public String overtimeSearchPage(@PathVariable("empId") Integer empId,Model model, OverTime overTime) {
			List<OverTime> overTimes = overTimeDAO.findOverTimeByEmpId(empId);
			System.out.println("overTime = " + overTime);
			overTimeDAO.addOverTime(overTime);
			model.addAttribute("overTimes",overTimeDAO.findOverTimeByEmpId(empId));
			return "emp/OvertimeRequest";
		}
	
	// 加班查詢
	@GetMapping(value = "/search", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String overtimeSearchPage(Model model, OverTime overTime, HttpSession session) {
		// 取得登入者的資訊
		Employee employee = (Employee)session.getAttribute("employee");
		List<OverTime> overTimes = overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno());
		System.out.println("overTime = " + overTime);
		overTimeDAO.addOverTime(overTime);
		model.addAttribute("overTimes",overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno()));
		return "emp/OvertimeRequest";
	}
	
	// 加班管理
	@GetMapping(value = { "/check" })
	public String overtimeCheckPage(Model model, HttpSession session) {
		// 取得登入者的資訊
		Employee employee = (Employee)session.getAttribute("employee");
		List<OverTime> overTimes = overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno());
		model.addAttribute("overTimes",overTimes);
		return "emp/Overtimecheck";
	}
	

		
		
		

}