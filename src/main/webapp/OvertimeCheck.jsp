<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<html>


<head>

<div class="container-xl mt-5">

	<div class="d-flex justify-content-center mx-auto p-4">

		<form action="http://localhost:8080/ManagementSystem_my/checkin"
			method="post" class="border rounded mx-auto p-4 ">
			<p class="fs-3 fw-bold text-center">加班管理</p>
			<div class="p-3">
				<table class="table table-bordered ">
					<thead>
						<tr>
							<th>員工編號</th>
							<th>名稱</th>
							<th>部門</th>
							<th>加班開始時間</th>
							<th>加班結束時間</th>
							<th>加班時數</th>
							<th>加班原因</th>
							<th>審核狀態</th>
							<th>審核人</th>
							
						</tr>
					</thead>
					<tbody>


						<tr>
							<td>001</td>
							<td>Amy</td>
							<td>Sales</td>
							<td>2023-12-19 17:00</td>
							<td>2023-12-19 18:00</td>
							<td>1</td>
							<td>工作太多</td>
							<td><button type="submit"class="btn " style="background-color: #ccdce8" >同意</button>
							<button type="submit"class="btn mx-1" style="background-color: #ccdce8" >駁回</button></td>
							<td>主管</td>
							
						</tr>


					</tbody>
				</table>
		</form>

	</div>
</div>




<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ '姓名', '本月時數', '上月時數' ], [ 'bonita', 60, 59 ],
				[ 'wang', 15, 20 ], [ 'yui', 50, 58 ], [ 'boss', 46, 30 ] ]);

		var options = {
			chart : {
				title : '加班時數統計'
			}
		};

		var chart = new google.charts.Bar(document
				.getElementById('columnchart_material'));

		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
</script>
</head>
<body>
	<div
		class="p-5 d-flex justify-content-center align-items-center vh-auto mx-auto">
		<div id="columnchart_material" style="width: 500px; height: 300px;"></div>
	</div>
</body>
</html>
