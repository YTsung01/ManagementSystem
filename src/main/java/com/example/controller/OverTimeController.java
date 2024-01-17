package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.model.dao.OverTimeTypeDataDAO;
import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;

@Controller
@RequestMapping("/overtime")
public class OverTimeController {
	
	@Autowired
	private OverTimeDAO overTimeDAO;
	
	@Autowired
	private OverTimeTypeDataDAO overTimeTypeDataDAO;
	
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
		public String overTimePage(Model model) {
			return "emp/OvertimeRequest";
		}
	
	// 加班申請頁
	@GetMapping(value = { "/request" })
	public String overtimeRequestPage(Model model, HttpSession session) {
		// 取得登入者的資訊
		Employee employee = (Employee)session.getAttribute("employee");
		//System.out.println("overTime = " + overTime);
		//overTimeDAO.addOverTime(overTime);
		Integer deptNo= employee.getEmpDeptno();
		model.addAttribute("overTimes", overTimeDAO.findAllOverTimeByDeptNo(deptNo));
		
		//Timestamp overTimeDate=overTime.getOverTimeDate();
		//model.addAttribute("overTimeDate", overTimeDate + "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("overTimeDate", sdf.format(new Date()));
		return "emp/OvertimeRequest";
	
	}
	
	// 加班申請-表單接收並將申請單存入資料庫
	@PostMapping("/add/{empId}")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> formMap , OverTime overTime) {
	
		
	//建立加班資料
	Map<String, Object> addOverTime = new LinkedHashMap<>();
	addOverTime.put("empId",overTime.getEmpId());
	addOverTime.put("empName",overTime.getEmpName());
	addOverTime.put("empDepartment",overTime.getEmpDepartment());
	addOverTime.put("overTimeTypeId",overTime.getOverTimeTypeId());
	addOverTime.put("overTimeTypeForDayId",overTime.getOverTimeTypeForDayId());
	addOverTime.put("overTimeStart",overTime.getOverTimeStart());
	addOverTime.put("overTimeEnd",overTime.getOverTimeEnd());
	addOverTime.put("overTimeReason",overTime.getOverTimeReason());
	
	//return formMap + "";
	return addOverTime+ "";
	}
	
	
	
	
	// 加班查詢(員工查自己)
	@RequestMapping(value = "/search/{empId}", method = {RequestMethod.GET, RequestMethod.POST})
		public String overtimeSearchPage(@PathVariable("empId") Integer empId,Model model, OverTime overTime, HttpSession session) {
			
			List<OverTime> overTimes = overTimeDAO.findOverTimeByEmpId(empId);
			System.out.println("overTime = " + overTimes);
			model.addAttribute("overTimes", overTimeDAO.findOverTimeByEmpId(empId));
			return "emp/OvertimeSearch";
		}
	
	
	
	// 加班查詢
	@PostMapping(value = "/search", produces = "text/plain;charset=utf-8")
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