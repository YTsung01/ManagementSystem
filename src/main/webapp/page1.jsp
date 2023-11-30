<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<!-- Left People：Bonita -->
<div class="container-xl mt-5">
	<!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->

	<form action="./index.html" method="post" enctype="multipart/form-data"
		class="border rounded mx-auto p-4">
		<div class="row">
			<!-- 左側 -->
			<!-- <div class="col-12 col-md-10 border-end"> -->
				<p class="fs-3 fw-bold text-center">假單申請</p>

				<!-- 填表人 -->
				<div class="row align-items-center pe-4 mb-3">
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">填表人：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="text" name="a_name" id="a_name" class="form-control"
							required>
						<div
							class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">填表日期：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="date" name="a_date" id="a_date" class="form-control"
							style="width: auto;" min="2023-11-01" max="2030-12-31">
						<!-- 控制日期最大最小值 -->

					</div>
				</div>

				<!-- 申請人 -->
				<div class="row align-items-center pe-4 mb-3">
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請人：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="text" name="a_name" id="a_name" class="form-control"
							required>
						<div
							class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請部門：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="text" name="a_name" id="a_name" class="form-control"
							required>
						<div
							class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
					<button type="button" class="col-12 col-md-1 btn btn-light ">..</button>



				</div>
				<div class="row align-items-center pe-4 mb-3">
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請人性別：</div>
					<div class="col-12 col-md-8">
						<label> <input type="radio" name="a_sex" id="a_sex_1"
							class="form-check-input" value=" 先生" required> 先生 <!-- required是設定為必填項目 -->
						</label> <label class="ms-3"> <input type="radio" name="a_sex"
							id="a_sex_2" class="form-check-input" value=" 小姐" required>
							小姐
						</label>
					</div>
				</div>


				<!-- 代理人 -->
				<div class="row align-items-center pe-4 mb-3">
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">代理人：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="text" name="a_name" id="a_name" class="form-control"
							required>
						<div
							class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
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
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">請假起始日：</div>
					<div class="col-12 col-md-2 me-3">
						<input type="datetime-local" name="a_date" id="a_date"
							class="form-control" style="width: auto;" min="2023-11-01"
							max="2023-12-31">
					</div>
				
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">請假結束日：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="datetime-local" name="a_date" id="a_date"
							class="form-control" style="width: auto;" min="2023-11-01"
							max="2023-12-31">
						<!-- 控制日期最大最小值 -->
					</div> 
					</div>
					
					<!-- 合計天數 -->
				<div class="row align-items-center pe-4 mb-5">
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">合計天數：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="text" name="a_name" id="a_name" class="form-control"
							required>
						<div
							class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
					<div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">合計時數：</div>
					<div class="col-12 col-md-2 position-relative">
						<input type="text" name="a_name" id="a_name" class="form-control"
							required>
						<div
							class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
					</div>
					<button type="button" class="col-12 col-md-1 btn btn-light ">計算</button>
					
					
				





				<!-- 留言內容 -->
				<div class="row align-items-center pe-4 mb-2 mt-2">
					<div class="col-12 col-md-2 text-md-end p-md-0">請假事由：</div>
					<div class="col-12 col-md-10">
						<textarea name="a_content" id="a_content" class="form-control"
							rows="5" required></textarea>
					</div>
				</div>

			<div class="row align-items-center pe-4 mb-2 mt-2">
			<div class="col-12 col-md-10">
				<label class="btn btn-outline-primary w-100"> <input
					type="file" name="upfile[]" multiple accept=".jpg, .jpeg, .png"
					id="upfile" class="upfile d-none"> <!-- 選多個東西要用陣列儲存 name是負責接收不是負責選 藥用multiple才能多選 accept來過濾 -->
					上傳附件
				</label>
				<ul style="list-style-type: disc;">
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

			<!-- 最後 -->
			<div class="col-12">
				<input type="submit"
					class="btn btn-outline-primary px-5 d-block w-100" value="確定送出">
			</div>

		</div>
	</form>
</div>


</section>
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

<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>