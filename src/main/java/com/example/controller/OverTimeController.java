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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dao.EmpBookDao;
import com.example.dao.FormDao;
import com.example.dao.OverTimeDao;
import com.example.entity.EmpBook;
import com.example.entity.Form;
import com.example.entity.OverTime;
import com.example.util.Qrcode;
import com.google.zxing.WriterException;

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
	public String OverTimeRequestPage(Model model, HttpSession session, @ModelAttribute OverTime overTime,RedirectAttributes redirectAttributes) {
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
		System.out.println("目前已經審核通過加班清單 : " + calculateOverTimeHourList);
		System.out.println("總申請時數 = " + totalOvertimeHour);
		// 填表日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("overTimeDate", sdf.format(new Date()));

		// 計算目前所剩下的加班時數
		int overTimeLeftHour = empBook.getOverTimeLeftHour() - totalOvertimeHour;
		model.addAttribute("overTimeLeftHour", overTimeLeftHour);
		System.out.println("目前所剩下的加班時數 = " + overTimeLeftHour);
		redirectAttributes.addFlashAttribute("overTimeLeftHour", overTimeLeftHour);

		return "emp/OvertimeRequest";

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("/add/{empId}")
	public String addOverTime(@RequestParam Map<String, Object> formMap, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) throws ParseException, WriterException, IOException {

		// 取得登入者資訊
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 利用uuid產生formId
		String uuid = UUID.randomUUID().toString();

		Form form = new Form();
		form.setApplier(empBook.getEmpId());
		form.setFormId(uuid);
		form.setType(2); // 加班表單固定type是2
		form.setApplyDate(new Date());

		formDao.addForm(form);

		OverTime overTime = new OverTime();
		overTime.setFormId(uuid);

		// 從表單取得加班資料

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
		String path = Qrcode.generateQRcode(uuid);
		overTimeDao.addOverTime(overTime);
		model.addAttribute("overtime", overTime);

		return "redirect:../search/{empId}";
		// return formMap + "<hr>"+ overTime+"";

	}

	// 加班查詢資料(員工查自己)
	@GetMapping(value = "/search/{empId}")
	public String overtimeSearchPage(Model model, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到自己所有的加班紀錄
		List<OverTime> OverTimeList = overTimeDao.findAllOverTimeByEmpId(empBook.getEmpId());
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		System.out.println("overTime = " + OverTimeList);
		model.addAttribute("overTime", OverTimeList);
		
		addOverTimeLeftHour(model, session);
		

	/*	// 計算目前已審核的總加班時數
		Integer empId = empBook.getEmpId();
		List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
		model.addAttribute("overTimesbyId", calculateOverTimeHourList);
		int totalOvertimeHour = calculateOverTimeHourList.stream().filter(o -> o.getVerifyState() == 1)
				.mapToInt(OverTime::getApplyHour).sum();
		model.addAttribute("totalOvertimeHour", totalOvertimeHour);

		// 填表日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("overTimeDate", sdf.format(new Date()));
		
		 Integer overTimeLeftHour = (Integer) model.asMap().get("overTimeLeftHour");
		 model.addAttribute("overTimeLeftHour", overTimeLeftHour);
		 System.out.println("overTimeLeftHour = "+ overTimeLeftHour);


		// 計算目前所剩下的加班時數
		int overTimeLeftHour = empBook.getOverTimeLeftHour() - totalOvertimeHour;
		model.addAttribute("overTimeLeftHour", overTimeLeftHour);
		empBookDao.addOverTimeLeftHourByEmpId(empId, overTimeLeftHour);*/

		// 尚未審核加班時數

		List<OverTime> nonCheckOutOverTimeHourList = overTimeDao.findNonCheckoutOverTimeFormByEmpId(empBook.getEmpId());
		int nonCheckOutOverTimeHour = nonCheckOutOverTimeHourList.stream().mapToInt(OverTime::getApplyHour).sum();
		model.addAttribute("nonCheckOutOverTimeHour", nonCheckOutOverTimeHour);
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
			return "emp/overTimeResult";
		}

		List<OverTime> filteredOverTimes = overTimeDao.findAllOverTimeByEmpIdAndStartDateAndEndDate(empBook.getEmpId(),
				StartDate2, EndDate2);

		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		model.addAttribute("OverTimefilter", filteredOverTimes);
		return "emp/OvertimeSearchDetail";
	}

	// 每筆紀錄的詳情頁
	@GetMapping(value = { "/deatil/{fomrId}" })
	public String searchOverTimeDetail(@PathVariable("fomrId") String fomrId, Model model, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		OverTime overTimes = overTimeDao.findOverTimeByFormId(fomrId).get();
		Form form = formDao.findFormByFormId(fomrId).get();

		if (overTimes.getOvertimeType() == 1) {
			model.addAttribute("overTimetype", "加班費");
		}

		if (overTimes.getOvertimeType() == 2) {
			model.addAttribute("overTimetype", "補休");
		}

		if (overTimes.getDayOrHoilday() == 1) {
			model.addAttribute("DayOrHoilday", "平日加班");
		}

		if (overTimes.getDayOrHoilday() == 2) {
			model.addAttribute("DayOrHoilday", "假日加班");
		}

		System.out.println(overTimes.getOvertimeType());
		model.addAttribute("overTime", overTimes);
		model.addAttribute("form", form);
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		return "emp/OverTimeResult";
	}

	// 修改 加班表單
	@GetMapping("/show/{formId}")
	public String showOvertime(@PathVariable("formId") String formId, Model model, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		OverTime overTime = overTimeDao.findOverTimeByFormId(formId).get();
		Form form = formDao.findFormByFormId(formId).get();

		model.addAttribute("overTime", overTime);
		model.addAttribute("form", form);
		System.out.println(formId);
		System.out.println(overTime);
		// 默認startTime
		String updateTime = sdf.format(overTime.getStartTime());
		String updateendTime = sdf.format(overTime.getEndTime());
		model.addAttribute("updateTime", updateTime);
		model.addAttribute("updateendTime", updateendTime);
		System.out.println(overTime.getStartTime());
		System.out.println(overTime.getEndTime());
		System.out.println(updateTime);
		System.out.println(overTime.getOvertimeType());
		System.out.println(overTime.getDayOrHoilday());

		// 計算目前已審核的總加班時數
		Integer empId = empBook.getEmpId();
		List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
		int totalOvertimeHour = calculateOverTimeHourList.stream().mapToInt(OverTime::getApplyHour).sum();
		model.addAttribute("totalOvertimeHour", totalOvertimeHour);

		return "emp/OvertimeRequestUpdate"; // 重導到 user 首頁
	}

	// 修改
	@PostMapping("/update/{formId}")
	public String updateOvertime(@PathVariable("formId") String formId,
			@RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime,
			@ModelAttribute("overTime") OverTime overTime, Model model, HttpSession session) throws ParseException {

		EmpBook empBook = (EmpBook) session.getAttribute("empBook");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date startTimeDate = dateFormat.parse(startTime);
		Date endTimeDate = dateFormat.parse(endTime);
		overTime.setStartTime(startTimeDate);
		overTime.setEndTime(endTimeDate);

		int rowcount = overTimeDao.updateOverTimeByFormId(formId, overTime);
		System.out.println("update  rowcount = " + rowcount);
		return "redirect:/app/overtime/search/" + empBook.getEmpId();
	}

	// 刪除
	@GetMapping("/delete/{formId}")
	public String deleteForm(@PathVariable("formId") String formId, HttpSession session, Model model) {
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		Form form = formDao.findFormByFormId(formId).get();
		model.addAttribute("empId", empBook.getEmpId());
		int rowcount = overTimeDao.cancelOverTimeByFormId(formId);
		int rowcount1 = formDao.cancelFormByFormId(formId);

		System.out.println("刪除成功:" + rowcount + rowcount1);
		return "redirect:/app/overtime/search/{empId}";
	}

	// ------------------------------審核功能---------------//

	// 主管依照加班查詢本部門所有加班資料
	@GetMapping(value = "/check", produces = "text/plain;charset=utf-8")
	public String overtimeSearchPageBoss(Model model, HttpSession session) {

		// 取得登入者的資訊
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");

		// 找到所有人的表單 依據登入者ID，去尋找Member的單子
		Integer depotNo = empBook.getEmpDeptno();

		List<EmpBook> deptEmpBooks = empBookDao.findEmpBooksByEmpDeptNo(depotNo);
		deptEmpBooks = deptEmpBooks.stream().filter(emp -> !emp.getEmpId().equals(empBook.getEmpId())).limit(1)
				.collect(Collectors.toList());

		List<OverTime> overTimes = new ArrayList<>();
		deptEmpBooks.forEach(emp -> {
			overTimes.addAll(overTimeDao.findAllOverTimeByDeptNo(emp.getEmpDeptno()));
		});

		System.out.println(deptEmpBooks.size());

		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());

		model.addAttribute("empBossName", empBossOpt.get().getEmpName());
		model.addAttribute("overTimes", overTimes);
		model.addAttribute("_method", "PUT");
		System.out.println("overTime = " + overTimes);
		return "boss/OvertimeCheck";
	}

	// 加班申請通過
	@PutMapping("/pass/{formId}")

	public String passbtn(@PathVariable("formId") String formId, Model model, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到這個表單
		OverTime overTime = overTimeDao.findOverTimeByFormId(formId).get();
		Form form = formDao.findFormByFormId(formId).get();
		Optional<EmpBook> emp = formDao.findEmpBookByFormId(formId);
		int rowcount = overTimeDao.passOverTimeByFormId(formId);
		System.out.println(formId + rowcount);
		return "redirect:../check";
	}

	// 加班申請未通過
	@PutMapping("/false/{formId}")
	public String falsebtn(@PathVariable("formId") String formId, @RequestParam("checkReason") String checkReason,
			HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到這個表單
		OverTime overTime = overTimeDao.findOverTimeByFormId(formId).get();
		Form form = formDao.findFormByFormId(formId).get();
		int rowcount = overTimeDao.falseOverTimeByFormId(formId, checkReason);
		System.out.println(formId);
		System.out.println(checkReason);

		return "redirect:../check";
	}
	
	// 首頁基礎資料
	private void addOverTimeLeftHour(Model model,HttpSession session) {
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 計算目前已審核的總加班時數
				Integer empId = empBook.getEmpId();
				List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
				model.addAttribute("overTimesbyId", calculateOverTimeHourList);
				int totalOvertimeHour = calculateOverTimeHourList.stream().mapToInt(OverTime::getApplyHour).sum();
				model.addAttribute("totalOvertimeHour", totalOvertimeHour);
				System.out.println("目前已經審核通過加班清單 : " + calculateOverTimeHourList);
				System.out.println("總申請時數 = " + totalOvertimeHour);
				// 填表日期

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				model.addAttribute("overTimeDate", sdf.format(new Date()));

				// 計算目前所剩下的加班時數
				int overTimeLeftHour = empBook.getOverTimeLeftHour() - totalOvertimeHour;
				model.addAttribute("overTimeLeftHour", overTimeLeftHour);
				System.out.println("目前所剩下的加班時數 = " + overTimeLeftHour);

	}
	
	

}
