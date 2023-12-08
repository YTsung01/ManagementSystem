<!DOCTYPE html>
<%@page import="model.Employeebook"%>
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

<%
 List<Employeebook> employeebooks=(List<Employeebook>)request.getAttribute("employeebooks");
%>




<html>
	<head>
	
	<form class="pure-form">
		員工編號: <input type="text" name="name" placeholder="請輸入員工編號" /><p />
		姓名: <input type="text" name="name" placeholder="請輸入姓名" /><p />
		部門: <input type="text" name="name" placeholder="請輸入部門"  /><p />
		
		<button type="submit" class="pure-button pure-button-primary">打卡</button>	  
	</form>
	
	
	<table  class="table table-bordered">
	<thead>
				<tr>
					<th>員工編號</th>
					<th>名稱</th>
					<th>性別</th>
					<th>部門</th>
					<th>職位</th>
					<th> </th>
				</tr>
	</thead>
  <tbody>
 		<tr>		
			<th>id</th>
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