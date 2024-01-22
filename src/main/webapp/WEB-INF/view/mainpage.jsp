<%@page import="com.example.model.entity.oldEmployee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/Systemheader.jsp"%>

${ empBook } ${ empBoss }

<div class="container-xl mt-5">
    <!-- 有需要上傳檔案,內有多媒體影像的話一定要加入 entype這個標籤 -->
    <form action="./index.html" method="post" enctype="multipart/form-data"
        class="border rounded mx-auto p-4">
        <div class="row">
            <!-- 左側 -->
            <p class="fs-3 fw-bold text-center">基本資料</p>
            <div class="col-12 col-md-6 border-end">
                <img src="../images/default_user.jpg"
                    class="d-block mx-auto w-100 rounded c-p " data-img="./img/1.jpg"
                    data-title="頭像" alt="">
            </div>
            <!-- 右側 -->
            <div class="col-12 col-md-6 ">
                <ul>
                    <li>姓名 :</li>
                    <p />
                    ${ empBook.empName }
                    <li>員工編號 :</li>
                    <p />
                    ${ empBook.empId }
                    <li>部門 :</li>
                    <p />
                    ${ empBook.empDepartment  }
                    <li>主管 :</li>
                    <p />
                    ${ empBossName }
                    <li>職位 :</li>
                    <p />
                    ${ empBook.empJob }
                    <li>到職日 :</li>
                    <p />
                    <fmt:formatDate value=${ empBook.hireDate } pattern="yyyy-MM-dd" />
                </ul>
            </div>
			<!-- 最後 -->
			<div class="col-12 p-5">
			 <c:if test="${empBook != null && empBook.getLevelId() == 2}">
				<p class="fs-5 fw-bold text-center">員工列表</p>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
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
