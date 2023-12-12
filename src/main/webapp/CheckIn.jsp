<!DOCTYPE html>
<%@page import="model.Employeebook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.List"%>




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
List<Employeebook> employeebooks = (List<Employeebook>) request.getAttribute("employeebooks");
%>




<html>
<head>
<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">
		<form action="./index.html" method="post"
			enctype="multipart/form-data" class="border rounded mx-auto p-4 ">
			<div class="d-flex justify-content-center">
				<div class="pb-2 mx-auto">
					員工編號: <input type="text" name="name" placeholder="請輸入員工編號" />
					<p />
				</div>

				<div class="pb-2">
					姓名: <input type="text" name="name" placeholder="請輸入姓名" />
					<p />
				</div>
				<div class="pb-2">
					部門: <input type="text" name="name" placeholder="請輸入部門" />
					<p />
				</div>
			</div>
			<div class="p-3">
				<button type="submit" class="pure-button pure-button-primary">打卡</button>

			</div>


			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th>員工編號</th>
							<th>名稱</th>
							<th>性別</th>
							<th>部門</th>
							<th>職位</th>
							<th>時間</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>id</th>
							<th>Nickname</th>
							<th>sex</th>
							<th>Department</th>
							<th>Job</th>
							<th>2023-12-05</th>
						</tr>


					</tbody>
				</table>
		</form>
	</div>
</div>
</div>
</head>

</html>