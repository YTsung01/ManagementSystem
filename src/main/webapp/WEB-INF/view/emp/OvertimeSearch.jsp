
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 表單元素 
${ overTime }

<hr>
${empBook }-->



<div class="d-flex justify-content-center mx-auto p-4">
	<div class="border rounded mx-auto p-4 ">
		<p class="fs-3 fw-bold text-center">加班紀錄</p>
		<div class="p-3">
			<table class="table table-bordered ">
				<thead>
					<tr>
						<th hidden="hidden">表單編號</th>
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
						<th>修改</th>
						<th>刪除</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="overtimes" items="${overTime}">
						<tr>
							<td hidden="hidden">${ overtimes.formId }</td>
							<td>${ empBook.empId }</td>
							<td>${ empBook.empName }</td>
							<td>${ empBook.empDepartment  }</td>
							<td><fmt:formatDate value="${ overtimes.startTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${ overtimes.endTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${overtimes.applyHour}</td>
							<td>${overtimes.reason}</td>
							<td
								style="${overtimes.verifyState == 0 ? 'color: red;' : (overtimes.verifyState == 2 ? 'color: black;' : (overtimes.verifyState == 1 ? 'color: blue;' : ''))} ">
								${overtimes.verifyState == 2 ? '審核中' : (overtimes.verifyState == 1 ? '同意' : '駁回')}</td>
							<td>${empBossName}</td>
							<td><a
								href="http://localhost:8080/ManagementSystem/app/overtime/deatil/${ overtimes.formId }"
								class="btn  align-items-center "
								style="background-color: #D6DCDB">詳情</a></td>
							<td><c:choose>
									<c:when test="${overtimes.verifyState eq 1}">
										<span class="btn btn-disabled align-items-center"
											style="background-color: #A2AFA6; cursor: not-allowed;">修改</span>
									</c:when>
									<c:otherwise>
										<a
											href="http://localhost:8080/ManagementSystem/app/overtime/show/${overtimes.formId}"
											class="btn align-items-center"
											style="background-color: #A2AFA6" id="detail">修改</a>
									</c:otherwise>
								</c:choose></td>
							<td><a
								href="http://localhost:8080/ManagementSystem/app/overtime/delete/${ overtimes.formId }"
								class="btn  align-items-center "
								style="color: white; background-color: #CC5F5A;">刪除</a></td>
						</tr>

					</c:forEach>

				</tbody>
			</table>
			<p>目前總加班時數:${totalOvertimeHour }小時</p>
			<p>剩餘加班時數: ${overTimeLeftHour}小時</p>
			<p>待審核加班時數: ${nonCheckOutOverTimeHour} 小時</p>



			<div class="p-6">
				<form class="d-flex"
					action="/ManagementSystem/app/overtime/searchOvertime" method="get"
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

<script>
	// 假设 ovetrimes.verifyState 包含 verifyState 的值（0 或其他值）
	var verifyState = $
	{
		overtimes.verifyState
	}; // 这里使用 JSP 表达式获取值

	// 获取链接元素
	var linkElement = document.getElementById('detail');

	// 根据 verifyState 值决定是否禁用链接
	if (verifyState === 1) {
		// 当 verifyState 为 0 时，禁用链接
		linkElement.style.pointerEvents = 'none'; // 禁用链接的点击事件
		linkElement.style.color = 'red'; // 修改链接颜色（可选）
		// 如果你想添加其他样式或效果，可以在这里进行设置
	} else {
		// 如果 verifyState 不为 0，保持链接可用
		linkElement.style.pointerEvents = 'auto'; // 恢复链接的点击事件
		linkElement.style.color = ''; // 恢复链接颜色（可选）
		// 还原其他样式或效果，如果有的话
	}
</script>
<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>
