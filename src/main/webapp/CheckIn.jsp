<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.security.KeyStore.Entry"%>
<%@page import="javax.swing.text.html.parser.Entity"%>
<%@page import="model.Employeebook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="java.util.stream.Collectors"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%!// !代表置頂  
	//將日期格式化 yyyy-MM-dd HH:mm:ss E
	private String getDateFormatString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		return sdf.format(date);
	}
%>
<%
List<Employeebook> employeebooks = (List<Employeebook>) request.getAttribute("employeebooks");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>打卡</title>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<div class="container-xl mt-5">

		<div class="d-flex justify-content-center mx-auto p-4">
	
				<form
					action="http://localhost:8080/ManagementSystem_my/checkin"
					method="post" class="border rounded mx-auto p-4 ">
					<div class="d-flex  ustify-content-center">
						<div class="pb-2 mx-auto ">
							員工編號:
							<input type="number" name="id" id="id"
								placeholder="請輸入員工編號" required />
							<p />
						</div>

						<div class="pb-2 ">
							姓名:
							<input type="text" name="nickname"
								id="nickname" placeholder="請輸入姓名" required />
							<p />
						</div>
						<div class="pb-2 ">
							部門:
							<input type="text" name="department"
								id="department" placeholder="請輸入部門" required />
							<p />
						</div>
					</div>
					<div class="p-3 d-flex justify-content-center">
						<button type="submit"
							class="btn btn-primary m-2 "onclick="clockIn()">
							上班
						</button>
						<button type="submit"
							class="btn btn-success m-2 "  onclick="clockOut()">
							下班
						</button>
					</div>

					<div class="p-3">
						<table class="table table-bordered ">
							<thead>
								<tr>
									<th>員工編號</th>
									<th>名稱</th>
									<th>部門</th>
									<th>上班時間</th>
									<th>下班時間</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Employeebook eb :
									employeebooks) {
								%>

								<tr>
									<td>
										<%=eb.getId()%>
									</td>
									<td>
										<%=
											eb.getNickname()
										%>
									</td>
									<td>
										<%=
											eb.getDepartment()
										%>
									</td>
									<td>
										<%=
											getDateFormatString(eb.getDate())
										%>
									</td>
									<td>
										<div id="clockOut"></div>
									</td>
								</tr>
								<%}%>

							</tbody>
						</table>
				</form>
	
		</div>
	</div>
	
	    <script>
        function displayDateTime() {
            var dateTime = new Date();
            var displayDate = dateTime.toDateString();
            var displayTime = dateTime.toLocaleTimeString();

            document.getElementById("date").innerHTML = "日期: " + displayDate;
            document.getElementById("time").innerHTML = "時間: " + displayTime;
        }

        function clockIn() {
            var clockInTime = new Date();
            var displayClockInTime = clockInTime.toLocaleTimeString();

            document.getElementById("clockIn").innerHTML = "上班打卡時間: " + displayClockInTime;
        }

        function clockOut() {
            var clockOutTime = new Date();
            var displayClockOutTime = clockOutTime.toLocaleTimeString();

            document.getElementById("clockOut").innerHTML = "下班打卡時間: " + displayClockOutTime;
        }

        setInterval(displayDateTime, 1000); // 每秒更新時間
    </script>

 
</body>

</html>