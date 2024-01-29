<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考勤系統</title>
<!-- 引入 Bootstrap 的 JavaScript 文件（需要先引入 jQuery）-->
	    <!-- 引入 jQuery -->
	    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">


<!-- 引入 Bootstrap 5 样式表 -->
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light "
		style="background-color: #4682b4" id="systemHeader">
		<div class="container-fluid">
			<a class="navbar-brand text-light " href="/ManagementSystem/app/main">考勤系統</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active text-light"
						aria-current="page" href="/ManagementSystem/app/main">首頁</a></li>

					</li>
					<div class="dropdown">
						<button class="btn dropdown-toggle text-light" type="button"
							id="dropdownMenuButton1" data-bs-toggle="dropdown"
							aria-expanded="false" style="background-color: #4682b4">
							出勤</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							<li><a class="dropdown-item" href="/ManagementSystem/app/checkin/${empBook.empId}">打卡</a></li>
							<li><a class="dropdown-item" href="/ManagementSystem/app/checkin/checkinresult/${empBook.empId}">出勤查詢</a></li>
						</ul>
					</div>

					<div class="dropdown">
						<button class="btn dropdown-toggle text-light" type="button"
							id="dropdownMenuButton1" data-bs-toggle="dropdown"
							aria-expanded="false" style="background-color: #4682b4">
							請假</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							<li><a class="dropdown-item" href="/ManagementSystem/app/takeOff/request">請假申請</a></li>
							<li><a class="dropdown-item" href="/ManagementSystem/app/takeOff/search/${empBook.empId}">請假查詢</a></li>
							<li><c:if test="${empBook != null && empBook.getLevelId() == 2}"><a class="dropdown-item" href="/ManagementSystem/app/takeOff/check">請假管理</a></c:if></li>
						</ul>
					</div>

					<div class="dropdown">
						<button class="btn dropdown-toggle text-light" type="button"
							id="dropdownMenuButton2" data-bs-toggle="dropdown"
							aria-expanded="false" style="background-color: #4682b4">
							加班</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
							<li><a class="dropdown-item"
								href="/ManagementSystem/app/overtime/request">加班申請</a></li>
							<li><a class="dropdown-item"
								href="/ManagementSystem/app/overtime/search/${empBook.empId}">加班查詢</a></li>
							<li><c:if test="${empBook != null && empBook.getLevelId() == 2}"><a class="dropdown-item"
								href="/ManagementSystem/app/overtime/check">加班管理</a></c:if></li>
						</ul>
					</div>

					<li class="nav-item"><a class="nav-link text-light "
						href="./Salary.jsp">薪資查詢</a></li>

				</ul>

			</div>
			<div class=" nav-item"><a
				href="/ManagementSystem/app/auth/login" class="text-light">登出</a>
		</div>
	</nav>

</body>
</html>
