package com.example.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@GetMapping(value = { "/{empId}" })
		public String checkInPage(@PathVariable("empId") Integer empId,Model model, HttpSession session) {
		//取得登入者資料
		EmpBook empBook = (EmpBook)session.getAttribute("empBook");
	    
	    if (empBook == null) {
	        return "redirect:/login";
	    }
		model.addAttribute("checkIn",checkInDao.findAllCheckInByEmpId(empBook.getEmpId()));
		return "emp/CheckIn";
		}
		
		
	//新增打卡資料	
	@PostMapping(value = {"/addcheckIn/{empId}"} , produces = "text/plain;charset=utf-8")
		//@ResponseBody
		public String addcheckIn( Model model, HttpSession session  ) throws ParseException {
			
			// 取得登入者資料
			EmpBook empBook = (EmpBook) session.getAttribute("empBook");
			
			//新增打卡資料
			CheckIn checkIn = new CheckIn();
			checkIn.setEmpId(empBook.getEmpId());
			checkInDao.addCheckIn(checkIn);
			model.addAttribute("checkIn", checkIn);
			model.addAttribute("checkInTime",checkIn.getCheckInTime() );
			model.addAttribute("checkOutTime", checkIn.getCheckOutTime());

			// 新增打卡時間

			return  "redirect:add/emp/CheckIn";
			/*
			 * / 新增打卡紀錄 (rowcount 資料表異動筆數) try { int rowcount =
			 * checkInDao.addCheckIn(checkInList); if(rowcount == 0) { return "打卡失敗"; } else
			 * { return "打卡成功"; } } catch (Exception e) { return "打卡失敗: " +
			 * (e.getMessage().contains("Duplicate entry") ? "已打卡" : e.getMessage()); }
			 */
		}
	
	@PostMapping(value = {"/addcheckOut/{empId}"}, produces = "text/plain;charset=utf-8")
	public String addcheckOut(Model model, HttpSession session) throws ParseException {
	    
	    // 取得登入者資料
	    EmpBook empBook = (EmpBook) session.getAttribute("empBook");
	    
	    // 找到今天的打卡紀錄紀錄
	    List<CheckIn> todayCheckInRecordCheckIns = checkInDao.findTodayCheckInByEmpId(empBook.getEmpId(), new Date());
	    
	    // 確保今天的打卡紀錄存在
	    if (todayCheckInRecordCheckIns != null && !todayCheckInRecordCheckIns.isEmpty()) {
	        // 新增下班打卡
	        todayCheckInRecordCheckIns.get( 0).setCheckOutTime(new Date());
	        checkInDao.addCheckOut(todayCheckInRecordCheckIns.get(0));
	        model.addAttribute("checkIn", todayCheckInRecordCheckIns.get(0));
	        model.addAttribute("checkOutTime", todayCheckInRecordCheckIns.get(0).getCheckOutTime());
	    } else {
	        // 若打卡紀錄不存在，可以進行相應處理，例如返回錯誤消息給前端
	        model.addAttribute("errorMessage", "找不到今天的打卡紀錄");
	    }
	    
	    // 更新畫面
	    // 這裡可以進行相應的頁面跳轉或局部刷新等操作
	    
	    // 返回視圖
	    return  "redirect:add/emp/CheckIn";
	}

}