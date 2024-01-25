<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<style>
/* 在打印时隐藏 Systemheader.jsp */
@media print 
{ 
#systemHeader
		{
		display: none;
	}
}
{
height


:

 

100


%;
overflow


:

 

hidden




}
</style>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ${form}
<hr>
${overTime} -->



<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-6 ">
		<div class="border rounded mx-auto px-4  position-relative">
			<p class="fs-3 fw-bold text-center pt-3">加班申請單</p>
			<button id="printButton"
				class="btn position-absolute top-10 start-20 "
				style="background-color: #e3f2fd">列印</button>

			<p class="fs-6 fw-bold text-end">

				<img
					src="http://localhost:8080/ManagementSystem/images/qrcodes/${ overTime.formId }.png"
					class="d-block mx-auto w-60 rounded c-p">

			</p>
			<div class="p-3">
				<table class="table table-bordered common-table " id="table1">
					<tbody>
						<tr>
							<th width="100px" align='center' valign="middle">申請部門</th>
							<td width="200px" align='center' valign="middle">${ empBook.empDepartment  }</td>
							<th width="100px" align='center' valign="middle">申請日期</th>
							<td width="200px" align='center' valign="middle">${ form.applyDate  }</td>
						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">申請人編號</th>
							<td width="200px" align='center' valign="middle">${ empBook.empId }</td>
							<th width="100px" align='center' valign="middle">申請人</th>
							<td width="200px" align='center' valign="middle">${ empBook.empName }</td>

						</tr>
						<tr>
						<th width="100px" align='center' valign="middle">加班類型</th>
						<td colspan="1" width="200px" align='center' valign="middle">${overTimetype}&nbsp;&nbsp;&nbsp;${DayOrHoilday}</td>
						<th width="100px" align='center' valign="middle">申請時數</th>
						<td colspan="1" width="200px" align='center' valign="middle">
							${overTime.applyHour}&nbsp;小時</td>
						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">加班事由</th>
							<td colspan="3" width="200px" height="100px" ;align='center'
								valign="middle">${overTime.reason}</td>
						</tr>



						<tr>
							<th width="100px" align='center' valign="middle">開始日期</th>
							<td width="200px" colspan="1"><fmt:formatDate
									value="${overTime.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<th width="100px" align='center' valign="middle">結束日期</th>
							<td width="200px" colspan="1"><fmt:formatDate
									value="${overTime.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>


						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">備註</th>
							<td colspan="3" width="200px" height="100px" align='center'
								valign="middle">${overTime.checkReason}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<ol>
				<p>【備註事項】:</p>
				<li>加班採預先申請制度</li>
				<li>加班時數請以小時為單位計算</li>
			</ol>


			<table class="table table-bordered common-table" id="table1">
				<thead>
					<tr>
						<th width="100px" align='center' valign="middle">部門主管</th>
						<td width="100px" align='center' valign="middle" height="100px">${ empBossName }</td>
						<th width="100px" align='center' valign="middle">申請人</th>
						<td width="100px" align='center' valign="middle" height="100px">${ empBook.empName }</td>
					</tr>
				</thead>
			</table>

		</div>
	</div>
</div>
<!-- JavaScript 代码 -->
<script type="text/javascript">
	$(document).ready(function() {
		// 点击按钮时执行打印操作
		$('#printButton').on('click', function() {
			window.print();
		});
	});
</script>
