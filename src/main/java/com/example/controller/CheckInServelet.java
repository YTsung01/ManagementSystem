package com.example.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Employeebook;
import com.example.model.EmployeebookDAOInMemory;
import com.example.model.EmployeebookDao;




@WebServlet(value= "/CheckIn")
public class CheckInServelet extends HttpServlet {
	
	
	private EmployeebookDao employeebookDao = new EmployeebookDAOInMemory();
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// 建立 Employeebook 物件
		Employeebook employeebook=new Employeebook();
		
		employeebook.setDate(new Date());
		

				
		// 加入到資料庫中
		employeebookDao.create(employeebook);
		
		// 重導到新增完成頁面 /WEB-INF/view/employeebook_result.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/employeebook_result.jsp");
		rd.forward(req, resp);

	}




	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//取得所有紀錄
		List<Employeebook> employeebooks= employeebookDao.readAll();
		// 重導到 /WEB-INF/view/employeebook.jsp
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/employeebook.jsp");
			req.setAttribute("employeebook", employeebooks); //傳遞參數給jsp
			rd.forward(req, resp);
	}
	
	
	

}




