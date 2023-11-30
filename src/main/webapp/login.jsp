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
	
		 <div class="d-flex justify-content-center align-items-center vh-100 mx-auto " >
	    	<form class="needs-validation border rounded mx-auto p-4" novalidate method="post" action="./mainpage.jsp">
	    	<fieldset>
	    	  <h4 class="text-center mb-3">Login</h4>
			  <div>
			    <label for="username" class="form-label">🙋‍♀帳號:</label>
			    <input type="text" class="form-control" id="username" name="username" value=""  placeholder="請輸入員工編號" required>
			    <div class="invalid-feedback">
			      請輸入帳號
			    </div>
			  </div>
			  <div>
			    <label for="password" class="form-label">🔑密碼:</label>
			    <input type="password" class="form-control" id="password" name="password" value="" placeholder="請輸入密碼"required>
			    <div class="invalid-feedback">
			      請輸入密碼
			    </div>
			  </div>
			  <div class="d-flex justify-content-center mt-4 mx-3">
			    <button class="btn mx-3" style="background-color: #e3f2fd" type="submit">員工登入</button>
			    <button type="button" class="btn mx-3" style="background-color: #ccdce8"
						onclick="window.location.href='./mainpage.jsp';">主管登入</button>
			  </div>
			  <div class="d-flex justify-content-center mt-4 mx-auto ">
			  <a  href=# >忘記密碼?</a>
			  </div>
			  </fieldset>
			</form>
		</div>
	</body>
</html>