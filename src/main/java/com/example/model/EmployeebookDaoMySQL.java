package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public abstract class EmployeebookDaoMySQL implements EmployeebookDao{
	
private Connection conn;
	
	public EmployeebookDaoMySQL() {
		// 透過 JNDI 來查找資源
		try {
			InitialContext ctx = new InitialContext(); // 初始環境
			Context envContext = (Context)ctx.lookup("java:comp/env"); // 取得環境物件
			DataSource ds = (DataSource)envContext.lookup("jdbc/web"); // 透過環境物件取得指定資源
			
			conn = ds.getConnection(); // 取得資源連線
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
}
	@Override
	protected void finalize() throws Throwable {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
