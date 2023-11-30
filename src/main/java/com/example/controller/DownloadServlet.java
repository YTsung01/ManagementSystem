package com.example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(value = "/download")
public class DownloadServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("APPLICATION/OCTET-STREAM"); //資料型別是一個跟串流有關的檔案型別
		resp.setHeader("Content-Disposition", "attachment; filename=\"123.xlsx\""); //資料內容處置方法
		String filePath = "C:\\Users\\NTPU\\Desktop\\123.xlsx";
	
	}

		
	
}
