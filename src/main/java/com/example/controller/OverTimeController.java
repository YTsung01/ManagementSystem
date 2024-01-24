package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dao.EmpBookDao;
import com.example.dao.FormDao;
import com.example.dao.OverTimeDao;
import com.example.entity.CheckIn;
import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.entity.OverTime;

@Controller
@RequestMapping("/overtime")
public class OverTimeController {

	@Autowired
	private OverTimeDao overTimeDao;

	@Autowired
	FormDao formDao;
	
	@Autowired
	EmpBookDao empBookDao;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	// 加班首頁
	@GetMapping(value = { "/request" })
	public String OverTimeRequestPage(Model model, HttpSession session, @ModelAttribute OverTime overTime) {
		// 取得登入者的資訊
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		Integer deptNo = empBook.getEmpDeptno();
		// 取得所有的
		model.addAttribute("overTimes", overTimeDao.findAllOverTimeByDeptNo(deptNo));

		// 計算目前已審核的總加班時數
		Integer empId = empBook.getEmpId();
		List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
		model.addAttribute("overTimesbyId", calculateOverTimeHourList);
		int totalOvertimeHour = calculateOverTimeHourList.stream().mapToInt(OverTime::getApplyHour).sum();
		model.addAttribute("totalOvertimeHour", totalOvertimeHour);

		// 填表日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("overTimeDate", sdf.format(new Date()));

		// 計算目前所剩下的加班時數
		int overTimeLeftHour = empBook.getOverTimeLeftHour() - totalOvertimeHour;
		model.addAttribute("overTimeLeftHour", overTimeLeftHour);
		empBook.setOverTimeLeftHour(overTimeLeftHour);

		return "emp/OvertimeRequest";

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("/add/{empId}")
    public String addOverTime(@RequestParam Map<String, Object> formMap, Model model, HttpSession session,RedirectAttributes redirectAttributes) throws ParseException{
			
			//取得登入者資訊
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			//利用uuid產生formId
			String uuid = UUID.randomUUID().toString();
			
			Form form = new Form();
			form.setApplier(empBook.getEmpId());
			form.setFormId(uuid);
			form.setType(2); 	//加班表單固定type是2
			form.setApplyDate(new Date());

			formDao.addForm(form);

			
			OverTime overTime = new OverTime();
			overTime.setFormId(uuid);
			
			
			//從表單取得加班資料
		
	        	Date startTime = sdf.parse(formMap.get("startTime") + "");
	            overTime.setStartTime(startTime);
	            
	            Date endTime = sdf.parse(formMap.get("endTime") + "");
	            overTime.setEndTime(endTime);
	      
			
			Integer applyHour = Integer.parseInt(formMap.get("applyHour") + "");
	        overTime.setApplyHour(applyHour);
	        
	        String reason = formMap.get("reason") + "";
	        overTime.setReason(reason);
	        
	        Integer dayOrHoilday = Integer.parseInt(formMap.get("dayOrHoilday") + "");
	        overTime.setDayOrHoilday(dayOrHoilday);
	        
	        Integer overtimeType = Integer.parseInt(formMap.get("overtimeType") + "");
	        overTime.setOvertimeType(overtimeType);
			overTimeDao.addOverTime(overTime);
		    model.addAttribute("overtime",overTime);
		        
			return  "redirect:../search/{empId}";
			//return formMap +  "<hr>"+ overTime+"";
	
}
	
	// 加班查詢資料(員工查自己)
	@GetMapping(value = "/search/{empId}")
		public String overtimeSearchPage(Model model, OverTime overTime, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到自己所有的加班紀錄
			List<OverTime> OverTimeList = overTimeDao.findAllOverTimeByEmpId(empBook.getEmpId());
			Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
			model.addAttribute("empBossName", empBossOpt.get().getEmpName());
			System.out.println("overTime = " + OverTimeList);
			model.addAttribute("overTime", OverTimeList);
			
			// 計算目前已審核的總加班時數
			Integer empId = empBook.getEmpId();
			List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
			model.addAttribute("overTimesbyId", calculateOverTimeHourList);
			int totalOvertimeHour = calculateOverTimeHourList.stream().mapToInt(OverTime::getApplyHour).sum();
			model.addAttribute("totalOvertimeHour", totalOvertimeHour);

			// 填表日期

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("overTimeDate", sdf.format(new Date()));

			// 計算目前所剩下的加班時數
			int overTimeLeftHour = empBook.getOverTimeLeftHour() - totalOvertimeHour;
			model.addAttribute("overTimeLeftHour", overTimeLeftHour);
			empBook.setOverTimeLeftHour(overTimeLeftHour);
			
			//尚未審核加班時數
			
			List<OverTime> nonCheckOutOverTimeHourList = overTimeDao.findNonCheckoutOverTimeFormByEmpId(empBook.getEmpId());
			int nonCheckOutOverTimeHour = nonCheckOutOverTimeHourList.stream().mapToInt(OverTime::getApplyHour).sum();
			model.addAttribute("nonCheckOutOverTimeHour", nonCheckOutOverTimeHour);
			return "emp/OvertimeSearch";
		}
	
	// 主管依照加班查詢本部門所有加班資料
	@GetMapping(value = "/search", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String overtimeSearchPageBoss(Model model, OverTime overTime, HttpSession session) {
		
		// 取得登入者的資訊
		EmpBook empBook = (EmpBook)session.getAttribute("employee");
		//List<OverTime> overTimes = overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno());
		System.out.println("overTime = " + overTime);
		
		//model.addAttribute("overTimes",overTimeDAO.findOverTimeHourByEmpId(employee.getEmpId()));
		return "emp/OvertimeSearch";
	}
	
	// 搜尋功能
	@GetMapping(value = { "/searchOvertime" })
	public String searchOvertime(@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate, Model model, HttpSession session)
			throws ParseException {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 處理搜索邏輯，selectedMonth 是前端傳遞過來的月份參數
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date StartDate2 = sdf.parse(startDate);
		Date EndDate2 = sdf.parse(endDate);

		if (startDate == null || endDate == null) {
			// 如果 startDate 或 endDate 为空值，返回错误信息
			model.addAttribute("error", "請選擇日期");
			return "emp/CheckInResult"; // 创建一个专门用于显示错误信息的页面，也可以直接返回原页面并在前端显示错误信息
		}
		List<OverTime> filteredOverTimes = overTimeDao.findAllOverTimeByEmpIdAndStartDateAndEndDate(empBook.getEmpId(),
				StartDate2, EndDate2);
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		// 將結果傳遞到 JSP 中
		model.addAttribute("OverTimefilter", filteredOverTimes);
		return "emp/OvertimeSearchDetail";
	}
	//每筆紀錄的詳情頁
	@GetMapping(value = { "/deatil/{fomrId}" })
	public String searchOverTimeDetail(Model model, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		
	
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		// 將結果傳遞到 JSP 中
		//model.addAttribute("OverTimefilter", filteredOverTimes);
		return "emp/OverTimeResult";
	}
	
	
	

}
