<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<html>


<head>

<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">

		<form action="http://localhost:8080/ManagementSystem_my/checkin"
			method="post" class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">請假申請單</p>
			<p class="fs-6 fw-bold text-end">申請單號: ______-______-______</p>
			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th>員工編號</th>
							<td colspan="4">123</td>


							<th>申請人</th>
							<td colspan="4">123</td>

						</tr>
						<tr>
							<th>員工編號</th>
						</tr>
						<tr>
							<th>員工編號</th>
						</tr>

					</thead>
					<tbody>


						<tr>
							<td>001</td>
							<td>Amy</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							
						


						</tr>
						<tr>
							<td>001</td>
							<td>Amy</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							<td>Sales</td>
							
						

						</tr>



					</tbody>


				</table>
		</form>
		<p>總計請假時數:</p>
		<p>剩餘請假時數:</p>

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
