<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
${form}
<hr>
${overTime}
<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">

		<form action="http://localhost:8080/ManagementSystem_my/checkin"
			method="post" class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">加班申請單</p>

			<p class="fs-6 fw-bold text-end">

				<img
					src="${pageContext.request.contextPath}/images/${overTime.formId}.png"
					class="d-block mx-auto w-80 rounded c-p">
			</p>
			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th width="100px" ;align='center' valign="middle">申請部門</th>
							<td width="200px" ;align='center' valign="middle">${ empBook.empDepartment  }</td>
							<th width="100px" ;align='center' valign="middle">申請日期</th>
							<td width="200px" ;align='center' valign="middle">${ form.applyDate  }</td>
						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">申請人編號</th>
							<td width="200px" ;align='center' valign="middle">${ empBook.empId }</td>
							<th width="100px" ;align='center' valign="middle">申請人</th>
							<td width="200px" ;align='center' valign="middle">${ empBook.empName }</td>

						</tr>
						<th width="100px" ;align='center' valign="middle">加班類型</th>
						<td colspan="3" ;width="200px" ;align='center' valign="middle">${overTimetype}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${DayOrHoilday}</td>
						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">加班事由</th>
							<td colspan="3" ;width="200px" ; height="100px" ;align='center'
								valign="middle">${overTime.reason}</td>
						</tr>

					</thead>
					<tbody>
						<tr>
							<th width="100px" align='center' valign="middle">開始日期</th>
							<td width="200px" colspan="2">${overTime.startTime}</td>
							<th width="100px" align='center' valign="middle">結束日期</th>
							<td width="200px" colspan="3">${overTime.endTime}</td>

						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">備註</th>
							<td colspan="3" width="200px"  height="100px" align='center'
								valign="middle">${overTime.checkReason}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p>總計加班時數:</p>
			<p>剩餘加班時數:</p>
			<ol>
				<p>【備註事項】:</p>
				<li>加班採預先申請制度</li>
				<li>加班時數請以小時為單位計算</li>
			</ol>

			<form>
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th width="100px" align='center' valign="middle">部門主管</th>
							<td width="100px" align='center' valign="middle"height="100px">${ empBossName }</td>
							<th width="100px" align='center' valign="middle">申請人</th>
							<td width="100px" align='center' valign="middle"height="100px">${ empBook.empName }</td>
						</tr>
					</thead>
				</table>
			</form>
		</form>
	</div>