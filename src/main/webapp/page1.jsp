<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

               <div class="container-xl mt-5"><!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->

            <form action="./index.html" method="post" enctype="multipart/form-data" class="border rounded mx-auto p-4">
                <div class="row">
                    <!-- 左側 -->
                    <div class="col-12 col-md-8 border-end">
                        <p class="fs-3 fw-bold text-center">假單申請</p>

                        <!-- 姓名 -->
                        <div class="row align-items-center pe-4 mb-3">
                            <div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請人：</div>
                            <div class="col-12 col-md-4 position-relative">
                                <input type="text" name="a_name" id="a_name" class="form-control" required>
                                <div class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
                            </div>
                            <div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">申請部門：</div>
                            <div class="col-12 col-md-4 position-relative">
                                <input type="text" name="a_name" id="a_name" class="form-control" required>
                                <div class="msg position-absolute top-0 end-0 ts-blueword pe-4 pt-2"></div>
                            </div>
                            
                         
                        </div>


                        <!-- 稱謂 -->
                        <div class="row align-items-center pe-4 mb-3">
                            <div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">稱謂：</div>
                            <div class="col-12 col-md-10">
                                <label>
                                    <input type="radio" name="a_sex" id="a_sex_1" class="form-check-input" value=" 先生"
                                        required> 先生 <!-- required是設定為必填項目 -->
                                </label>
                                <label class="ms-3">
                                    <input type="radio" name="a_sex" id="a_sex_2" class="form-check-input" value=" 小姐"
                                        required> 小姐
                                </label>
                            </div>
                        </div>

                        <!-- 熟悉語言 -->
                        <div class="row align-items-center pe-4 mb-3">
                            <div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">熟悉語言：</div>
                            <div class="col-12 col-md-10">
                                <label>
                                    <input type="checkbox" name="a_lang[]" id="a_lang_1"
                                        class="form-check-input ch-lang">
                                    中文
                                </label>
                                <label class="ms-3">
                                    <input type="checkbox" name="a_lang[]" id="a_lang_2"
                                        class="form-check-input ch-lang">
                                    英文
                                </label>
                                <label class="ms-3">
                                    <input type="checkbox" name="a_lang[]" id="a_lang_3"
                                        class="form-check-input ch-lang">
                                    日文
                                </label>
                                <label class="ms-3">
                                    <input type="checkbox" name="a_lang[]" id="a_lang_4"
                                        class="form-check-input ch-lang">
                                    韓文
                                </label>
                                <small class="ms-2 ts-gray-9-08">(至少勾選一項)</small>
                                <span id="msg_ch_lang" class="msg ts-redword ps-4 d-none">請至少勾選一項</span>
                            </div>
                        </div>


                        <!-- 選擇地區 -->
                        <div class="row align-items-center pe-4 mb-3">
                            <div class="col-12 col-md-2 text-md-end text-nowrap p-md-0">選擇地區：</div>
                            <div class="col-12 col-md-10">
                                <select name="a_area" id="a_area" class="form-select" required>
                                    <option value="" selected disabled>請選擇報名參加的地區 ...</option>
                                    <!-- 第一個option是沒有值 代表你沒選擇,所以下面的選項都會有value(因為required要接收value) selected代表預設選擇 disabled代表他不能再被選-->
                                    <option value="台北">台北</option>
                                    <option value="新竹">新竹</option>
                                    <option value="台中">台中</option>
                                    <option value="台南">台南</option>
                                    <option value="高雄">高雄</option>
                                </select>
                            </div>
                        </div>


                        <!-- 留言內容 -->
                        <div class="row align-items-center pe-4 mb-2">
                            <div class="col-12 col-md-2 text-md-end p-md-0">留言內容：</div>
                            <div class="col-12 col-md-10">
                                <textarea name="a_content" id="a_content" class="form-control" rows="5"
                                    required></textarea>
                            </div>
                        </div>


                        <!-- 報名日期 -->
                        <div class="row align-items-center pe-4 mb-2">
                            <div class="col-12 col-md-2 text-md-end p-md-0">報名日期：</div>
                            <div class="col-12 col-md-10">
                                <input type="date" name="a_date" id="a_date" class="form-control" style="width: auto;"
                                    min="2023-11-01" max="2023-12-31"> <!-- 控制日期最大最小值 -->
                            </div>
                        </div>


                        <!-- 報名班次 -->
                        <div class="row align-items-center pe-4 mb-2">
                            <div class="col-12 col-md-2 text-md-end p-md-0">報名班次：</div>
                            <div class="col-12 col-md-10">
                                <input list="apply_date" name="a_date_2" id="a_date_2"
                                    class="form-control d-inline-block" style="width: auto;"> <!--可打字的下拉式選單 -->

                                <datalist id="apply_date">
                                    <option value="Java 2023 梯次"></option>
                                    <option value="Java 2024 梯次"></option>
                                    <option value="Python 2023 梯次"></option>
                                    <option value="Python 2024 梯次"></option>
                                    <option value="JavaScript 2023 梯次"></option>
                                    <option value="JavaScript 2024 梯次"></option>
                                </datalist>

                                <span class="msg ts-blueword ps-2 pt-2"></span>
                            </div>
                        </div>

                    </div>



                    <!-- 右側 -->
                    <div class="col-12 col-md-4">
                        <label class="btn btn-outline-primary w-100">
                            <input type="file" name="upfile[]" multiple accept=".jpg, .jpeg, .png" id="upfile"
                                class="upfile d-none"><!-- 選多個東西要用陣列儲存 name是負責接收不是負責選 藥用multiple才能多選 accept來過濾 -->
                            上傳檔案
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

                    <!-- 最後 -->
                    <div class="col-12">
                        <input type="submit" class="btn btn-outline-primary px-5 d-block w-100" value="確定送出">
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