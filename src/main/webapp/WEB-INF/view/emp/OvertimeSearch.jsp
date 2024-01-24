
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 表單元素 -->
${ overTime }

<hr>
${empBook }



<div class="d-flex justify-content-center mx-auto p-4">
	<div class="border rounded mx-auto p-4 ">
		<p class="fs-3 fw-bold text-center">加班紀錄</p>
		<div class="p-3">
			<table class="table table-bordered ">
				<thead>
					<tr>
						<th >表單編號</th>
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
					<c:forEach var="overtimes" items="${overTime}" >
						<tr>
							<td>${ overtimes.formId }</td>
							<td>${ empBook.empId }</td>
							<td>${ empBook.empName }</td>
							<td>${ empBook.empDepartment  }</td>
							<td><fmt:formatDate value="${ overtimes.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${ overtimes.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${overtimes.applyHour}</td>
							<td>${overtimes.reason}</td>
							<td>${overtimes.verifyState}</td>
							<td>${empBossName}</td>
							<td><a
								href="http://localhost:8080/ManagementSystem/app/overtime/deatil/${ overtimes.formId }">詳情</a></td>
						</tr>

					</c:forEach>
					
				</tbody>
			</table>
			<p>目前總加班時數:${totalOvertimeHour }</p>
			<p>剩餘加班時數: ${overTimeLeftHour}小時</p>
			<p>待審核加班時數: ${nonCheckOutOverTimeHour} 小時</p>
			
			
			
				<div class="p-6">
					<form class="d-flex"	
						action="/ManagementSystem/app/overtime/searchOvertime" method="get" onsubmit="return validateForm();">
						<input type="datetime-local" name="startDate" id="startDate"
							class="form-control" style="width: auto;" min="2023-09-01"
							max="2024-12-31"> <input type="datetime-local"
							name="endDate" id="endDate" class="form-control"
							style="width: auto;" min="2023-09-01" max="2023-12-31">
						<button class="btn btn btn-outline-success " type="submit" id="searchbutton">Search</button>
					</form>
				</div>
			

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

