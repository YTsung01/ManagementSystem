<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.example.model.entity.oldEmployee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
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
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
String formNumber = sdf.format(new Date());
%>
</head>
<body>

	${ empBook }
	<hr>
	${ checkIn }

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
				style="background-color: #e3f2fd" onclick="submitForm('checkIn')">上班
			</button>
			<button type="button" class="btn mx-3 align-items-center m-4"
				style="background-color: #ccdce8" onclick="submitForm('checkOut')">下班
			</button>

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
						<td>${ empBook.empId }</td>
						<td>${ empBook.empName }</td>
						<td>${ empBook.empDepartment  }</td>
						<td>${ empBook.empJob }</td>
						<td id="checkInTime"><fmt:formatDate value="${checkInTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td id="checkOutTime"><fmt:formatDate value="${checkOutTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</tbody>
			</table>
		</sp:form>
	</div>

	<script>
function submitForm(action) {
    $.ajax({
        type: 'POST',
        url: './add/${empBook.empId}',
        data: { action: action, empId: ${empBook.empId} },
        success: function (response) {
            // 更新上班和下班時間的顯示
            var currentTime = new Date().toLocaleString();
            if (action === 'checkIn') {
                $('#checkInTime').text(currentTime);
            } else if (action === 'checkOut') {
                $('#checkOutTime').text(currentTime);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}

</script>

</body>

</html>