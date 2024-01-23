package com.example.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.dao.CheckInDao;
import com.example.entity.CheckIn;
import com.example.entity.EmpBook;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

	@Autowired
	private CheckInDao checkInDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	/**
	 * 
	 * 打卡紀錄 +-------+---------+---------------+--------+---------------------+ |
	 * empId | empName | empDepartment | empJob | CheckInTime |
	 * +-------+---------+---------------+--------+---------------------+ | 101 |
	 * Solar | sale |Engineer|'2023-12-12 16:12:39'| | 102 | MoonByul| sale
	 * |Engineer|'2023-12-12 16:12:39'| | 103 | WheeIn | sale |Engineer|'2023-12-12
	 * 16:12:39'| | 104 | Hwasa | sale |Engineer|'2023-12-12 16:12:39'|
	 * +-------+---------+---------------+--------+---------------------+
	 */

	// 打卡首頁
	@GetMapping(value = { "/{empId}" })
	public String checkInPage(Model model, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");

		Optional<CheckIn> latestCheckin = checkInDao.findLatestCheckInByEmpId(empBook.getEmpId());

		if (latestCheckin.isPresent()) {
			Date checkInTime = latestCheckin.get().getCheckInTime();
			Date CheckOutTime = latestCheckin.get().getCheckOutTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 判斷是否超過9點及18點
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(checkInTime);
			calendar.setTime(CheckOutTime);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour > 9 || hour < 7) {
				model.addAttribute("lateCheckInMessage", "打卡時間異常，請申請假單");
			}
			if (hour > 18 || hour < 17) {
				model.addAttribute("lateCheckOutMessage", "打卡時間異常，請申請加班");
			}
			String formattedCheckInTime = dateFormat.format(checkInTime);
			String formattedCheckOutTime = dateFormat.format(CheckOutTime);
			model.addAttribute("formattedCheckInTime", formattedCheckInTime);
			model.addAttribute("formattedCheckOutTime", formattedCheckOutTime);

		} else {
			model.addAttribute("formattedCheckInTime", "");
			model.addAttribute("formattedCheckOutTime", "");
		}
		return "emp/CheckIn";
	}

	// 新增上班打卡資料
	@PostMapping(value = { "/addcheckIn/{empId}" }, produces = "text/plain;charset=utf-8")
	// @ResponseBody
	public String addcheckIn(Model model, HttpSession session) throws ParseException {

		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");

		// 新增打卡資料
		CheckIn checkIn = new CheckIn();
		checkIn.setEmpId(empBook.getEmpId());
		checkInDao.addCheckIn(checkIn);
		model.addAttribute("checkIn", checkIn);
		// 找到最新一筆checkin時間
		Optional<CheckIn> latestCheckin = checkInDao.findLatestCheckInByEmpId(empBook.getEmpId());
		Date checkInTime = latestCheckin.get().getCheckInTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 判斷是否超過9點及18點
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(checkInTime);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour > 9) {
			model.addAttribute("lateCheckInMessage", "");
		}
		String formattedCheckInTime = dateFormat.format(checkInTime);
		model.addAttribute("formattedCheckInTime", formattedCheckInTime);
		return "redirect:../{empId}";
	}

	// 新增下班打卡
	@GetMapping(value = { "/addcheckOut/{empId}" }, produces = "text/plain;charset=utf-8")
	public String addcheckOut(Model model, HttpSession session) throws ParseException {

		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");

		// 找到最新一筆checkin並取得時間
		Optional<CheckIn> latestCheckin = checkInDao.findLatestCheckInByEmpId(empBook.getEmpId());
		if (latestCheckin.isPresent()) {
			Date checkInTime = latestCheckin.get().getCheckInTime();
			Date CheckOutTime = latestCheckin.get().getCheckOutTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 判斷是否超過9點及18點
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(checkInTime);
			calendar.setTime(CheckOutTime);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour > 9) {

				model.addAttribute("lateCheckInMessage", "");
			}

			if (hour > 18) {
				model.addAttribute("lateCheckOutMessage", "");
			}
			String formattedCheckInTime = dateFormat.format(checkInTime);
			String formattedCheckOutTime = dateFormat.format(CheckOutTime);
			model.addAttribute("formattedCheckInTime", formattedCheckInTime);
			model.addAttribute("formattedCheckOutTime", formattedCheckOutTime);

		} else {
			model.addAttribute("formattedCheckInTime", "");
			model.addAttribute("formattedCheckOutTime", "");
		}
		return "redirect:../{empId}";
	}

	// 得到加班資料頁面
	@GetMapping(value = { "/checkinresult/{empId}" })
	public String checkInandCheckOutData(Integer empId, Model model, CheckIn checkIn, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到所有的加班紀錄
		List<CheckIn> CheckIndata = checkInDao.findAllCheckInByEmpId(empBook.getEmpId());
		//統計遲到次數
		int lateCount = checkInDao.countLateCheckIns(empBook.getEmpId());
		
		model.addAttribute("lateCount", lateCount);
		model.addAttribute("CheckIndata", CheckIndata);
		return "emp/CheckInResult";
	}
	
	//搜尋功能
	@GetMapping(value={"/searchCheckIn"})
	public String searchCheckIn(@RequestParam(name = "startDate", required = false) String startDate,
								@RequestParam(name = "endDate", required = false) String endDate, 
								Model model, HttpSession session) throws ParseException {
	    // 取得登入者資料
	    EmpBook empBook = (EmpBook) session.getAttribute("empBook");
	    // 處理搜索邏輯，selectedMonth 是前端傳遞過來的月份參數
	    Date StartDate2 = sdf.parse(startDate);
	    Date EndDate2 = sdf.parse(endDate);
	    List<CheckIn> filteredCheckIns = checkInDao.findAllCheckInByEmpIdAndStartDateAndEndDate(empBook.getEmpId(), StartDate2, EndDate2);
	    // 將結果傳遞到 JSP 中
	    model.addAttribute("CheckInfilter", filteredCheckIns);
	    return "emp/CheckIndatailResult";
	}

}