<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<style>
body {
	overflow: hidden;
}
</style>
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
String formNumber = sdf.format(new Date());
%>

<script>
	function submitFrom(flagValue) {
		document.getElementById("flag").value = flagValue;
		//document.getElementById("checkIn").action = "./addcheckIn/${empBook.empId}?flag=" + flagValue;
		document.getElementById("checkIn").submit();
	}
	
</script>

<script>
	 $(document).ready(function () {
        $("#checkInbtn").click(function () {
        	 console.log('打上班卡');
        	 var checkInTimeElement = $("#checkInTime");
        	 var checkOutTimeElement = $("#checkOutTime");
        	 checkOutTimeElement.css("display", "none");
    });
</script>	
	


</head>
<body onload="startTime()">

	<!-- ${ empBook } -->
	<div class="d-flex justify-content-center mx-auto p-4">
		<sp:form modelAttribute="checkIn"
			action="./addcheckIn/${empBook.empId}" method="post"
			class="border rounded mx-auto p-4">

			<div class="pb-2">
				員工編號: ${empBook.empId }
				<p />
			</div>
			<div class="pb-2">
				姓名: ${ empBook.empName }
				<p />
			</div>
			<div class="pb-2">
				部門: ${ empBook.empDepartment  }
				<p />
			</div>
			<div class="pb-2">
				職位: ${ empBook.empJob }
				<p />
			</div>
			<div class="pb-2">
				今日時間: <font><span id="nowDateTimeSpan"></span></font>
				<p />
			</div>
			<input type="hidden" id="flag" name="flag" value="1" />
			<input type="hidden" id="flag" name="flag" value="2" />

			<button onclick="submitFrom(1)" id="checkInbtn"
				class="btn mx-3 align-items-center m-4"
				style="background-color: #e3f2fd">上班</button>
			<button onclick="submitFrom(2)"
				class="btn mx-3 align-items-center m-4" id="checkOutbtn"
				style="background-color: #ccdce8">下班</button>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>員工編號</th>
						<th>名稱</th>
						<th>部門</th>
						<th>職位</th>
						<th>上班時間</th>
						<th>下班時間</th>
					</tr>
				</thead>
				<tr>

					<td>${ empBook.empId }</td>
					<td>${ empBook.empName }</td>
					<td>${ empBook.empDepartment  }</td>
					<td>${ empBook.empJob }</td>
					<td id="checkInTime"
						style="${lateCheckInMessage != null ? 'color: red;' : ''}">
						${formattedCheckInTime}</td>

					<td id="checkOutTime"
						style="${lateCheckOutMessage != null ? 'color: red;' : ''} ">
						${formattedCheckOutTime}</td>
				</tbody>
			</table>
		</sp:form>

	</div>

	<script language="JavaScript">
		function startTime() {
			var today = new Date();//定義日期物件   
			var yyyy = today.getFullYear();//通過日期物件的getFullYear()方法返回年    
			var MM = today.getMonth() + 1;//通過日期物件的getMonth()方法返回年    
			var dd = today.getDate();//通過日期物件的getDate()方法返回年     
			var hh = today.getHours();//通過日期物件的getHours方法返回小時   
			var mm = today.getMinutes();//通過日期物件的getMinutes方法返回分鐘   
			var ss = today.getSeconds();//通過日期物件的getSeconds方法返回秒   
			// 如果分鐘或小時的值小於10，則在其值前加0，比如如果時間是下午3點20分9秒的話，則顯示15：20：09   
			MM = checkTime(MM);
			dd = checkTime(dd);
			mm = checkTime(mm);
			ss = checkTime(ss);
			var day; //用於儲存星期（getDay()方法得到星期編號）
			if (today.getDay() == 0)
				day = "星期日 "
			if (today.getDay() == 1)
				day = "星期一 "
			if (today.getDay() == 2)
				day = "星期二 "
			if (today.getDay() == 3)
				day = "星期三 "
			if (today.getDay() == 4)
				day = "星期四 "
			if (today.getDay() == 5)
				day = "星期五 "
			if (today.getDay() == 6)
				day = "星期六 "
			document.getElementById('nowDateTimeSpan').innerHTML = yyyy + "-"
					+ MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "   "
					+ day;
			setTimeout('startTime()', 1000);//每一秒中重新載入startTime()方法 
		}

		function checkTime(i) {
			if (i < 10) {
				i = "0" + i;
			}
			return i;
		}
	</script>


</body>

</html>