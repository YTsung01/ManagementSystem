import java.util.UUID;

@Controller
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/generateUUID")
    public String generateUUID(Model model) {
        UUID uuid = UUID.randomUUID();
        model.addAttribute("uuid", uuid.toString());
        return "example/generateUUID";
    }
}



<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自動產生表單單號</title>
</head>
<body>

<%
    // 使用當前時間生成唯一的表單單號
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String formNumber = dateFormat.format(new Date());
%>

<form action="processForm.jsp" method="post">
    <!-- 在這裡添加其他表單元素 -->
    
    <!-- 顯示自動產生的表單單號 -->
    <label for="formNumber">表單單號：</label>
    <input type="text" id="formNumber" name="formNumber" value="<%= formNumber %>" readonly>
    
    <br><br>
    
    <input type="submit" value="提交表單">
</form>

</body>
</html>




## 專案介紹
員工管理考勤系統(現正進行中 尚未完成)
+ document.getElementById('bookingId').value

## 技術棧
### Backend
* JAVA
* MySQL
* SpringMVC


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





