package com.example.controller.Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Employeebook;
import model.EmployeebookDAOInMemory;
import model.EmployeebookDao;
import model.EmployeebookDaoMySQL;

@WebServlet(value= "/checkin")
public class CheckInServelet extends HttpServlet {
	
	private EmployeebookDao employeebookDao = new EmployeebookDAOInMemory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取得所有留言紀錄
		
		List<Employeebook> employeebooks = employeebookDao.readAll();
		
		//重導到 /WEB-INF/view/guestbook/guestbook.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/CheckIn.jsp");
		req.setAttribute("employeebooks", employeebooks); //傳遞參數給JSP    
		rd.forward(req, resp);
	}
	//給表單新增使用
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//getParameter 取得表單資料
		String nickname =req.getParameter("nickname");
		Integer id =Integer.parseInt(req.getParameter("id")); //直接裝箱轉型成int
		String department =req.getParameter("department");
	
		
		//建立Guestbook物件
		
		Employeebook employeebook= new Employeebook();
		
		employeebook.setId(id);
		employeebook.setNickname(nickname);
		employeebook.setDepartment(department);
		employeebook.setDate(new Date());
		
		
		
		
		//加入到資料庫中
		
		employeebookDao.create(employeebook);
		
		//重導到新增完成頁面/WEB-INF/view/guestbook/guestbook_result.jsp
		//RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/guestbook/guestbook_result.jsp");
		//rd.forward(req, resp);
		
		//請瀏覽器根據下面的網址自行重導
		resp.sendRedirect("http://localhost:8080/ManagementSystem_my/checkin");
	}
	
	

}




