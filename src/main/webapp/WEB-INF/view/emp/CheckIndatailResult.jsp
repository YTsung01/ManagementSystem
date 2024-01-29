<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


	<div class="d-flex justify-content-center mx-auto p-4">
		<div class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">出勤紀錄</p>

			<div>

				<div class="p-6">
					<form class="d-flex"
						action="/ManagementSystem/app/checkin/searchCheckIn" method="get">
						<input type="datetime-local" name="startDate" id="startDate"
							class="form-control" style="width: auto;" min="2023-09-01"
							max="2024-12-31"> <input type="datetime-local"
							name="endDate" id="endDate" class="form-control"
							style="width: auto;" min="2023-09-01" max="2023-12-31">
						<button class="btn btn btn-outline-success " type="submit">Search</button>
					</form>
				</div>


			</div>
			<table class="table table-bordered m-3 p-3 mx-auto ">

				<thead>
					<tr>
						<th>員工編號</th>
						<th>名稱</th>
						<th>部門</th>
						<th>上班時間</th>
						<th>下班時間</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="checkin" items="${CheckInfilter}">
						<tr>
							<td>${ empBook.empId }</td>
							<td>${ empBook.empName }</td>
							<td>${ empBook.empDepartment  }</td>
							<td
								style="color: ${checkin.checkInTime.hours > 9 && checkin.checkInTime.hours le 18 ? 'black' : 'red'};">
								<fmt:formatDate value="${ checkin.checkInTime  }"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td
								style="color: ${checkin.checkOutTime.hours > 9 && checkin.checkOutTime.hours le 18 ? 'black' : 'red'};">
								<fmt:formatDate value="${ checkin.checkOutTime  }"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>

