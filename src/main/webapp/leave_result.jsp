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
							<th width="100px" ;align='center' valign="middle">申請部門</th>
							<td  width="200px" ;align='center' valign="middle">123</td>
							<th width="100px" ;align='center' valign="middle">申請日期</th>
							<td width="200px" ;align='center' valign="middle">123</td>
						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">申請人編號</th>
							<td width="200px" ;align='center' valign="middle">123</td>
							<th width="100px" ;align='center' valign="middle">申請人</th>
							<td width="200px" ;align='center' valign="middle">123</td>

						</tr>
							<th width="100px" ;align='center' valign="middle">請假類型</th>
							<td colspan="3" ;width="200px" ;align='center' valign="middle">123</td>
						</tr>
						<tr>
							<th width="100px" ;align='center' valign="middle">請假事由</th>
							<td colspan="3" ;width="200px" ; height="100px" ;align='center'
								valign="middle">123</td>
						</tr>

					</thead>
					<tbody>
						<tr>
							<th width="100px" ;align='center' valign="middle">開始日期</th>
							<td width="200px" ;colspan="2">123</td>
							<th width="100px" ;align='center' valign="middle">結束日期</th>
							<td width="200px" ;colspan="3">123</td>

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
			<li>員工請假規定及薪資核定辦法依勞基法規定</li>
			<li>簽章須包含部門主管及代理人同意</li>
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