<!DOCTYPE html>
<%@page import="com.example.model.entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

 <%@page import="java.util.Date"%>
 <%@page import="java.text.SimpleDateFormat"%>
 
 <%@page import="java.util.List"%>




<%! // !代表置頂  

	interface Pattern {
		String ROW_PATTERN = "['%s', %s]";
		String TABLE_ROW_PATTERN = "[%d, '%s', %d, '%s', '%s', '%s'],";
	}	

	//將日期格式化 yyyy-MM-dd HH:mm:ss E
	private String getDateFormatString(Date date){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
	return sdf.format(date);
}
%>






<html>
	<head>
	
	<div class="d-flex justify-content-center mx-auto p-4"  >
	<form action="http://localhost:8080/ManagementSystem_my/CheckInDao" method="post" enctype="multipart/form-data"
		class="border rounded mx-auto p-4">
		
		<div class="pb-2">員工編號: <input type="text" name="name" placeholder="請輸入員工編號" /><p /></div>
		
		<div class="pb-2">姓名: <input type="text" name="name" placeholder="請輸入姓名" /><p /></div>
		<div class="pb-2">部門: <input type="text" name="name" placeholder="請輸入部門"  /><p /></div>
		<div class="pb-2">職位: <input type="text" name="name" placeholder="請輸入職位"  /><p /></div>
		
		
		
		
		<button type="submit" class="pure-button pure-button-primary">打卡上班</button>	  
		<button type="submit" class="pure-button pure-button-primary">打卡下班</button>	  
	</form>
	</div>
	

	
	
	<table  class="table table-bordered">
	<thead>
				<tr>
					<th>員工編號</th>
					<th>名稱</th>
					<th>性別</th>
					<th>部門</th>
					<th>職位</th>
					<th>時間 </th>
				</tr>
	</thead>
  <tbody>
 		<tr>		
			<th>${ employee.empId }</th>
			<th>Nickname</th>
			<th>sex</th>
			<th>Department</th>
			<th>Job</th>
			<th >time</th>				
		</tr>

 
  </tbody>
</table>

		
	</head>
		
</html>