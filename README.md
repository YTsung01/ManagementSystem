## 專案介紹
員工管理考勤系統(現正進行中 尚未完成)
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>顯示當日日期</title>
</head>
<body>

<h1>當日日期：</h1>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="formattedDate" />
<p>${formattedDate}</p>

</body>
</html>




## 技術棧
### Backend
* JAVA
* MySQL


### Frontend
* HTML
* JavaScript
* Bootstraps


## 功能與操作示範
* 登入與登出
* 打卡
* 請假申請&查詢
* 加班申請&查詢
* 主管帳號
  *簽核
  *考勤
*薪資計算 





