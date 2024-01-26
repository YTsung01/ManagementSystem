<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.UUID"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<div class="container-xl mt-5">

	<!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->
	<form action="./add/${empBook.empId}" method="post"
		enctype="multipart/form-data" class="border rounded mx-auto p-4">

		<div class="row">
			<!-- 左側 -->
			<!-- <div class="col-12 col-md-10 border-end"> -->
			<p class="fs-3 fw-bold text-center">假單申請</p>

			<!-- 填表人 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">填表人：</div>
				<div class="col-12 col-md-2 position-relative">
					${ empBook.empName }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">填表日期：</div>
				<div class="col-12 col-md-2 position-relative">
					${takeOffDate}
					<!-- 控制日期最大最小值 -->

				</div>
			</div>

			<!-- 申請人 -->

			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請人：</div>

				<div class="col-12 col-md-2 position-relative">
					<select name="applierName" id="applierName" class="form-select" required>
						<option value="" selected disabled>請選擇申請人..</option>
						<!-- 第一個option是沒有值 代表你沒選擇,所以下面的選項都會有value(因為required要接收value) selected代表預設選擇 disabled代表他不能再被選-->
						<c:forEach var="emp" items="${allDeptEmp}">
							<option value="${ emp.empId }">${emp.empName}</option>
						</c:forEach>


					</select>


					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>


				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請部門：</div>
				<div class="col-12 col-md-2 position-relative">
					${ empBook.empDepartment  }
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>

			</div>




			<!-- 代理人 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">代理人：</div>
				<div class="col-12 col-md-2 position-relative">
					<select name="agentName" id="agentName" class="form-select"
						required>
						<option value="" selected disabled>請選擇代理人..</option>
						<!-- 第一個option是沒有值 代表你沒選擇,所以下面的選項都會有value(因為required要接收value) selected代表預設選擇 disabled代表他不能再被選-->
						<c:forEach var="emp" items="${allDeptEmp}">
							<option value="${emp.empId}">${emp.empName}</option>

						</c:forEach>

					</select>

					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
				</div>
				<button type="button"
					class="col-12 col-md-2 btn btn-light text-nowrap"
					name="searchDayoff" id="searchDayoff">查詢剩餘時數</button>
			</div>

			<!-- 選擇假別 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">選擇假別：</div>
				<div class="col-12 col-md-7">
					<select name="a_area" id="a_area" class="form-select" required>
						<option value="" selected disabled>請選擇請假類別..</option>
						<!-- 第一個option是沒有值 代表你沒選擇,所以下面的選項都會有value(因為required要接收value) selected代表預設選擇 disabled代表他不能再被選-->
						<option value="特別休假">特別休假</option>
						<option value="病假">病假</option>
						<option value="事假">事假</option>
						<option value="喪假">喪假</option>
						<option value="公假">公假</option>
					</select>
				</div>
			</div>

			<!-- 請假起始日 -->
			<div class="row align-items-center pe-4 mb-3">
				<div class="col-14 col-md-2 text-md-end text-nowrap p-md-0 ">請假起始日：</div>
				<div class="col-14 col-md-2 position-relative">
					<input type="datetime-local" name="startTime" id="startTime"
						class="form-control me-3" style="width: auto;" min="2023-11-01"
						max="2023-12-31">

					<!-- 控制日期最大最小值 -->
				</div>

				<div class="col-14 col-md-2 text-md-end text-nowrap p-md-0">結束日：</div>
				<div class="col-14 col-md-2 position-relative">
					<input type="datetime-local" name="endTime" id="endTime"
						class="form-control" style="width: auto;" min="2023-11-01"
						max="2023-12-31"> <span id="overTimeEndError"
						style="color: red;"></span>
					<!-- 控制日期最大最小值 -->
				</div>
			</div>

			<!-- 合計天數 -->
			<div class="row align-items-center pe-4 mb-5">
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">合計天數：</div>
				<div class="col-12 col-md-2 position-relative">
					<!-- 添加一個隱藏的 input 元素 -->
					<input type="hidden" name="calculatedtakeoffDay"
						id="calculatedtakeoffDay"> <input type="text"
						name="takeoffDay" id="takeoffDay" class="form-control" required
						readonly>
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2">天</div>
				</div>
				<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">合計時數：</div>
				<div class="col-12 col-md-2 position-relative">
					<!-- 添加一個隱藏的 input 元素 -->
					<input type="hidden" name="calculatedtakeoffHour"
						id="calculatedtakeoffHour"> <input type="text"
						name="takeoffhour" id="takeoffhour" class="form-control" required
						readonly>
					<div
						class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2 ">時</div>
				</div>
				<button type="button"
					class="col-12 col-md-1 btn btn-light text-nowrap "
					name="calculatebtn" id="calculatebtn">計算</button>
				<span class="col-12 col-md-1  text-nowrap " id="overTimeStartError"
					style="color: red;"></span>
			</div>


			<!-- 留言內容 -->
			<div class="row  pe-4 mb-5 mt-3">
				<div class="col-12 col-md-2 text-md-end p-md-0">請假事由：</div>
				<div class="col-12 col-md-10 mb-4">
					<textarea name="a_content" id="a_content" class="form-control"
						rows="5" required></textarea>
					<div class="col-12 col-md-12 mt-5">
						<label class="btn btn-outline-primary w-100"> <input
							type="file" name="upfile[]" multiple accept=".jpg, .jpeg, .png"
							id="upfile" class="upfile d-none"> <!-- 選多個東西要用陣列儲存 name是負責接收不是負責選 藥用multiple才能多選 accept來過濾 -->
							上傳附件
						</label>

						<ul style="list-style-type: disc; margin: 50px">
							<li>上傳照片最多 10 張</li>
							<li>每張照片容量最高 1M</li>
							<li>照片寬度最小 1000px</li>
							<li>檔案類型必須是 jpg、png、gif</li>
							<li>按下【確定送出】才完成上傳</li>
						</ul>

						<div id="img_errmsg" class="text-danger text-center tw-bold"></div>
						<div id="img_area" class="text-center"></div>
					</div>

				</div>
			</div>



			<!-- 最後 -->
			<div
				style="display: flex; justify-content: center; align-items: center;">
				<button type="submit" id="submitbtn"
					class="btn btn-primary align-items-center m-4" value="送出">送出</button>
				<button type="reset" class="btn btn-danger">清除</button>
			</div>


		</div>
	</form>
