<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>上下班打卡系統</title>
</head>
<body>
    <h1>上下班打卡系統</h1>
    
    <%-- 表單輸入編號 --%>
    <form method="post">
        <label for="employeeId">輸入您的編號：</label>
        <input type="text" id="employeeId" name="employeeId">
        <br><br>
        <input type="submit" value="提交">
    </form>
    
    <%-- 按鈕和列表顯示打卡時間 --%>
    <%
        // 檢查是否有提交編號
        String employeeId = request.getParameter("employeeId");
        if(employeeId != null && !employeeId.isEmpty()) {
            // 獲取上班和下班時間（模擬用）
            String clockInTime = request.getParameter("clockInTime");
            String clockOutTime = request.getParameter("clockOutTime");
            
            // 儲存打卡時間
            if(clockInTime != null && !clockInTime.isEmpty()) {
                // 取得當前時間
                java.util.Date now = new java.util.Date();
                clockInTime = now.toString(); // 在實際情況下應該是從資料庫中獲取
            }
            if(clockOutTime != null && !clockOutTime.isEmpty()) {
                // 取得當前時間
                java.util.Date now = new java.util.Date();
                clockOutTime = now.toString(); // 在實際情況下應該是從資料庫中獲取
            }
    %>
            <form method="post">
                <input type="hidden" name="employeeId" value="<%= employeeId %>">
                <input type="hidden" name="clockInTime" value="<%= clockInTime %>">
                <input type="hidden" name="clockOutTime" value="<%= clockOutTime %>">
                <input type="submit" name="clockIn" value="上班">
                <input type="submit" name="clockOut" value="下班">
            </form>
            
            <%-- 顯示打卡列表 --%>
            <h2>打卡列表</h2>
            <ul>
                <li>編號：<%= employeeId %></li>
                <li>上班時間：<%= clockInTime %></li>
                <li>下班時間：<%= clockOutTime %></li>
            </ul>
    <% } %>
</body>
</html>