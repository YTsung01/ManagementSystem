<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.UUID"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>


${ employee }
<hr>
${overTimes}
<hr>
${overTimesbyId}

<div class="container-xl mt-5">
	<!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->


	<%
	// 使用當前時間生成唯一的表單單號
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String formNumber = dateFormat.format(new Date());
	%>

	<%
	UUID uuid = UUID.randomUUID();
	uuid.toString();
	%>
	<%=uuid%>

	<sp:form modelAttribute="overTime" action="./add/${employee.empId}"
		method="post" class="border rounded mx-auto p-4">
		<div class="row">
			<!-- 左側 -->
			<!-- <div class="col-12 col-md-10 border-end"> -->
			<p class="fs-3 fw-bold text-center">加班申請</p>

			<!-- 填表人 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">員工編號：</div>
				<div class="col-12 col-md-2 position-relative" name="empId">
					${employee.empId }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">填表日期：</div>
				<div class="col-12 col-md-2 position-relative" name="overTimeDate">
					<p>${overTimeDate}</p>
					<!--<input type="date" name="a_date" id="a_date" class="form-control"
						style="width: auto;" min="2023-11-01" max="2030-12-31">-->
					<!-- 控制日期最大最小值 -->

				</div>
			</div>

			<!-- 申請人 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請人：</div>
				<div class="col-12 col-md-2 position-relative" name="empName">
					${ employee.empName }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請部門：</div>
				<div class="col-12 col-md-2 position-relative" name="empDepartment">
					${ employee.empDepartment  }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
			</div>
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請類型：</div>
				<div class="col-12 col-md-8">
					<sp:radiobuttons path="overTimeTypeId"
						items="${overTimeTypeDatas }" itemLabel="name" itemValue="id"
						cssClass="element-margin" />
				</div>
			</div>

			<!-- 選擇加班類型 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請類型：</div>
				<div class="col-12 col-md-8">
					<sp:radiobuttons path="overTimeTypeForDayId"
						items="${overTimeTypeForDayDatas }" itemLabel="name"
						itemValue="id" cssClass="element-margin" />
					<button type="button"
						class="m-3 col-12 col-md-3 btn btn-light text-nowrap"
						name="searchDayoff" id="searchDayoff">查詢剩餘時數</button>
				</div>
			</div>

			<!-- 請假起始日 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">加班起始日：</div>
				<div class="col-12 col-md-2 position-relative">
					<input type="datetime-local" name="overTimeStart"
						id="overTimeStart" class="form-control" style="width: auto;"
						min="2023-11-01" max="2023-12-31">
					<!-- 控制日期最大最小值 -->
				</div>

				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">結束日：</div>
				<div class="col-12 col-md-2 position-relative">
					<input type="datetime-local" name="overTimeEnd" id="overTimeEnd"
						class="form-control" style="width: auto;" min="2023-11-01"
						max="2023-12-31">
					<!-- 控制日期最大最小值 -->
				</div>
			</div>

			<!-- 合計天數 -->
			<div class="row align-items-center pe-4 mb-5">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">合計時數：</div>
				<div class="col-12 col-md-2 position-relative">
					<input type="text" name="overTimeHour" id="overTimeHour"
						class="form-control" required>
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2 ">時</div>
				</div>
				<button type="button"
					class="col-12 col-md-1 btn btn-light text-nowrap "
					name="calculatebtn" id="calculatebtn">計算</button>


				<!-- 留言內容 -->
				<div class="row  pe-4 mb-5 mt-3">
					<div class="col-12 col-md-2 text-md-end p-md-0">加班事由：</div>
					<div class="col-12 col-md-10 mb-4">
						<textarea name="overTimeReason" id="overTimeReason"
							class="form-control" rows="5" required></textarea>
					</div>
				</div>



				<!-- 最後 -->
				<div
					style="display: flex; justify-content: center; align-items: center;">
					<button type="submit"
						class="btn btn-primary align-items-center m-4" value="送出">送出</button>
					<button type="reset" class="btn btn-danger">清除</button>
				</div>
				<div>
				<input name="overTimeFormId" id="overTimeFormId" type="hidden" value="${overTimeformId}" /> 
				<input name="empDeptno" id="empDeptno" type="hidden" value="${ employee.empDeptno }"/>		
				<input name="empJob" id="empJob" type="hidden" value="${ employee.empJob }"/>	
				<sp:input path="overTimeLeftHour" id="overTimeLeftHour" type="hidden" value="${overTimeLeftHour} " />
				<sp:input path="VerifyState" id="VerifyState" type="hidden" value="2" />
				<sp:input path="overTimeCheckReason" id="overTimeCheckReason" type="hidden" value="${OverTimeCheckReason }" /> 
				<sp:input path="employee" id="employee" type="hidden" value="${ employee }"/>

				</div>
				
				<!-- 	<div class="col-12 col-md-2 position-relative" name="empDeptno">
					${ employee.empDeptno } 
					<div class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
					<div class="col-12 col-md-2 position-relative" name="empJob">
					${ employee.empJob }
					<div class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div> -->




			</div>
		</div>
	</sp:form>
</div>


<!-- 表單檢查方法 添加一個onsubmit 一開始return false 先設定讓前端擋掉所以後端不會出現資料 改成true才會讓後端真正接收 -->
<script>
	function check() {
		let email = $("#email").val();
		if (email == '') {
			alert('email沒有填寫');
			return false;
		}
		console.log('print' + email);
		return true;
	}
</script>


<script>
	let resultDate_msg = $('#resultDate').next();
	let resultTime_msg = $('#overTimeHour').next();

	$('#calculatebtn').on('click', function() {
		startDate = new Date($('#overTimeStart').val());
		endDate = new Date($('#overTimeEnd').val());

		var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
		var diffDays = Math.floor(timeDiff / (1000 * 3600 * 24));
		var diffHours = Math.ceil(timeDiff / (1000 * 60 * 60));
		var diffDayHours = diffHours % 24;
		var difftotalDays = Math.floor(diffHours / 24);
		//var diffDayHours = diffHours- diffDays*24;

		if (diffDays < 1) {
			resultDate_msg.text('0' + '天');
			//resultTime_msg.text(diffHours+ '時');

		} else {
			resultDate_msg.text(difftotalDays + '天');

			//resultTime_msg.text(diffDayHours+ '時');

		}

		if (diffDayHours > 8) {

			resultTime_msg.text('8' + '時');
		} else {

			resultTime_msg.text(diffDayHours + '時');
		}

	});
</script>


<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>