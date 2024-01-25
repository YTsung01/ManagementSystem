package com.example.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/*
import com.example.model.dao.OverTimeDAO;
import com.example.model.dao.OverTimeTypeDataDAO;
import com.example.model.entity.Employee;
import com.example.model.entity.OverTime;
import com.example.model.entity.OverTimeTypeData;
import com.example.model.entity.OverTimeTypeForDayData;
import com.example.util.Qrcode;

import com.google.zxing.WriterException;


//@Controller
//@RequestMapping("/overtime")
public class LeaveController {
	
	//@Autowired
	private OverTimeDAO overTimeDAO;
	
	//@Autowired
	private OverTimeTypeDataDAO overTimeTypeDataDAO;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	// 加班申請頁
	@GetMapping(value = { "/request" })
	public String overtimeRequestPage(Model model, HttpSession session, 
			@ModelAttribute OverTime overTime ,@ModelAttribute OverTimeTypeData overTimeTypeData) throws WriterException, IOException {
		// 取得登入者的資訊
		Employee employee = (Employee)session.getAttribute("employee");
		//System.out.println("overTime = " + overTime);
		//overTimeDAO.addOverTime(overTime);
		Integer deptNo= employee.getEmpDeptno();
		model.addAttribute("overTimes", overTimeDAO.findAllOverTimeByDeptNo(deptNo));
		
		
		//計算目前以審核的總加班時數	
		Integer empId = employee.getEmpId();
		model.addAttribute("overTimesbyId", overTimeDAO.findCheckoutOverTimeHourByEmpId(empId));
		
//		//計算目前已審核的總加班時數	
//		List<OverTime> calculateOverTimeHourList = overTimeDAO.findCheckoutOverTimeHourByEmpId(empId);
//		int totalOvertimeHour = calculateOverTimeHourList.stream().mapToInt(OverTime::getOverTimeHour).sum();
//		model.addAttribute("totalOvertimeHour", totalOvertimeHour);
//		
//		//計算目前所剩下的加班時數
//		int overIimeLeftHour = 46-totalOvertimeHour;
//		model.addAttribute("overIimeLeftHour", overIimeLeftHour);
//		overTime.setOverTimeLeftHour(overIimeLeftHour);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		model.addAttribute("overTimeDate", sdf.format(new Date()));
//		
//		 //把basedata傳給jsp
//        List<OverTimeTypeData> overTimeTypeDatas = overTimeTypeDataDAO.findAllOverTimeTypeDatas();
//        List<OverTimeTypeForDayData> overTimeTypeForDayDatas = overTimeTypeDataDAO.findAllOverTimeTypeForSalaryDatas();
//        model.addAttribute("overTimeTypeForDayDatas",overTimeTypeForDayDatas);
//        model.addAttribute("overTimeTypeDatas",overTimeTypeDatas);
//    
		
		return "emp/OvertimeRequest";
	
	}
	
	// 加班申請-表單接收並將申請單存入資料庫
	@PostMapping("/add/{empId}")
    public String addOverTime(@RequestParam Map<String, Object> formMap, Model model, HttpSession session,RedirectAttributes redirectAttributes) throws ParseException, WriterException, IOException {
		
		// 產生 uuid及Qr code
		String uuid = UUID.randomUUID().toString();
		String path = Qrcode.generateQRcode(uuid);
		
		//取得登入者資料
		
		Employee employee = (Employee)session.getAttribute("employee");
		
		OverTime overTime = new OverTime();
		
        // 表單參數注入加班資料
//        overTime.setEmpId(employee.getEmpId());
//        overTime.setEmpName(employee.getEmpName());
//        overTime.setEmpDepartment(employee.getEmpDepartment());
//        overTime.setEmpDeptno(employee.getEmpDeptno());
//        overTime.setEmpJob(employee.getEmpJob());
//        overTime.setEmployee(employee);
        
      //表單號碼
//       overTime.setOverTimeFormId(uuid.toString());
//        
//        try {
//        	Date overTimeStart = sdf.parse(formMap.get("overTimeStart") + "");
//            overTime.setOverTimeStart(overTimeStart);
//            
//            Date overTimeEnd = sdf.parse(formMap.get("overTimeEnd") + "");
//            overTime.setOverTimeEnd(overTimeEnd);
//            	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        //取得本次申請的加班時數
//        Integer overTimeHour = Integer.parseInt(formMap.get("overTimeHour") + "");
//        overTime.setOverTimeHour(overTimeHour);
        
        取得申請類型的名稱(加班/補修) --沒顯示!
        String overTimeType = formMap.get("overTimeTypeId") + "";
        overTimeTypeData.setName(overTimeType);
      //取得申請類型的名稱(平日加班/假日加班) --沒顯示!
        String overTimeTypeForDay = formMap.get("overTimeTypeForDayId") + "";
        overTimeTypeData.setName(overTimeTypeForDay);
        
        //取得申請類型(加班/補修)
        Integer overTimeTypeId = Integer.parseInt(formMap.get("overTimeTypeId") + "");
        overTime.setOverTimeTypeId(overTimeTypeId);
        //取得申請類型(平日加班/假日加班)
        Integer overTimeTypeForDayId = Integer.parseInt(formMap.get("overTimeTypeForDayId") + "");
        overTime.setOverTimeTypeForDayId(overTimeTypeForDayId);
        //加班申請原因
        String overTimeReason = formMap.get("overTimeReason") + "";
        overTime.setOverTimeReason(overTimeReason);
        
        Date OverTimeDate = sdf.parse(sdf.format(new Date()));
        overTime.setOverTimeDate(OverTimeDate);
        model.addAttribute("overTimeDate",OverTimeDate);
        
        
		// 計算目前已審核的總加班時數
		List<OverTime> calculateOverTimeHourList = overTimeDAO.findCheckoutOverTimeHourByEmpId(employee.getEmpId());
		int totalOvertimeHour = calculateOverTimeHourList.stream().mapToInt(OverTime::getOverTimeHour).sum();
		model.addAttribute("totalOvertimeHour", totalOvertimeHour);
		// 計算提交表單後所剩餘的加班時數
		int overIimeLeftHour = 46 - totalOvertimeHour - overTimeHour;
		model.addAttribute("overIimeLeftHour", overIimeLeftHour);
		overTime.setOverTimeLeftHour(overIimeLeftHour);

		// 送出表單當下審核狀態固定=2(待審核)
		Integer VerifyState = 2;
		overTime.setVerifyState(VerifyState);

		// 送出表單當下審核狀態固定=2(待審核)
		String OverTimeCheckReason = "待審核";
		overTime.setOverTimeCheckReason(OverTimeCheckReason);
        
       //注入資料
        overTimeDAO.addOverTime(overTime); 
        model.addAttribute("overtime",overTime);
        
       //return formMap + "<hr>" + overTime + "";
        redirectAttributes.addAttribute("empId", employee.getEmpId());

        return "redirect:../search/{empId}" ;
        //return "emp/overtime/search/{empId}";
    }
	
	
	// 加班查詢(員工查自己)
	@GetMapping(value = "/search/{empId}")
		public String overtimeSearchPage(@RequestParam Model model, OverTime overTime, HttpSession session) {
			Employee employee = (Employee)session.getAttribute("employee");
		
			List<OverTime> overTimes = overTimeDAO.findOverTimeByEmpId(employee.getEmpId());
			System.out.println("overTime = " + overTimes);
			model.addAttribute("overTimes", overTimeDAO.findOverTimeByEmpId(employee.getEmpId()));
			return "emp/OvertimeSearch";
		}
	
	
	
	// 主管依照加班查詢本部門所有加班資料
	@GetMapping(value = "/search", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String overtimeSearchPageBoss(Model model, OverTime overTime, HttpSession session) {
		
		// 取得登入者的資訊
		Employee employee = (Employee)session.getAttribute("employee");
		//List<OverTime> overTimes = overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno());
		System.out.println("overTime = " + overTime);
		overTimeDAO.addOverTime(overTime);
		//model.addAttribute("overTimes",overTimeDAO.findOverTimeHourByEmpId(employee.getEmpId()));
		return "emp/OvertimeSearch";
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
*/