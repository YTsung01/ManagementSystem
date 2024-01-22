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

@Controller
@RequestMapping("/main")
public class MainPageController {
	
	@Autowired
	EmpBookDao empBookDao;

	@GetMapping
	public String mainPage(Model model,HttpSession session) {
		EmpBook empBook = (EmpBook)session.getAttribute("empBook");
		Integer empDeptno = empBook.getEmpDeptno();
		Optional<EmpBook> empBoss =empBookDao.findEmpBookByEmpDeptNoAndLevelId(empDeptno);
		model.addAttribute("empBoss", empBoss);
		
		 if (empBoss.isPresent()) {
	            String empBossName = empBoss.get().getEmpName();
	            model.addAttribute("empBossName", empBossName);
	        } else {
	            // 如果 empName 不存在，可以根据需要添加默认值或处理逻辑
	        	 model.addAttribute("empName", "Default Name");
	        }
		//控制levelId==2時才可以顯示員工列表
		 boolean showEmployeeList = empBoss.isPresent() && empBook.getLevelId() == 2;

		 if (showEmployeeList) {
		     List<EmpBook> employeeList = empBookDao.findEmpBooksByEmpDeptNo(empDeptno);
		     model.addAttribute("employeeList", employeeList);
		 }

		 model.addAttribute("showEmployeeList", showEmployeeList);
		    
		return "mainpage";
	}
}
