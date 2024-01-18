package com.example.controller;

import java.io.IOException;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.example.model.entity.OverTimeTypeData;
import com.example.model.entity.OverTimeTypeForDayData;
import com.example.util.Qrcode;

import com.google.zxing.WriterException;


@Controller
@RequestMapping("/overtime")
public class OverTimeController {
	
	@Autowired
	private OverTimeDAO overTimeDAO;
	
	@Autowired
	private OverTimeTypeDataDAO overTimeTypeDataDAO;
	
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
		
		Integer empId= employee.getEmpId();
		model.addAttribute("overTimesbyId", overTimeDAO.findNoneCheckoutOverTimeHourByEmpId(empId));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("overTimeDate", sdf.format(new Date()));
		
		 //把basedata傳給jsp
        List<OverTimeTypeData> overTimeTypeDatas = overTimeTypeDataDAO.findAllOverTimeTypeDatas();
        List<OverTimeTypeForDayData> overTimeTypeForDayDatas = overTimeTypeDataDAO.findAllOverTimeTypeForSalaryDatas();
        model.addAttribute("overTimeTypeForDayDatas",overTimeTypeForDayDatas);
        model.addAttribute("overTimeTypeDatas",overTimeTypeDatas);
    
		
		return "emp/OvertimeRequest";
	
	}
	
	// 加班申請-表單接收並將申請單存入資料庫
	@PostMapping("/add/{empId}")
	@ResponseBody
    public String addOverTime(@RequestParam Map<String, Object> formMap, Model model, HttpSession session) throws ParseException, WriterException, IOException {
		/* 
		 * {
		 * overTimeTypeId=1, overTimeTypeForDayId=1, overTimeStart=2024-01-17T15:36,
		 * overTimeEnd=2024-01-17T19:36, overTimeHour=4, overTimeReason=1212}
		 *  
		 *  OverTime [
		 *  overTimeFormId=null, 
		 *  overTimeDate=null, 
		 *  empId=101, 
		 *  empName=Solar, 
		 *  empDepartment=Sales, 
		 *  empDeptno=null, 
		 *  empJob=null, 
		 *  overTimeStart=Wed Jan 17 16:46:00 CST 2024, 
		 *  overTimeEnd=Wed Jan 17 18:46:00 CST 2024, 
		 *  overTimeHour=2, 
		 *  overTimeLeftHour=null, 
		 *  overTimeType=null, 
		 *  overTimeTypeId=1, 
		 *  overTimeTypeForDay=null, 
		 *  overTimeTypeForDayId=1,
		 *  overTimeReason=212, 
		 *  verifyState=null,
		 *  overTimeCheckReason=null, 
		 *  employee=null]
		 */
		// 
		
		// 產生 QR CODE
		String uuid = UUID.randomUUID().toString();
		String path = Qrcode.generateQRcode(uuid);
		
		
		
		Employee employee = (Employee)session.getAttribute("employee");
		
		OverTime overTime = new OverTime();
		OverTimeTypeData overTimeTypeData = new OverTimeTypeData();
		
        // 表單參數注入加班資料
        overTime.setEmpId(employee.getEmpId());
        overTime.setEmpName(employee.getEmpName());
        overTime.setEmpDepartment(employee.getEmpDepartment());
        overTime.setEmpDeptno(employee.getEmpDeptno());
        overTime.setEmpJob(employee.getEmpJob());
        overTime.setEmployee(employee);
      
        try {
        	Date overTimeStart = sdf.parse(formMap.get("overTimeStart") + "");
            overTime.setOverTimeStart(overTimeStart);
            
            Date overTimeEnd = sdf.parse(formMap.get("overTimeEnd") + "");
            overTime.setOverTimeEnd(overTimeEnd);
            	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        Integer overTimeHour = Integer.parseInt(formMap.get("overTimeHour") + "");
        overTime.setOverTimeHour(overTimeHour);
        
        
        String overTimeType = formMap.get("overTimeTypeId") + "";
        overTimeTypeData.setName(overTimeType);
        
        String overTimeTypeForDay = formMap.get("overTimeTypeForDayId") + "";
        overTimeTypeData.setName(overTimeTypeForDay);
        
        
        Integer overTimeTypeId = Integer.parseInt(formMap.get("overTimeTypeId") + "");
        overTime.setOverTimeTypeId(overTimeTypeId);
        
        Integer overTimeTypeForDayId = Integer.parseInt(formMap.get("overTimeTypeForDayId") + "");
        overTime.setOverTimeTypeForDayId(overTimeTypeForDayId);
        
        String overTimeReason = formMap.get("overTimeReason") + "";
        overTime.setOverTimeReason(overTimeReason);
        
        
        overTime.setOverTimeFormId(uuid.toString());
        
       
        Date OverTimeDate = sdf.parse(sdf.format(new Date()));
        overTime.setOverTimeDate(OverTimeDate);
        model.addAttribute("overTimeDate",OverTimeDate);
        
        
        String overTimeLeftHourString = formMap.get("OverTimeLeftHour") + "";
        Integer overTimeLeftHour = null;

        if (!"null".equals(overTimeLeftHourString)) {
            overTimeLeftHour = Integer.parseInt(overTimeLeftHourString);
        }
        
        
     
        
        Integer VerifyState = 2;
        overTime.setVerifyState(VerifyState);
        
        
        String OverTimeCheckReason = "待審核";
        overTime.setOverTimeCheckReason(OverTimeCheckReason);
        
       
        overTimeDAO.addOverTime(overTime); 
        model.addAttribute("overtime",overTime);
        
       return formMap + "<hr>" + overTime + "";
        
        //return "redirect:../search/" + employee.getEmpId();
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
		//List<OverTime> overTimes = overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno());
		System.out.println("overTime = " + overTime);
		overTimeDAO.addOverTime(overTime);
		//model.addAttribute("overTimes",overTimeDAO.findOverTimeHourByEmpId(employee.getEmpId()));
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