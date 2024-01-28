package com.example.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dao.AttachementDao;
import com.example.dao.EmpBookDao;
import com.example.dao.FormDao;
import com.example.dao.TakeOffDao;
import com.example.entity.Attachement;
import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.entity.TakeOff;

@Controller
@RequestMapping("/takeOff")
public class TakeOffController {

	@Autowired
	TakeOffDao takeOffDao;

	@Autowired
	EmpBookDao empBookDao;

	@Autowired
	FormDao formDao;
	
	@Autowired
	AttachementDao attachementDao;

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
		int takeOffLeftHour = takeoffTotalHours - sumTakeOffHour;
		model.addAttribute("takeOffLeftHour", takeOffLeftHour);

		// 申請人(只能幫同部門的人申請)
		List<EmpBook> allDeptEmp = empBookDao.findEmpBooksByEmpDeptNo(empBook.getEmpDeptno());
		model.addAttribute("allDeptEmp", allDeptEmp);
		System.out.print(allDeptEmp);

		return "emp/TakeOffRequest";
		

	}
	
	

	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("/add/{empId}")
	@ResponseBody
	public String addTakeOff(@RequestParam Map<String, Object> formMap,
			@RequestParam("applierName") Integer applierId, @RequestParam("agentName") Integer agentId,
							Model model, HttpSession session,
							RedirectAttributes redirectAttributes)   throws ParseException, IllegalStateException, IOException {

		
		 System.out.println("前端傳來的 selectedEmployee 值為: "  );
		// 取得登入者資訊
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 利用uuid產生formId
		String uuid = UUID.randomUUID().toString();

		Form form = new Form();
		form.setApplier(applierId);
		form.setFormId(uuid);
		form.setType(1); // 請假表單固定type是1
		form.setApplyDate(new Date());
		System.out.println(applierId);
		System.out.println(agentId);

		formDao.addForm(form);

		TakeOff takeOff = new TakeOff();
		takeOff.setFormId(uuid);

		// 從表單取得請假資料
		Integer applier = Integer.parseInt(formMap.get("applier") + "");
		form.setApplier(applier);
		
		Integer agent = Integer.parseInt(formMap.get("agent") + "");
		takeOff.setAgent(agent);
		
		Integer takeoffType = Integer.parseInt(formMap.get("takeoffType") + "");
		takeOff.setTakeoffType(takeoffType);

		Date startTime = sdf.parse(formMap.get("startTime") + "");
		takeOff.setStartTime(startTime);

		Date endTime = sdf.parse(formMap.get("endTime") + "");
		takeOff.setEndTime(endTime);
		System.out.println(formMap);
		
		
		Integer takeoffHour = Integer.parseInt(formMap.get("takeoffHour") + "");
		takeOff.setTakeoffHour(takeoffHour);

		String reason = formMap.get("reason") + "";
		takeOff.setReason(reason);



		takeOffDao.addTakeOff(takeOff);
		model.addAttribute("takeOff", takeOff);
		
		Attachement attachement = new Attachement();
		
		
		
		
		
		
		return formMap + "<hr>" + takeOff;
	//	return "redirect:../search/{empId}";

	}
	// 加班查詢資料(員工查自己)
	@GetMapping(value = "/search/{empId}")
	public String takeoffSearchPage(Model model, HttpSession session, @ModelAttribute TakeOff takeOff) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到自己所有的請假紀錄
		List<TakeOff> takeOffs = takeOffDao.findCheckoutTakeOffByEmpId(empBook.getEmpId());
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		System.out.println("takeOff = " + takeOffs);
		model.addAttribute("takeOff", takeOffs);

		// 計算目前已審核的總請假時數
		Integer empId = empBook.getEmpId();
		List<TakeOff> calculateTakeOffHourList = takeOffDao.findCheckoutTakeOffByEmpId(empId);
		model.addAttribute("TakeOffsbyId", calculateTakeOffHourList);
		int sumTakeOffHour = calculateTakeOffHourList.stream().mapToInt(TakeOff::getTakeoffHour).sum();
		model.addAttribute("totalTakeOffHour", sumTakeOffHour);

		// 填表日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("overTimeDate", sdf.format(new Date()));

		// 計算目前所剩下的請假時數
		Integer takeoffTotalHours = empBook.getTakeoffTotalHours();
		int takeOffLeftHour = takeoffTotalHours - sumTakeOffHour;
		model.addAttribute("takeOffLeftHour", takeOffLeftHour);
		empBook.setTakeoffTotalHours(takeoffTotalHours);

		// 尚未審核請假時數
		List<TakeOff> nonCheckOutTakeOffList = takeOffDao.findNonCheckoutTakeOffFormByEmpId(empBook.getEmpId());
		int nonCheckOutTakeOffHour = nonCheckOutTakeOffList.stream().mapToInt(TakeOff::getTakeoffHour).sum();
		model.addAttribute("nonCheckOutTakeOffHour", nonCheckOutTakeOffHour);
		return "emp/TakeOffSearch";
	}

	// 搜尋功能
	@GetMapping(value = { "/searchTakeOff" })
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
			return "emp/overTimeResult";
		}
		
		List<TakeOff> filteredTakeOffs = takeOffDao.findAllTakeOffByEmpIdAndStartDateAndEndDate(empBook.getEmpId(),
				StartDate2, EndDate2);
		System.out.println("filteredTakeOffs = " + filteredTakeOffs);
		System.out.println("StartDate2 = " + StartDate2);
		System.out.println("EndDate2 = " + EndDate2);
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		// 將結果傳遞到 JSP 中
		model.addAttribute("TakeOfffilter", filteredTakeOffs);
		return "emp/TakeOffSearchDetail";
	}
	
	// 每筆紀錄的詳情頁
		@GetMapping(value = { "/deatil/{fomrId}" })
		public String searchOverTimeDetail(@PathVariable("fomrId") String fomrId, Model model, HttpSession session) {
			// 取得登入者資料
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			TakeOff takeOff = takeOffDao.findTakeOffByFormId(fomrId).get();
			Form form = formDao.findFormByFormId(fomrId).get();

			if (takeOff.getTakeoffType() == 1) {
				model.addAttribute("takeOfftype", "特休");
			}

			if (takeOff.getTakeoffType() == 2) {
				model.addAttribute("takeOfftype", "事假");
			}
			
			if (takeOff.getTakeoffType() == 3) {
				model.addAttribute("takeOfftype", "病假");
			}
			
			if (takeOff.getTakeoffType() == 4) {
				model.addAttribute("takeOfftype", "喪假");
			}
			if (takeOff.getTakeoffType() == 5) {
				model.addAttribute("takeOfftype", "公假");
			}

			model.addAttribute("takeOff", takeOff);
			model.addAttribute("form", form);
			Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
			model.addAttribute("empBossName", empBossOpt.get().getEmpName());
			return "emp/TakeOffResult";
		}
		
		// 修改 加班表單
		@GetMapping("/show/{formId}")
		public String showOvertime(@PathVariable("formId") String formId, Model model, HttpSession session) {
			// 取得登入者資料
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			TakeOff takeOff = takeOffDao.findTakeOffByFormId(formId).get();
			Form form = formDao.findFormByFormId(formId).get();

			model.addAttribute("takeOff", takeOff);
			model.addAttribute("form", form);
			System.out.println(formId);
			System.out.println(takeOff);
			// 默認startTime
			String updateTime = sdf.format(takeOff.getStartTime());
			String updateendTime = sdf.format(takeOff.getEndTime());
			model.addAttribute("updateTime", updateTime);
			model.addAttribute("updateendTime", updateendTime);
			System.out.println(takeOff.getStartTime());
			System.out.println(takeOff.getEndTime());
			System.out.println(updateTime);
			System.out.println(takeOff.getTakeoffType());
			
			
			// 計算目前已審核的總請假時數
			Integer empId = empBook.getEmpId();
			List<TakeOff> calculateTakeOffHourList = takeOffDao.findNonCheckoutTakeOffFormByEmpId(empId);
			int totalTakeOffHour = calculateTakeOffHourList.stream().mapToInt(TakeOff::getTakeoffHour).sum();
			model.addAttribute("totalTakeOffHour", totalTakeOffHour);

			return "emp/TakeOffRequestUpdate"; // 重導到 user 首頁
		}
		
		// 修改
		@PostMapping("/update/{formId}")
		public String updateTakeOff(@PathVariable("formId") String formId,
				@RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime,
				@ModelAttribute("takeOff") TakeOff takeOff, Model model,
				HttpSession session) throws ParseException {

			EmpBook empBook = (EmpBook) session.getAttribute("empBook");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date startTimeDate = dateFormat.parse(startTime);
			Date endTimeDate = dateFormat.parse(endTime);
			takeOff.setStartTime(startTimeDate);
			takeOff.setEndTime(endTimeDate);

			int rowcount = takeOffDao.updateTakeOffByEmpId(formId, takeOff);
			System.out.println("update  rowcount = " + rowcount);
			return "redirect:/app/takeOff/search/"+empBook.getEmpId();
		}

		// 刪除
		@GetMapping("/delete/{formId}")
		public String deleteForm(@PathVariable("formId") String formId, HttpSession session, Model model) {
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			Form form = formDao.findFormByFormId(formId).get();
			model.addAttribute("empId", empBook.getEmpId());
			int rowcount = takeOffDao.cancelTakeOffByFormId(formId);
			int rowcount1 = formDao.cancelFormByFormId(formId);

			System.out.println("刪除成功:" + rowcount + rowcount1);
			return "redirect:/app/overtime/search/{empId}";
		}

		// ------------------------------審核功能---------------//

		// 主管依照加班查詢本部門所有加班資料
		@GetMapping(value = "/check", produces = "text/plain;charset=utf-8")
		public String takeOffSearchPageBoss(Model model, HttpSession session) {

			// 取得登入者的資訊
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			
			// 找到所有人的表單 依據登入者ID，去尋找Member的單子
			Integer depotNo = empBook.getEmpDeptno();
			List<EmpBook> deptEmpBooks = empBookDao.findEmpBooksByEmpDeptNo(depotNo);
			deptEmpBooks = deptEmpBooks
					.stream()
					.filter( emp -> !emp.getEmpId().equals(empBook.getEmpId()))
					.collect(Collectors.toList());
			
			List<TakeOff> takeOffs = new ArrayList<>();
			deptEmpBooks.forEach(emp-> {
				takeOffs.addAll(takeOffDao.findAllTakeOffByDeptNo(emp.getEmpDeptno()));
			});
			
			Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
			
			model.addAttribute("empBossName", empBossOpt.get().getEmpName());
			model.addAttribute("takeOffs", takeOffs);
			model.addAttribute("_method", "PUT");
			System.out.println("takeOff = " + takeOffs);
			return "boss/TakeOffsCheck";
		}

		// 加班申請通過
		@PutMapping("/pass/{formId}")
		
		public String passbtn(@PathVariable("formId") String formId, Model model, HttpSession session) {
			// 取得登入者資料
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			// 找到這個表單
			TakeOff takeOff = takeOffDao.findTakeOffByFormId(formId).get();
			Form form = formDao.findFormByFormId(formId).get();
			Optional<EmpBook> emp = formDao.findEmpBookByFormId(formId); 
			int rowcount = takeOffDao.passTakeOffByFormId(formId);
			System.out.println(formId + rowcount);
			return "redirect:../check";
		}

		// 加班申請未通過
		@PutMapping("/false/{formId}")
		public String falsebtn(@PathVariable("formId") String formId, 
				@RequestParam("checkReason") String checkReason,
				HttpSession session) {
			// 取得登入者資料
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			// 找到這個表單
			TakeOff takeOff = takeOffDao.findTakeOffByFormId(formId).get();
			Form form = formDao.findFormByFormId(formId).get();
			int rowcount = takeOffDao.falseTakeOffByFormId(formId, checkReason);
			System.out.println(formId);
			System.out.println(checkReason);

			return "redirect:../check";
		}

		

}