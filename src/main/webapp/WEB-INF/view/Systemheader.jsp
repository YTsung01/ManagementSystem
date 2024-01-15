<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Layout</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #4682b4">
  <div class="container-fluid">
    <a class="navbar-brand text-light " href="#">考勤系統</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active text-light" aria-current="page" href="/ManagementSystem/app/main">首頁</a>
        </li>
        
          
        </li>
         <li class="nav-item">
          <a class="nav-link text-light " href="/ManagementSystem/app/checkin/">打卡</a>
        </li>
        
        
    <div class="dropdown">
	  <button class="btn dropdown-toggle text-light" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false"
	  style="background-color: #4682b4">
	    請假
	  </button>
	  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
	    <li><a class="dropdown-item" href="./LeaveRequest.jsp">請假申請</a></li>
	    <li><a class="dropdown-item" href="./LeaveSearch.jsp">請假查詢</a></li>
	    <li><a class="dropdown-item" href="./LeaveCheck.jsp">請假管理</a></li>
	  </ul>
	</div>
	
	<div class="dropdown">
	  <button class="btn dropdown-toggle text-light" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false"
	  style="background-color: #4682b4">
	    加班
	  </button>
	  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
	    <li><a class="dropdown-item" href="./OvertimeRequest.jsp">加班申請</a></li>
	    <li><a class="dropdown-item" href="./OvertimeSearch.jsp">加班查詢</a></li>
	    <li><a class="dropdown-item" href="./OvertimeCheck.jsp">加班管理</a></li>
	  </ul>
	</div>
        
        
         <li class="nav-item">
          <a class="nav-link text-light " href="./Salary.jsp">薪資查詢</a>
        </li>
        
      </ul>
     
     
     
    </div>
    <i class="bi bi-person-circle text-light h5 mb-0 d-none d-lg-block"></i>
  </div>
</nav>


</body>
</html>