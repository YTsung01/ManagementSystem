<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp" %>


<html>
	<head>
	
	<div class="d-flex justify-content-center mx-auto p-4"  >
	<table  class="table table-bordered ">
	<thead>
				<tr  class="table-secondary">
					<th>員工編號</th>
					<th>名稱</th>
					<th>部門</th>
					<th>職位</th>
					<th>基本薪資</th>
					<th>請假時數</th>
					<th>加班時數</th>
					<th>考核加薪</th>
					<th>其他薪資異動</th>
		
				</tr>
	</thead>
  <tbody>
 		<tr>		
			<th>id</th>
			<th>Nickname</th>
			<th>Department</th>
			<th>Job</th>
			<th>基本薪資</th>
			<th>請假時數</th>
			<th>加班時數</th>
			<th>考核加薪</th>		
			<th>其他薪資異動</th>		
		</tr>

 
  </tbody>
</table>

</div>


<div class="d-flex justify-content-center mx-auto p-4 w-50"  >
	<table  class="table table-bordered ">
		<tr>
			<th>薪資總額</th>
			<th>salary</th>
			<th>
				<div class="dropdown">
				  <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
				    月份
				  </button>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" href="#">2023-09</a></li>
				    <li><a class="dropdown-item" href="#">2023-10</a></li>
				    <li><a class="dropdown-item" href="#">2023-11</a></li>
				    <li><a class="dropdown-item" href="#">其他月份請洽人資</a></li>
				  </ul>
				</div>
			</th>
	
		</tr>


 
  </tbody>
</table>

</div>
		
	</head>
		
</html>
