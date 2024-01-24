<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<style>
body {
	overflow: hidden;
}
</style>
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
String formNumber = sdf.format(new Date());
%>
</head>
<body>
	<!-- ${ empBook } -->
	<div class="d-flex justify-content-center mx-auto p-4">
		<sp:form modelAttribute="checkIn"
			action="./addcheckIn/${empBook.empId}" method="post"
			enctype="multipart/form-data" class="border rounded mx-auto p-4">

			<div class="pb-2">
				員工編號: ${empBook.empId }
				<p />
			</div>
			<div class="pb-2">
				姓名: ${ empBook.empName }
				<p />
			</div>
			<div class="pb-2">
				部門: ${ empBook.empDepartment  }
				<p />
			</div>
			<div class="pb-2">
				職位: ${ empBook.empJob }
				<p />
			</div>
			<div class="pb-2">
				今日時間:
				<%=formNumber%>
				<p />
			</div>





			<button type="submit" class="btn mx-3 align-items-center m-4"
				style="background-color: #e3f2fd">上班</button>
			<a href="javascript:void(0);"
				onClick="addCheckOut(${ empBook.empId  })"
				class="btn mx-3 align-items-center m-4"
				style="background-color: #ccdce8">下班</a>

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
					<tr>
						<td>${ empBook.empId }</td>
						<td>${ empBook.empName }</td>
						<td>${ empBook.empDepartment  }</td>
						<td>${ empBook.empJob }</td>
						<td id="checkInTime"
							style="${lateCheckInMessage != null ? 'color: red;' : ''}">
							${formattedCheckInTime}</td>
						
						<td name="checkOutTime" id="${formattedCheckOutTime}" value="${formattedCheckOutTime}"
							style="${lateCheckOutMessage != null ? 'color: red;' : ''}">
							${formattedCheckOutTime}</td>
				</tbody>
			</table>
		</sp:form>
	</div>

	<script type="text/javascript">
		function submitForm(action) {
			// 設置表單的action屬性
			document.form1.action = "./add" + action + "/${empBook.empId}";
			// 提交表單
			document.form1.submit();
		}
	</script>

	<script type="text/javascript">
			
			function addCheckOut(empId, checkOutTime) {
				window.location.href='./addcheckOut?checkOutTime=' + checkOutTime;
			}
			
		</script>

</body>

</html>