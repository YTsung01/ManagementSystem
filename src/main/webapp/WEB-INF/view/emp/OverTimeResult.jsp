<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	.watermark {
		content: "駁回";
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

.position-relative.watermark::after {
	display: var(--after-display1, none);
}

.position-relative::after {
	display: var(--after-display, none);
}

.position-relative.watermark::after {
	display: var(--after-display1, none);
}
</style>
<script type="text/javascript">
    $(document).ready(function() {
        // 判斷是否顯示浮水印
        var overTimeVerifyState = ${overTime.verifyState};

        if (overTimeVerifyState === 2) {
            document.documentElement.style.setProperty('--after-display', 'block');
        }
        
        if (overTimeVerifyState === 0) {
            document.documentElement.style.setProperty('--after-display1', 'block');
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

				<img src="/ManagementSystem/app/img/${ overTime.formId }.png"
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

<script type="text/javascript">
    $(document).ready(function() {
        // 判斷是否顯示浮水印
        if (${overTime.verifyState == 2}) {
            document.documentElement.style.setProperty('--after-display', 'block');
        }
        
        if (${overTime.verifyState == 0}) {
            document.documentElement.style.setProperty('--after-display1', 'block');
        }

        // 列印按鈕
        $('#printButton').on('click', function() {
            window.print();
        });
    });
</script>


<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>