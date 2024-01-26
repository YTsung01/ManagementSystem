package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.EmpBookDao;
import com.example.entity.EmpBook;
import com.example.service.MainPageImpl;
import com.example.service.MainPageStatus;

@Controller
@RequestMapping("/main")
public class MainPageController {

	@Autowired
	EmpBookDao empBookDao;

	@Autowired
	MainPageImpl mainPageImpl;

	@GetMapping
	public String mainPage(Model model, HttpSession session) {

		EmpBook empBook = (EmpBook) session.getAttribute("empBook");
		model.addAttribute("empBook", empBook);
		Optional<EmpBook> empBossOpt = empBookDao.findEmpBookByEmpDeptNoAndLevelId(empBook.getEmpDeptno());
		MainPageStatus mainPageStatus = mainPageImpl.getMainPageStatusByEmpBook(empBook, empBossOpt);

		if (mainPageStatus == MainPageStatus.EXIST_BOSS_EMP_LEVEL_1) {
			model.addAttribute("empBossName", empBossOpt.get().getEmpName());
			model.addAttribute("showEmployeeList", false);
		} else if (mainPageStatus == MainPageStatus.EXIST_BOSS_EMP_LEVEL_2) {
			List<EmpBook> employeeList = empBookDao.findEmpBooksByEmpDeptNo(empBook.getEmpDeptno());
			model.addAttribute("empBossName", empBook.getEmpName());
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("showEmployeeList", true);
		} else if (mainPageStatus == MainPageStatus.NON_EXIST_BOSS) {
			model.addAttribute("empName", "Default Name");
			model.addAttribute("showEmployeeList", false);
		}

		return "mainpage";
	}
}
