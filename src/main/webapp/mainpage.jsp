<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<div class="container-xl mt-5">
	<!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->

	<form action="./index.html" method="post" enctype="multipart/form-data"
		class="border rounded mx-auto p-4">
		<div class="row">
			<!-- 左側 -->
			<p class="fs-3 fw-bold text-center">基本資料</p>

			<div class="col-12 col-md-6 border-end">

				<img src="img/1.jpg" class="d-block mx-auto w-100 rounded c-p "
					data-img="p1_1.jpg" data-title="頭像" alt="">


			</div>

			<!-- 右側 -->
			<div class="col-12 col-md-6 ">
				<ul>
					<li>姓名 : </li><p/>
					<li>員工編號 :</li><p/>
					<li>部門 :</li><p/>
					<li>主管 :</li><p/>
					<li>職位 :</li><p/>
					<li>到職日 :</li><p/>
				</ul>

			</div>

			<!-- 最後 -->
			<div class="col-12 p-5">
				<p class="fs-5 fw-bold text-center">員工列表</p>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">姓名</th>
							<th scope="col">職位</th>
							<th scope="col">到職日</th>

						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>Mark</td>
							<td>Otto</td>
							<td>2023-12-04</td>

						</tr>
						<tr>
							<th scope="row">2</th>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>2023-12-04</td>

						</tr>
						<tr>
							<th scope="row">3</th>
							<td>Larry the Bird</td>
							<td>Thornton</td>
							<td>2023-12-04</td>

						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>
