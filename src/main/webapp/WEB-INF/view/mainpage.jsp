<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--  ${ empBook }
//<hr>
//${ empBossName }-->
<head>

</head>

<div class="container-xl mt-5">
    <!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->
    <form action="./index.html" method="post" enctype="multipart/form-data"
        class="border rounded mx-auto p-4">
        <div class="row">
            <!-- 左側 -->
            <p class="fs-3 fw-bold text-center">基本資料</p>
            <div class="col-12 col-md-6 border-end">
                <img src="../images/202.jpg"
                    class="d-block mx-auto w-80 rounded c-p " data-img="./img/102.jpg"
                    data-title="頭像" alt="">
            </div>
            <!-- 右側 -->
            <div class="col-12 col-md-6 ">
                <ul>
                    <li>姓名 : ${ empBook.empName }</li>
                    <p />
                    
                    <li>員工編號 :${ empBook.empId }</li>
                    <p />
                    
                    <li>部門 :${ empBook.empDepartment  }</li>
                    <p />
                    <div> <c:if test="${empBook != null && empBook.getLevelId() == 1}">
                    <li>主管 : ${ empBossName }</li>
                    <p /></c:if></div>
                   
                    <li>職位 :${ empBook.empJob }</li>
                    <p />
                    
                    <li>到職日 :<fmt:formatDate value="${empBook.hireDate}" pattern="yyyy-MM-dd" /></li>
                    <p></p>
                </ul>
            </div>
			<!-- 最後 -->
			<div class="col-12 p-5">
			 <c:if test="${empBook != null && empBook.getLevelId() == 2}">
				<p class="fs-5 fw-bold text-center">員工列表</p>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">員工編號</th>
							<th scope="col">姓名</th>
							<th scope="col">職位</th>
							<th scope="col">到職日</th>

						</tr>
					</thead>
					<tbody>
                <c:forEach var="employee" items="${employeeList}">
                    <tr>
                        <th scope="row">${employee.empId}</th>
                        <td>${employee.empName}</td>
                        <td>${employee.empJob}</td>
                        <td> <fmt:formatDate value="${employee.hireDate}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                </c:forEach>
						
					</tbody>
				</table>
			</c:if>
			</div>
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/view/Systemfooter.jsp"%>
