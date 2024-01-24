package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.example.dao.TakeOffDao;
import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.entity.TakeOff;

@Controller
@RequestMapping("/takeOff")
public class TakeOffController {

	@Autowired
	private TakeOffDao takeOffDao;
	
	@Autowired
	private EmpBookDao empBookDao;

	@Autowired
	FormDao formDao;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	// 請假首頁
	@GetMapping(value = { "/request" })
	public String TakeOffRequestPage(Model model, HttpSession session, @ModelAttribute TakeOff takeOff) {
		// 取得登入者的資訊
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		Integer deptNo = empBook.getEmpDeptno();
		
		// 取得所有的
		model.addAttribute("takeOffs", takeOffDao.findAllTakeOffByDeptNo(deptNo));

		// 計算目前已審核的總請假時數
		Integer empId = empBook.getEmpId();
		List<TakeOff> calculateTakeOffHourList = takeOffDao.findCheckoutTakeOffByEmpId(empId);
		model.addAttribute("TakeOffsbyId", calculateTakeOffHourList);
		int sumTakeOffHour = calculateTakeOffHourList.stream().mapToInt(TakeOff::getTakeoffHour).sum();
		model.addAttribute("totalTakeOffHour", sumTakeOffHour);

		// 填表日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("takeOffDate", sdf.format(new Date()));

		// 計算目前所剩下的請假時數
		Integer takeoffTotalHours = empBook.getTakeoffTotalHours();
		int takeOffLeftHour = takeoffTotalHours -sumTakeOffHour;
		model.addAttribute("takeOffLeftHour", takeOffLeftHour);
		
		// 申請人(只能幫同部門的人申請)
		List<EmpBook> allDeptEmp= empBookDao.findEmpBooksByEmpDeptNo(empBook.getEmpDeptno());
		model.addAttribute("allDeptEmp", allDeptEmp);
		System.out.print(allDeptEmp);
		
		
		return "emp/TakeOffRequest";
		
		

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("/add/{empId}")
    public String addTakeOff(@RequestParam Map<String, Object> formMap, Model model, HttpSession session,RedirectAttributes redirectAttributes) throws ParseException{
			
			//取得登入者資訊
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			//利用uuid產生formId
			String uuid = UUID.randomUUID().toString();
			
			Form form = new Form();
			form.setApplier(empBook.getEmpId());
			form.setFormId(uuid);
			form.setType(1); 	//請假表單固定type是2
			form.setApplyDate(new Date());

			formDao.addForm(form);

			TakeOff takeOff=new TakeOff();
			takeOff.setFormId(uuid);
			
			
			
			//從表單取得請假資料
		
	        Date startTime = sdf.parse(formMap.get("startTime") + "");
	        takeOff.setStartTime(startTime);
	           
	        Date endTime = sdf.parse(formMap.get("endTime") + "");
	        takeOff.setEndTime(endTime);
	      
			Integer takeoffHour = Integer.parseInt(formMap.get("takeoffHour") + "");
			takeOff.setTakeoffHour(takeoffHour);
	
	        
	        String reason = formMap.get("reason") + "";
	        takeOff.setReason(reason);
	        
	        Integer takeoffType= Integer.parseInt(formMap.get("takeoffType") + ""); 
	        takeOff.setTakeoffType(takeoffType);
	       
	        takeOffDao.addTakeOff(takeOff);
	        model.addAttribute("takeOff",takeOff);
	        
	      
			return  "redirect:../search/{empId}";
	
}
	
	// 加班查詢(員工查自己)
	@GetMapping(value = "/search/{empId}")
		public String takeoffSearchPage(Model model,TakeOff takeOff, HttpSession session) {
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		
			List<TakeOff> takeOffs = takeOffDao.findCheckoutTakeOffByEmpId(empBook.getEmpId());
			System.out.println("takeOff = " + takeOffs);
			model.addAttribute("takeOffs", takeOffs);
			return "emp/TakeOffSearch";
		}
	
	// 主管依照加班查詢本部門所有加班資料
	@GetMapping(value = "/search", produces = "text/plain;charset=utf-8")
	//@ResponseBody
	public String overtimeSearchPageBoss(Model model, TakeOff takeOff, HttpSession session) {
		
		// 取得登入者的資訊
		EmpBook empBook = (EmpBook)session.getAttribute("employee");
		//List<OverTime> overTimes = overTimeDAO.findAllOverTimeByDeptNo(employee.getEmpDeptno());
		System.out.println("takeOff = " + takeOff);
		
		//model.addAttribute("overTimes",overTimeDAO.findOverTimeHourByEmpId(employee.getEmpId()));
		return "emp/TakeOffSearch";
	}
	
	

}
