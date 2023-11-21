<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>考勤系統登入</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" >
	</head>
	<body>
		<div style="display:flex; justify-content:center; align-items: center; height:100vh; border: 15px; border-color: #0000">
			<form method="post" action="./frontend/main.jsp" class="pure-form">
			<fieldset>
				<legend>考勤系統登入</legend>
				🙋‍♀帳號: <input type="text" id="uesername" name="uesername" value="John"  placeholder="請輸入帳號" /> <p />
				🔑密碼: <input type="password" id="password" name="password" value="1234"  placeholder="請輸入密碼" /> <p />
				<button type="submit" class="btn " style="background-color: #e3f2fd;">一般員工登入</button>
				<button type="button" class="pure-button pure-button-primary" 
						onclick="window.location.href='./backend/main.jsp';">主管登入</button>
			</fieldset>
			
			
			</form>
		</div>
	
	</body>
</html>