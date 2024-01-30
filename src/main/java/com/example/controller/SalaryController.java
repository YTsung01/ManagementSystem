package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.EmpBookDao;
import com.example.dao.FormDao;
import com.example.dao.OverTimeDao;
import com.example.dao.SalaryDao;
import com.example.dao.TakeOffDao;
import com.example.entity.EmpBook;
import com.example.entity.OverTime;
import com.example.entity.TakeOff;

@Controller
@RequestMapping("/salary")
public class SalaryController {

	@Autowired
	SalaryDao salaryDao;

	@Autowired
	OverTimeDao overTimeDao;

	@Autowired
	FormDao formDao;

	@Autowired
	EmpBookDao empBookDao;

	@Autowired
	TakeOffDao takeOffDao;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	// 薪水
	@GetMapping(value = { "/search" })
	public String SalarySearchPage(Model model, HttpSession session) {
		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		if (empBook.getLevelId() == 1) {
			int bonus = 0;
			model.addAttribute("bonus", "無");
			int yearsBonus = 2500;
			model.addAttribute("yearsBonus", yearsBonus);
			// 計算總請假時數
			// 計算總加班時數

			List<OverTime> OverTimeList = overTimeDao.findAllOverTimeByEmpId(empBook.getEmpId());
			model.addAttribute("overTime", OverTimeList);

			// 計算目前已審核的總加班時數
			Integer empId = empBook.getEmpId();
			List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
			model.addAttribute("overTimesbyId", calculateOverTimeHourList);
			int totalOvertimeHour = calculateOverTimeHourList.stream().filter(o -> o.getVerifyState() == 1)
					.mapToInt(OverTime::getApplyHour).sum();
			model.addAttribute("totalOvertimeHour", totalOvertimeHour);

			// 加班的薪水 一小時 500
			int Overtimebonus = totalOvertimeHour * 500;
			model.addAttribute("Overtimebonus", Overtimebonus);
			
			// 計算目前已審核的總請假時數
			// 請假薪水 一小時 扣 250

			List<TakeOff> calculateTakeoffHourList = takeOffDao.findCheckoutTakeOffByEmpId(empId);
			model.addAttribute("takeoffbyId", calculateTakeoffHourList);
			int totaltakeOffHour = calculateTakeoffHourList.stream().mapToInt(TakeOff::getTakeoffHour).sum();
			model.addAttribute("totaltakeOffHour", totaltakeOffHour);
			
			int bassSalary = empBook.getSalary();
			int totalSalary = bassSalary + Overtimebonus + bonus +yearsBonus - (totaltakeOffHour) * 250;
			model.addAttribute("totalSalary", totalSalary);

			

		}

		if (empBook.getLevelId() == 2) {
			int bonus = 5000;
			model.addAttribute("bonus", "無");
			int yearsBonus = 5500;
			model.addAttribute("yearsBonus", yearsBonus);

			// 計算總請假時數
			// 計算總加班時數

			List<OverTime> OverTimeList = overTimeDao.findAllOverTimeByEmpId(empBook.getEmpId());
			model.addAttribute("overTime", OverTimeList);

			// 計算目前已審核的總加班時數
			Integer empId = empBook.getEmpId();
			List<OverTime> calculateOverTimeHourList = overTimeDao.findCheckoutOverTimeFormByEmpId(empId);
			model.addAttribute("overTimesbyId", calculateOverTimeHourList);
			int totalOvertimeHour = calculateOverTimeHourList.stream().filter(o -> o.getVerifyState() == 1)
					.mapToInt(OverTime::getApplyHour).sum();
			model.addAttribute("totalOvertimeHour", totalOvertimeHour);

			// 加班的薪水 一小時 500
			int Overtimebonus = totalOvertimeHour * 500;
			model.addAttribute("Overtimebonus", Overtimebonus);
			
			// 計算目前已審核的總請假時數
			// 請假薪水 一小時 扣 250

			List<TakeOff> calculateTakeoffHourList = takeOffDao.findCheckoutTakeOffByEmpId(empId);
			model.addAttribute("takeoffbyId", calculateTakeoffHourList);
			int totaltakeOffHour = calculateTakeoffHourList.stream().mapToInt(TakeOff::getTakeoffHour).sum();
			model.addAttribute("totaltakeOffHour", totaltakeOffHour);
			
			int bassSalary = empBook.getSalary();
			int totalSalary = bassSalary + Overtimebonus + bonus + yearsBonus - (totaltakeOffHour) * 250;
			model.addAttribute("totalSalary", totalSalary);

		}


		return "emp/Salary";

	}

}
