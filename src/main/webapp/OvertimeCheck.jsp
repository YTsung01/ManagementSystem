<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['姓名', '本月時數', '上月時數' ],
          ['bonita', 60, 59],
          ['wang', 15, 20 ],
          ['yui', 50, 58],
          ['boss', 46, 30]
        ]);

        var options = {
          chart: {
            title: '加班時數統計'
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
  </head>
  <body>
  <div class="p-5 d-flex justify-content-center align-items-center vh-auto mx-auto">
    <div id="columnchart_material" style="width: 500px; height: 300px;" ></div></div>
  </body>
</html>
