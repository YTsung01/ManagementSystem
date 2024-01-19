<%@page import="com.example.model.entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<style>
body {
	overflow: hidden;
}
</style>
<%!// !代表置頂  

	interface Pattern {
		String ROW_PATTERN = "['%s', %s]";
		String TABLE_ROW_PATTERN = "[%d, '%s', %d, '%s', '%s', '%s'],";
	}

	//將日期格式化 yyyy-MM-dd HH:mm:ss E
	private String getDateFormatString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		return sdf.format(date);
	}%>

<%
// 使用當前時間生成唯一的表單單號
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
String formNumber = dateFormat.format(new Date());
%>
</head>
<body>

${ employee }
<hr>
${ checkInList }

<div class="d-flex justify-content-center mx-auto p-4">
	<sp:form modelAttribute="checkIn" action="./add/${employee.empId}
		method="post" enctype="multipart/form-data"
		class="border rounded mx-auto p-4">

		<div class="pb-2">
			員工編號: ${employee.empId }
			<p />
		</div>
		<div class="pb-2">
			姓名: ${ employee.empName }
			<p />
		</div>
		<div class="pb-2">
			部門: ${ employee.empDepartment  }
			<p />
		</div>
		<div class="pb-2">
			職位: ${ employee.empJob }
			<p />
		</div>
		<div class="pb-2">
			今日時間:
			<%=formNumber%>
			<p />
		</div>





		<button type="submit"
			class="pure-button pure-button-primary align-items-center m-4">上班</button>
		<button type="submit"
			class="pure-button pure-button-primary align-items-center m-4">下班</button>





		<table class="table table-bordered">
			<thead>
				<tr>
					<th>員工編號</th>
					<th>名稱</th>
					<th>部門</th>
					<th>職位</th>
					<th>上班時間</th>
					<th>下班時間</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${ employee.empId }</td>
					<td>${ employee.empName }</td>
					<td>${ employee.empDepartment  }</td>
					<td>${ employee.empJob }
					</th>
					<td>${ checkin.checkInTime }</td>
					<td>${ checkin.checkInTime }</td>
				</tr>
			</tbody>
		</table>

	</sp:form>
</div>
</body>

</html>