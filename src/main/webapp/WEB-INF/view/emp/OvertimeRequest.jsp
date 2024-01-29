<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.UUID"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<!-- ${ employee }
<hr>
${overTimes}
<hr>
${overTimesbyId}
<hr>
${totalOvertimeHour} ${overIimeLeftHour} -->

<div class="container-xl mt-5">
	<!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->


	<%
	// 使用當前時間生成唯一的表單單號
	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	//String formNumber = dateFormat.format(new Date());
	%>

	<%
	//UUID uuid = UUID.randomUUID();
	//uuid.toString();
	%>
	<%
	//=uuid
	%>

	<sp:form modelAttribute="overTime" action="./add/${empBook.empId}"
		method="post" class="border rounded mx-auto p-4">
		<input type="hidden" name="formId" value="${overTime.formId}">
		<div class="row">
			<!-- 左側 -->
			<!-- <div class="col-12 col-md-10 border-end"> -->
			<p class="fs-3 fw-bold text-center">加班申請</p>

			<!-- 填表人 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">員工編號：</div>
				<div class="col-12 col-md-2 position-relative" name="empId">
					${empBook.empId }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">填表日期：</div>
				<div class="col-12 col-md-2 position-relative" name="overTimeDate">
					<p>${overTimeDate}</p>
				</div>
			</div>
			<!-- 申請人 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請人：</div>
				<div class="col-12 col-md-2 position-relative" name="empName">
					${ empBook.empName }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請部門：</div>
				<div class="col-12 col-md-2 position-relative" name="empDepartment">
					${ empBook.empDepartment  }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
			</div>
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請類型：</div>
				<div class="col-12 col-md-8">
					<label> <input type="radio" name="overtimeType"
						id="overtimeType" required value="1" /> 加班費
					</label> <label> <input type="radio" name="overtimeType"
						id="overtimeType" required value="2" /> 補休
					</label>
				</div>
			</div>

			<!-- 選擇加班類型 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請類型：</div>
				<div class="col-12 col-md-8">
					<label> <input type="radio" name="dayOrHoilday"
						id="dayOrHoilday" required value="1" /> 平日加班
					</label> <label> <input type="radio" name="dayOrHoilday"
						id="dayOrHoilday" required value="2" /> 假日加班
					</label>
					<button type="button"
						class="m-3 col-12 col-md-3 btn btn-light text-nowrap"
						name="searchDayoff" id="searchDayoff">查詢剩餘時數</button>
				</div>
			</div>

			<!-- 加班起始日 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">加班起始日：</div>
				<div class="col-12 col-md-2 position-relative">
					<input type="datetime-local" name="startTime" id="overTimeStart"
						class="form-control" style="width: auto;" min="2023-11-01"
						max="2023-12-31">
					<!-- 控制日期最大最小值 -->

				</div>

				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">結束日：</div>
				<div class="col-12 col-md-2 position-relative">
					<input type="datetime-local" name="endTime" id="overTimeEnd"
						class="form-control" style="width: auto;" min="2023-11-01"
						max="2023-12-31"> <span id="overTimeEndError"
						style="color: red;"></span>
					<!-- 控制日期最大最小值 -->
				</div>
			</div>

			<!-- 合計天數 -->
			<div class="row align-items-center pe-4 mb-5">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">合計時數：</div>
				<div class="col-12 col-md-2 position-relative">
					<!-- 添加一個隱藏的 input 元素 -->
					<input type="hidden" name="calculatedOverTimeHour"
						id="calculatedOverTimeHour"> <input type="text"
						name="applyHour" id="overTimeHour" class="form-control" required
						readonly>
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2 ">小時</div>
				</div>
				<button type="button"
					class="col-12 col-md-1 btn btn-light text-nowrap "
					name="calculatebtn" id="calculatebtn">計算</button>
				<span class="col-12 col-md-1  text-nowrap " id="overTimeStartError"
					style="color: red;"></span>
			</div>

			<!-- 留言內容 -->
			<div class="row  pe-4 mb-5 mt-3">
				<div class="col-12 col-md-2 text-md-end p-md-0">加班事由：</div>
				<div class="col-12 col-md-10 mb-4">
					<textarea name="reason" id="reason" class="form-control" rows="5"
						required></textarea>
				</div>
			</div>



			<!-- 最後 -->
			<div
				style="display: flex; justify-content: center; align-items: center;">
				<button type="submit" class="btn btn-primary align-items-center m-4"
					value="送出">送出</button>
				<button type="reset" class="btn btn-danger">清除</button>
			</div>
			<div>
				<input name="overTimeFormId" id="overTimeFormId" type="hidden"
					value="${overTimeformId}" />
			</div>


		</div>


		<div class="container mt-5">
			<!-- 模态框 -->
			<div class="modal fade" id="remainingHoursModal" tabindex="-1"
				role="dialog" aria-labelledby="remainingHoursModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="remainingHoursModalLabel">本月剩餘時數</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body" id="remainingHoursModalBody">
							<!-- 這裡顯示剩餘時數的內容 -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">關閉</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</sp:form>
