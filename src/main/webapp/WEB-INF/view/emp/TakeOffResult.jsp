<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<style>
/* 在打印時顯示審核中浮水印 */
@media print {
    :after {
        content: "審核中";
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-size: 250px;
        color: red;
        pointer-events: none;
        display: none; /* 預設隱藏 */
    }

    /* 在打印時隱藏 Systemheader.jsp */
    #systemHeader, #printButton {
        display: none;
    }
}

/* 在 JS 中動態設置 display 值 */
.position-relative::after {
    display: var(--after-display, none);
}
</style>

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
							<th width="100px" ;align='center' valign="middle">申請部門</th>
							<td  width="200px" ;align='center' valign="middle">${ empBook.empDepartment  }</td>
							<th width="100px" ;align='center' valign="middle">申請日期</th>
							<td width="200px" ;align='center' valign="middle">${ form.applyDate  }</td>
						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">申請人編號</th>
							<td width="200px" ;align='center' valign="middle">${ empBook.empId }</td>
							<th width="100px" ;align='center' valign="middle">申請人</th>
							<td width="200px" ;align='center' valign="middle">${ empBook.empName }</td>

						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">代理人編號</th>
							<td width="200px" ;align='center' valign="middle">123</td>
							<th width="100px" ;align='center' valign="middle">代理人</th>
							<td width="200px" ;align='center' valign="middle">123</td>
						<tr>
							<th width="100px" ;align='center' valign="middle">請假類型</th>
							<td colspan="3" ;width="200px" ;align='center' valign="middle">${ takeOff.takeOfftype }</td>
						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">請假事由</th>
							<td colspan="3" ;width="200px" ; height="100px" ;align='center'
								valign="middle">${ takeOff.reason }</td>
						</tr>

					</thead>
					<tbody>
						<tr>
							<th width="100px" ;align='center' valign="middle">開始日期</th>
							<td width="200px" ;colspan="2">${ takeOff.startTime }</td>
							<th width="100px" ;align='center' valign="middle">結束日期</th>
							<td width="200px" ;colspan="3">${ takeOff.endTime }</td>

						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">備註</th>
							<td colspan="3" ;width="200px" ; height="100px" ;align='center'
								valign="middle">123</td>
						</tr>
					</tbody>
				</table>
		</form>
		<p>總計請假時數:</p>
		<p>剩餘請假時數:</p>
		<ol>
			<p>【備註事項】:</p>
			<li>員工請假規定及薪資核定辦法依照勞基法規定</li>
			<li>請假需經過部門主管以及代理人簽章</li>
		</ol>
		
		<form>
			<table class="table table-bordered ">
				<thead>
					<tr>
						<th width="100px" ;align='center' valign="middle">部門主管</th>
						<td width="100px" ; height="100px"></td>
						<th width="100px" ;align='center' valign="middle">代理人</th>
						<td width="100px" ; height="100px"></td>
						<th width="100px" ;align='center' valign="middle">申請人</th>
						<td width="100px" ; height="100px"></td>
					</tr>
				</thead>
			</table>
		</form>

	</div>
</div>
</body>
</html>