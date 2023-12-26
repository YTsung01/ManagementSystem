<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp" %>
首頁

<html>
	<form class="pure-form" >				
		員工編號: <span class="border"> ${ employee.empId } <p /></span>
		姓名: 姓名 <p />
		部門: 部門 <p />
	</form>
</html>

<%@ include file="/WEB-INF/view/Systemfooter.jsp" %>
