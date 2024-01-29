<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<<<<<<< HEAD
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
=======
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	
>>>>>>> branch 'master' of https://github.com/YTsung01/ManagementSystem.git


${overTimes}

<<<<<<< HEAD
<style>
.center {
	text-align: center;
	vertical-align: middle;
}
</style>

<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">

		<div class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">加班管理</p>
			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th hidden="hidden" class="center">員工編號</th>
							<th class="center">申請單號</th>
							<th class="center">名稱</th>
							<th class="center">部門</th>
							<th class="center">加班開始時間</th>
							<th class="center">加班結束時間</th>
							<th>加班時數</th>
							<th>加班原因</th>
							<th>審核狀態</th>
							<th width="150px" class="center">審核</th>
							<th align='center' valign="middle">審核人</th>

						</tr>
					</thead>
					<tbody>

						<c:forEach var="overtime" items="${overTimes}" varStatus="stat">
							<tr>
								<td hidden="hidden" class="center">${ overtime.formId }</td>
								<td class="center">${ overtime.empBook.empId }</td>
								<td class="center">${ overtime.empBook.empName }</td>
								<td class="center">${ overtime.empBook.empDepartment  }</td>
								
								<td class="center"><fmt:formatDate
										value="${ overtime.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="center"><fmt:formatDate
										value="${ overtime.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="center">${overtime.applyHour}</td>
								<td class="center">${overtime.reason}</td>
								<td class="center"
									style="${overtime.verifyState == 0 ? 'color: red;' : ''} ">
									${overtime.verifyState == 2 ? '審核中' : (overtime.verifyState == 1 ? '同意' : '駁回')}</td>
								<form method="POST" action="./pass/${ overtime.formId }">
									<input name="_method" type="hidden" value="${_method}" />
								<td><button type="submit" class="btn "
										style="background-color: #ccdce8">同意</button>
									</form>
									<button class="btn mx-1" style="background-color: #ccdce8"
										data-bs-toggle="modal"
										data-bs-target="#rejectModel${stat.index}">駁回</button></td>
								<td>${empBossName}</td>

							</tr>

							<!-- Modal -->
							<div class="modal fade" id="rejectModel${stat.index}"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">


									<form method="POST" action="./false/${ overtimes.formId }">
										<input name="_method" type="hidden" value="${_method}" />
										<div class="modal-content">

											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">${ overtimes.formId }請填寫駁回原因</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="row justify-content-center">
													<div class="col-md-8">
														<textarea class="form-control" id="checkReason"
															name="checkReason" rows="5" placeholder="請填寫駁回原因"
															required></textarea>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">關閉</button>
												<button type="submit" class="btn btn-primary">送出</button>
											</div>
										</div>
									</form>
								</div>
							</div>

							<!-- Modal END -->
						</c:forEach>
					</tbody>

				</table>


			</div>

		</div>
	</div>
	
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
=======

<style>
.center {
	text-align: center;
	vertical-align: middle;
}
</style>

<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">

		<div class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">加班管理</p>
			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th hidden="hidden" class="center">申請單號</th>
							<th class="center">員工編號</th>
							<th class="center">名稱</th>
							<th class="center">部門</th>
							<th class="center">加班開始時間</th>
							<th class="center">加班結束時間</th>
							<th>加班時數</th>
							<th>加班原因</th>
							<th >審核狀態</th>
							<th colspan="1" width="150px" class="center">審核</th>
							<th align='center' valign="middle">審核人</th>

						</tr>
					</thead>
					<tbody>

						<c:forEach var="overtime" items="${overTimes}" varStatus="stat">
							<tr>
								<td hidden="hidden" class="center">${ overtime.formId }</td>
								<td class="center">${ overtime.empBook.empId }</td>
								<td class="center">${ overtime.empBook.empName }</td>
								<td class="center">${ overtime.empBook.empDepartment  }</td>

								<td class="center"><fmt:formatDate
										value="${ overtime.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="center"><fmt:formatDate
										value="${ overtime.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="center">${overtime.applyHour}</td>
								<td class="center">${overtime.reason}</td>
								<td class="center"
									style="${overtime.verifyState == 0 ? 'color: red;' : (overtime.verifyState == 2 ? 'color: black;' : (overtime.verifyState == 1 ? 'color: blue;' : ''))} "">
									${overtime.verifyState == 2 ? '審核中' : (overtime.verifyState == 1 ? '同意' : '駁回')}</td>
								<td  colspan="2"  class="d-flex">
								<form method="POST" action="./pass/${ overtime.formId }"  class="me-1">
									<input name="_method" type="hidden" value="${_method}" />
								<button type="submit" class="btn " id="agree"
											style="background-color: #A2AFA6" ${overtime.verifyState == 1 ? 'disabled' : ''}>同意</button>
								</form>
								
								<button class="btn mx-1" style="background-color: #CC5F5A"
									data-bs-toggle="modal"
									data-bs-target="#rejectModel${stat.index}">駁回</button>
								</td>
								<td>${empBossName}</td>

							</tr>

							<!-- Modal -->
							<div class="modal fade" id="rejectModel${stat.index}"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">


									<form method="POST" action="./false/${ overtime.formId }">
										<input name="_method" type="hidden" value="${_method}" />
										<div class="modal-content">

											<div class="modal-header">
											${ overtime.formId }
												<h5 class="modal-title" id="exampleModalLabel">${ overtime.formId }請填寫駁回原因</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="row justify-content-center">
													<div class="col-md-8">
														<textarea class="form-control" id="checkReason"
															name="checkReason" rows="5" placeholder="請填寫駁回原因"
															required></textarea>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">關閉</button>
												<button type="submit" class="btn btn-primary">送出</button>
											</div>
										</div>
									</form>
								</div>
							</div>

							<!-- Modal END -->

						</c:forEach>
					</tbody>

				</table>


			</div>

		</div>
	</div>
</div>



<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
>>>>>>> branch 'master' of https://github.com/YTsung01/ManagementSystem.git
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['${empBook.empName}', '${empBook.overTimeLeftHour}'],
          ['${empBook.empName}', ${empBook.overTimeLeftHour}],
          ['${empBook.empName}',       ${empBook.overTimeLeftHour}],
          ['${empBook.empName}',   ${empBook.overTimeLeftHour}],
          ['${empBook.empName}',  ${empBook.overTimeLeftHour}],
          ['${empBook.empName}',     ${empBook.overTimeLeftHour}]
        ]);

        var options = {
          title: '加班時數統計'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
<<<<<<< HEAD
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
=======


</head>
<body>
	<div id="piechart" style="width: 900px; height: 500px;"></div>
</body>
>>>>>>> branch 'master' of https://github.com/YTsung01/ManagementSystem.git
