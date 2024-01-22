package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.entity.oldEmployee;

@Controller
@RequestMapping("/main")
public class MainPageController {

	@GetMapping
	public String mainPage(Model model) {
		return "mainpage";
	}
}
