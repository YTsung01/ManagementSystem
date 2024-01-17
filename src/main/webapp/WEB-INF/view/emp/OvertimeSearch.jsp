<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<html>


<head>
${ overtimes }

<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">

		<form action="http://localhost:8080/ManagementSystem_my/checkin"
			method="post" class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">加班查詢</p>
			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th>員工編號</th>
							<th>名稱</th>
							<th>部門</th>
							<th>加班開始時間</th>
							<th>加班結束時間</th>
							<th>加班時數</th>
							<th>加班原因</th>
							<th>審核狀態</th>
							<th>審核人</th>
							<th>詳情</th>

						</tr>
					</thead>
					<tbody>


						<tr>
							<td>${ employee.empId }</td>
							<td>${ employee.empName }</td>
							<td>${ employee.empDepartment  }</td>
							<td>${ overtimes  }</td>
							<td>2023-12-19 18:00</td>
							<td>1</td>
							<td>工作太多</td>
							<td>同意</td>
							<td>主管</td>
							<td><a
								href="http://localhost:8080/ManagementSystem_my/OverTimeResult.jsp">詳情</a></td>

						</tr>
						<tr>
							<td>001</td>
							<td>Amy</td>
							<td>Sales</td>
							<td>2023-12-30 17:00</td>
							<td>2023-12-30 18:00</td>
							<td>1</td>
							<td>工作太多</td>
							<td>駁回</td>
							<td>主管</td>
							<td><a
								href="http://localhost:8080/ManagementSystem_my/OverTimeResult.jsp">詳情</a></td>

						</tr>



					</tbody>
	


				</table>
		</form>
		<p>總計加班時數:</p>
		<p>剩餘加班時數:</p>

		<div class="dropdown">
			<a class="btn  dropdown-toggle" style="background-color: #ccdce8"
				href="#" role="button" id="dropdownMenuLink"
				data-bs-toggle="dropdown" aria-expanded="false"> 查詢其他月份... </a>

			<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<li><a class="dropdown-item" href="#">2023-11月</a></li>
				<li><a class="dropdown-item" href="#">2023-10月</a></li>
				<li><a class="dropdown-item" href="#">2023-9月</a></li>
				<li><a class="dropdown-item disabled">其餘月份請洽人資</a></li>
			</ul>
		</div>

	</div>
</div>
</body>
</html>