</div>
<!-- 模态框 -->
<div class="container mt-5">

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





<script>

$('#calculatebtn').on('click', function() {
    // 取得上班時間範圍
    var workStartTime = new Date();
    workStartTime.setHours(9, 0, 0, 0);  // 上午9點

    var workEndTime = new Date();
    workEndTime.setHours(18, 0, 0, 0);  // 下午5點

    var startDate = new Date($('#startTime').val());
    var endDate = new Date($('#endTime').val());

    // 檢查是否在上班時間範圍內
    if (startDate < workStartTime || endDate > workEndTime) {
        $('#overTimeStartError').text('請依上班時間請假');
        return;
    } else {
        $('#overTimeStartError').text(''); // 清除錯誤信息
    }
        var timeDiff =Math.abs(endDate.getTime()- startDate.getTime());
        var diffDays = Math.floor(timeDiff / (1000 * 3600 * 24));         
        var diffHours =  Math.ceil(timeDiff / (1000 * 60 * 60));
        var diffDayHours = diffHours % 24;
        var difftotalDays = Math.floor(diffHours/ 24);
        //var diffDayHours = diffHours- diffDays*24;

        let calculatedResult = diffDays;
        let calculatedResult2 = diffDayHours-1;
        
        
        // 將計算結果丟給隱藏的 input 元素
        $('#calculatedtakeoffDay').val(calculatedResult);
        $('#calculatedtakeoffHour').val(calculatedResult2);

        // 更新顯示在 readonly 的 input 元素中
        $('#takeoffDay').val(calculatedResult);
        $('#takeoffhour').val(calculatedResult2);

        // 使用 console.log 顯示目前的計算值
        console.log('目前的計算值：', calculatedResult +'天'+ calculatedResult2+'時');
       
    
        });
</script>

<script>
    // 當代理人下拉選單的值發生變化時
    document.getElementById("agentName").addEventListener("change", function() {
        // 取得代理人和申請人的下拉選單元素
        var agentDropdown = document.getElementById("agentName");
        var applierDropdown = document.getElementById("applierName");

        // 取得選中的代理人和申請人的值
        var selectedAgent = agentDropdown.options[agentDropdown.selectedIndex].text
        var selectedApplier = applierDropdown.options[applierDropdown.selectedIndex].text

        // 檢查是否選中相同的代理人和申請人
        if (selectedAgent === selectedApplier) {
            // 如果相同，顯示錯誤訊息（你可以根據實際需求自行調整提示內容和樣式）
            alert("代理人和申請人不可為同一人");
            // 或者清空申請人的選擇
            applierDropdown.value = "";
        }
    });
</script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
        $(document).ready(function () {
        	 console.log('Calculate button clicked');
            $("#searchDayoff").click(function () {
            	 console.log('Calculate button clicked');
                
                var remainingHours = ${takeOffLeftHour}; 

                var modalBody = $("#remainingHoursModalBody");
                modalBody.text("本月剩餘時數為:  " + ${takeOffLeftHour} + "  小時");

                if (remainingHours < 10) {
                    modalBody.css('color', 'red');
                } else {
                    modalBody.css('color', ''); 
                }
             
                $("#remainingHoursModal").modal("show");
            });
        });
    </script>




<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>