</div>
<script>
        $(document).ready(function () {
        	 console.log('Calculate button clicked');
            $("#searchDayoff").click(function () {
            	 console.log('Calculate button clicked');
                
                var remainingHours = ${totalOvertimeHour}; 

                var modalBody = $("#remainingHoursModalBody");
                modalBody.text("本月剩餘時數為:  " + ${overTimeLeftHour} + "  小時");

                if (remainingHours < 10) {
                    modalBody.css('color', 'red');
                } else {
                    modalBody.css('color', ''); 
                }
             
                $("#remainingHoursModal").modal("show");
            });
        });
    </script>
<script>
$(document).ready(function () {
    $('#calculatebtn').on('click', function() {
        // 獲取起始和結束日期的值
        const startDateValue = $('#overTimeStart').val();
        const endDateValue = $('#overTimeEnd').val();

        // 檢查是否選擇了日期
        if (!startDateValue || !endDateValue) {
            // 若未選擇日期，顯示提示信息
           $('#overTimeStartError').text('請選擇加班日期');
            return;
        }else {
            $('#overTimeStartError').text(''); // 清除錯誤信息
        }

        const startDate = new Date(startDateValue);
        const endDate = new Date(endDateValue);
        
        if (startDate >= endDate) {
            $('#overTimeStartError').text('加班開始日期必須小於加班結束日期');
            return;
        } else {
            $('#overTimeStartError').text(''); // 清除錯誤信息
        }
        
       
        

        // 計算時間差
        const timeDiff = Math.abs(endDate.getTime() - startDate.getTime());

        // 計算相關數值
        const diffHours = Math.floor(timeDiff / (1000 * 60 * 60));

        // 計算結果
        let calculatedResult = diffHours;
 		if (calculatedResult < 1) {
        	$('#overTimeStartError').text('加班時數最小單位為1小時，請重新確認'); return;
        } else {
            $('#overTimeStartError').text(''); // 清除錯誤信息
        }
 		
 		// 檢查加班時數是否合理
        if (diffHours > 4  || !Number.isInteger(diffHours)) {
        	 $('#overTimeStartError').text('加班時數不可大於4小時，請重新確認');
            return;
        }
 		
        if (diffHours < 0  || !Number.isInteger(diffHours)) {
       	 $('#overTimeStartError').text('加班時數不可為空，請重新確認');
           return;
       }
 		
        // 將計算結果丟給隱藏的 input 元素
        $('#calculatedOverTimeHour').val(calculatedResult);

        // 更新顯示在 readonly 的 input 元素中
        $('#overTimeHour').val(calculatedResult);

        // 使用 console.log 顯示目前的計算值
        console.log('目前的計算值：', calculatedResult);
    });
});
</script>

<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>