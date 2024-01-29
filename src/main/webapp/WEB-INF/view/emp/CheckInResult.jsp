<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<body>
	<div class="d-flex justify-content-center mx-auto p-4">
		<div class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">本月出勤紀錄</p>
			<div class="p-3">
				<table class="table table-bordered ">

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

						<c:forEach var="checkin" items="${CheckIndata}">
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
			<p>總計遲到次數: ${lateCount}</p>

			<div>

				<div class="p-6">
					<form class="d-flex"	
						action="/ManagementSystem/app/checkin/searchCheckIn" method="get" onsubmit="return validateForm();">
						<input type="datetime-local" name="startDate" id="startDate"
							class="form-control" style="width: auto;" min="2023-09-01"
							max="2024-12-31"> <input type="datetime-local"
							name="endDate" id="endDate" class="form-control"
							style="width: auto;" min="2023-09-01" max="2023-12-31">
						<button class="btn btn btn-outline-success " type="submit" id="searchbutton">Search</button>
					</form>
				</div>
				<c:if test="${not empty error}">
					<div style="color: red;">${error}</div>
				</c:if>

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
    
<<<<<<< HEAD
    	<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>
=======
    	<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>
>>>>>>> branch 'master' of https://github.com/YTsung01/ManagementSystem.git
