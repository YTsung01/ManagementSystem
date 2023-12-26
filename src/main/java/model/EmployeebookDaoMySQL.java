package model;

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

import com.example.*;


public class EmployeebookDaoMySQL implements EmployeebookDao{
	
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
	
	@Override
	public void create(Employeebook employeebook) {
		String sql = "insert into employeebook(id, NickName, department) values(?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 配置 sql ? 資料
			pstmt.setInt(1, employeebook.getId());
			pstmt.setString(2, employeebook.getNickname());
			pstmt.setString(3, employeebook.getDepartment());

			// 提交送出
			int rowcount = pstmt.executeUpdate();
			System.out.println("rowcount(異動筆數) = " + rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Employeebook> readAll() {
		String sql = "select id, NickName, department, date from employeebook order by id";
		List<Employeebook> employeebooks = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql) ) {
			
			// 將 rs 的資料逐筆注入到 employeebook 物件中
			while (rs.next()) {
				Employeebook employeebook = new Employeebook();
				employeebook.setId(rs.getInt("empid"));
				employeebook.setNickname(rs.getString("empName"));
				employeebook.setDepartment(rs.getString("empDepartment"));
				employeebook.setDate(new Date(rs.getTimestamp("date").getTime()));

				// 加入到 employeebooks 集合中
				employeebooks.add(employeebook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employeebooks;
	}

	

	
	
}
