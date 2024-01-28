
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 表單元素 
<hr>
${empBook }-->


${empBook }
${ takeOff }

<div class="d-flex justify-content-center mx-auto p-4">
	<div class="border rounded mx-auto p-4 ">
		<p class="fs-3 fw-bold text-center">請假紀錄</p>
		<div class="p-3">
			<table class="table table-bordered ">
				<thead>
					<tr>
						<th hidden="hidden">表單編號</th>
						<th>員工編號</th>
						<th>名稱</th>
						<th>部門</th>
						<th>請假開始時間</th>
						<th>請假結束時間</th>
						<th>請假時數</th>
						<th>請假原因</th>
						<th>審核狀態</th>
						<th>審核人</th>
						<th>詳情</th>
						<th>修改</th>
						<th>刪除</th>

					</tr>
				</thead> 
				<tbody>
					<c:forEach var="takeOffs" items="${takeOff}">
						<tr>
							<td hidden="hidden">${ takeOffs.formId }</td>
							<td>${ empBook.empId }</td>
							<td>${ empBook.empName }</td>
							<td>${ empBook.empDepartment  }</td>
							<td><fmt:formatDate value="${ takeOffs.startTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${ takeOffs.endTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${takeOffs.applyHour}</td>
							<td>${takeOffs.reason}</td>
							<td style="${takeOffs.verifyState == 0 ? 'color: red;' : ''} ">
								${takeOffs.verifyState == 2 ? '審核中' : (takeOffs.verifyState == 1 ? '同意' : '駁回')}</td>
							<td>${empBossName}</td>
							<td><a
								href="http://localhost:8080/ManagementSystem/app/takeOff/deatil/${ takeOffs.formId }" class="btn  align-items-center "
								style="background-color:#D6DCDB">詳情</a></td>
							<td><a 
								href="http://localhost:8080/ManagementSystem/app/takeOff/update/${ takeOffs.formId }"
								class="btn  align-items-center "
								style="background-color: #A2AFA6">修改</a></td>
							<td><a href="javascript:void(0);"
								onClick="updateItem(${ item.itemId })"
								class="btn  align-items-center "
								style="color: white; background-color: #CC5F5A;">刪除</a></td>
						</tr>

					</c:forEach>

				</tbody>
			</table> 
			<p>目前總請假時數:${totalTakeOffHour }小時</p>
			<p>剩餘請假時數: ${TakeOffLeftHour}小時</p>
			<p>待審核請假時數: ${nonCheckTakeOffHour} 小時</p>



			<div class="p-6">
				<form class="d-flex"
					action="/ManagementSystem/app/takeOff/searchTakeOff" method="get"
					onsubmit="return validateForm();">
					<input type="datetime-local" name="startDate" id="startDate"
						class="form-control" style="width: auto;" min="2023-09-01"
						max="2024-12-31"> <input type="datetime-local"
						name="endDate" id="endDate" class="form-control"
						style="width: auto;" min="2023-09-01" max="2023-12-31">
					<button class="btn btn btn-outline-success " type="submit"
						id="searchbutton">Search</button>
				</form>
			</div>

		</div>
	</div>
</div>



<script>
	function validateForm() {
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;

		if (startDate === "" || endDate === "") {
			alert("請選擇日期");
			return false;
		}

		return true;
	}
</script>