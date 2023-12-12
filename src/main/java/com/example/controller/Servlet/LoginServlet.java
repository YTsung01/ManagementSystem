package com.example.controller.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet(value= "/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//預設username= user,password=123
        boolean isPasswordMatch = BCrypt.checkpw(password, "$2a$10$qgVZMdNoKQ/JwXr4eQS8SOu2cYBPE9Y.ox2QRX9y.wjIKQZ7S3OBe");
		
        if("user".equals(username) && isPasswordMatch) {
			//設定Http Session資料
        	//用session對應到username存到server端
        	HttpSession session = req.getSession(); //取得sessionid資訊
        	session.setAttribute("islogin", true);
        	session.setAttribute("username", username);
        	session.setMaxInactiveInterval(60*3*5);
			resp.sendRedirect("./");
			return;
		}
		
		//驗證沒有通過的時候要怎麼處理
		
        	req.setAttribute("error", "帳號或密碼輸入錯誤");
        	RequestDispatcher rd = req.getRequestDispatcher("./login.jsp");
        	rd.forward(req, resp);
	
}}
