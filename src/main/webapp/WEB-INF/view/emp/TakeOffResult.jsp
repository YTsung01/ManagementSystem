<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
/* 在打印時顯示審核中浮水印 */
@media print {
	.position-relative::after {
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



<script type="text/javascript">
    $(document).ready(function() {
        // 判斷是否顯示浮水印
        var takeOffVerifyState = '${takeOff.verifyState}' ;
        console.log(takeOffVerifyState)
        
        if (takeOffVerifyState === 2) {
            document.documentElement.style.setProperty('--after-display', 'block');
        }
        
        if (takeOffVerifyState === 0) {
        	 $('#printButton').prop('disabled', true);
            }
        

        // 列印按鈕
        $('#printButton').on('click', function() {
            window.print();
        });
    });
</script>

<!-- ${form}
<hr>
${overTime} -->



<div class="container-xl mt-5 ">

	<div class="d-flex justify-content-center mx-auto p-6 ">
		<div class="border rounded mx-auto px-4  position-relative">
			<p class="fs-3 fw-bold text-center pt-3">加班申請單</p>
			
			<button id="printButton"
				class="btn position-absolute top-10 start-20 "
				style="background-color: #e3f2fd">列印</button>

			<p class="fs-6 fw-bold text-end">

				<img
					src="/ManagementSystem/app/img/qrcodes/${ takeOff.formId }.png"
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
							<td width="200px" align='center' valign="middle">${ form.applier }</td>
							<th width="100px" align='center' valign="middle">申請人</th>
							<td width="200px" align='center' valign="middle">${ applierName }</td>

						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">代理人編號</th>
							<td width="200px" align='center' valign="middle">${ takeOff.agent }</td>
							<th width="100px" align='center' valign="middle">代理人</th>
							<td width="200px" align='center' valign="middle">${ agentName }</td>

						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">請假假別</th>
							<td colspan="1" width="200px" align='center' valign="middle">${takeoffType}</td>
							<th width="100px" align='center' valign="middle">申請時數</th>
							<td colspan="1" width="200px" align='center' valign="middle">
								${takeOff.takeoffDay}&nbsp;天&nbsp;&nbsp;&nbsp;${takeOff.takeoffHour}&nbsp;小時</td>
						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">加班事由</th>
							<td colspan="3" width="200px" height="100px" align='center'
								valign="middle">${takeOff.reason}</td>
						</tr>



						<tr>
							<th width="100px" align='center' valign="middle">開始日期</th>
							<td width="200px" colspan="1"><fmt:formatDate
									value="${takeOff.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<th width="100px" align='center' valign="middle">結束日期</th>
							<td width="200px" colspan="1"><fmt:formatDate
									value="${takeOff.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>


						</tr>
						<tr>
							<th width="100px" align='center' valign="middle">備註</th>
							<td colspan="3" width="200px" height="100px" align='center'
								valign="middle">${takeOff.checkReason}</td>
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

<script type="text/javascript">
    $(document).ready(function() {
        // 判斷是否顯示浮水印
        if (${takeOff.verifyState == 2}) {
            document.documentElement.style.setProperty('--after-display', 'block');
        }
        
        if (${takeOff.verifyState == 0}) {
            document.documentElement.style.setProperty('--after-display1', 'block');
        }

        // 列印按鈕
        $('#printButton').on('click', function() {
            window.print();
        });
    });
</script>


<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>