package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
					model.addAttribute("lateCheckInMessage", "");
				}
				if (hour > 18 || hour < 17) {
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
			return "emp/CheckIn";
		}

	// 新增打卡資料
	@PostMapping(value = { "/addcheckIn/{empId}" }, produces = "text/plain;charset=utf-8")
	// @ResponseBody
	public String addcheckIn(Model model, HttpSession session,@RequestParam Map map) throws ParseException {
		
		Integer flag = Integer.parseInt(map.get("flag") + "");
		System.out.println("flag = " + flag);
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		if (flag == 1) {
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
		}

		if (flag == 2) {
			// 確認是否有最新的最新一筆checkin並取得時間然後指update CheckOutTime
			Optional<CheckIn> latestCheckin = checkInDao.findLatestCheckInByEmpId(empBook.getEmpId());
			if (latestCheckin.isPresent()) {
				// 利用empId把它更新進去
				CheckIn checkins = latestCheckin.get();
				checkInDao.addCheckOut(empBook.getEmpId());
				// 取得checkOutTime
				Date CheckOutTime = checkins.getCheckOutTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				// 判斷是否超過9點及18點
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(CheckOutTime);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				if (hour > 18) {
					model.addAttribute("lateCheckOutMessage", "");
				}

				String formattedCheckOutTime = dateFormat.format(CheckOutTime);
				model.addAttribute("formattedCheckOutTime", formattedCheckOutTime);

			}
		}

		return "redirect:../{empId}";
	}

	// 得到打卡資料頁面
	@GetMapping(value = { "/checkinresult/{empId}" })
	public String checkInandCheckOutData(Model model, CheckIn checkIn, HttpSession session) {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		// 找到自己的所有打卡紀錄
		List<CheckIn> CheckIndata = checkInDao.findAllCheckInByEmpId(empBook.getEmpId());
		// 統計遲到次數
		int lateCount = checkInDao.countLateCheckIns(empBook.getEmpId());

		model.addAttribute("lateCount", lateCount);
		model.addAttribute("CheckIndata", CheckIndata);
		return "emp/CheckInResult";
	}

	// 搜尋功能
	@GetMapping(value = { "/searchCheckIn" })
	public String searchCheckIn(@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate, Model model, HttpSession session)
			throws ParseException {
		// 取得登入者資料
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date StartDate2 = sdf.parse(startDate);
		Date EndDate2 = sdf.parse(endDate);

		if (startDate == null || endDate == null) {
			// 如果 startDate 或 endDate 为空值，返回错误信息
			model.addAttribute("error", "請選擇日期");
			return "emp/CheckInResult"; // 创建一个专门用于显示错误信息的页面，也可以直接返回原页面并在前端显示错误信息
		}
		List<CheckIn> filteredCheckIns = checkInDao.findAllCheckInByEmpIdAndStartDateAndEndDate(empBook.getEmpId(),
				StartDate2, EndDate2);
		// 將結果傳遞到 JSP 中
		model.addAttribute("CheckInfilter", filteredCheckIns);
		return "emp/CheckIndatailResult";
	}

}