package com.example.controller;

import java.util.Base64;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.EmpBookDao;
import com.example.entity.EmpBook;
import com.example.service.LoginImpl;
import com.example.service.LoginStatus;
import com.example.util.KeyUtil;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private EmpBookDao dao;
	
	@Autowired
	LoginImpl loginImpl;

	// 登入首頁
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	// 員工登入處理
	@PostMapping("/login")
	public String login(@RequestParam("empId") Integer empId, @RequestParam("password") String password,
			HttpSession session, Model model) throws Exception {
		
		// 根據 EmployeeName 查找 emp 物件
		Optional<EmpBook> empOpt = dao.findEmpBookByEmpId(empId);
		
		if (empOpt.isPresent()) {
			EmpBook empBook = empOpt.get();
			// 將 password 進行 AES 加密
			// -------------------------------------------------------------------
			final String KEY = KeyUtil.getSecretKey();
			SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
			byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, password);
			String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);
			// -------------------------------------------------------------------------------------------
//			if (employee.getEmppassword().equals(encryptedPasswordECBBase64)) { // 比對加密過後的 password 是否相同
			if (empBook.getEmpPassword().equals(encryptedPasswordECBBase64)) { // 比對加密過後的 password 是否相同
				session.setAttribute("empBook", empBook); // 將 employee 物件放入到 session 變數中
				return "redirect:/app/main"; // OK, 導向前台首頁
			} else {
				session.invalidate(); // session 過期失效
				model.addAttribute("loginMessage", "密碼錯誤,請重新確認");
				return "login";
			}
		} else {
			session.invalidate(); // session 過期失效
			model.addAttribute("loginMessage", "無此使用者,請重新確認");
			return "login";
		}
	}
	


	// 主管登入處理
	@PostMapping("/login_boss")
	public String loginBackend(@RequestParam("empId") Integer empId, @RequestParam("password") String password,
			HttpSession session, Model model) throws Exception {
		// 根據 empname 查找 emp 物件
		Optional<EmpBook> empOpt = dao.findEmpBookByEmpId(empId);
		if (empOpt.isPresent()) {
			EmpBook empBook = empOpt.get();
			// 將 password 進行 AES 加密
			// -------------------------------------------------------------------
			final String KEY = KeyUtil.getSecretKey();
			SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
			byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, password);
			String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);

			// -------------------------------------------------------------------------------------------
			if (empBook.getEmpPassword().equals(encryptedPasswordECBBase64)) {
				// 比對 level = 2 才可以登入後台
				if (empBook.getLevelId() == 2) {
					session.setAttribute("empBook", empBook); // 將 employee 物件放入到 session 變數中
					return "redirect:/app/main"; // OK, 導向後台首頁
					
				}
				session.invalidate(); // session 過期失效
				model.addAttribute("loginMessage", "無權限登入後台");
				return "login";
			} else {
				session.invalidate(); // session 過期失效
				model.addAttribute("loginMessage", "密碼錯誤,請重新確認");
				return "login";
			}
		} else {
			session.invalidate(); // session 過期失效
			model.addAttribute("loginMessage", "無此使用者,請重新確認");
			return "login";
		}
	}

	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/app/login";

	}

}
