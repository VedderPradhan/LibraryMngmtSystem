<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報管理</title>
</head>
<body>
	<div id="header">
		<h1>新宿図書館 SHINJUKU LIBRARY</h1>
	</div>
	<br>
	<br>
<jsp:include page="/menu.jsp" />
<div id="form3">
<h3>貸出情報管理</h3>

			
	<table border="1" align="center">
			<tr>
				<td>会員ID</td>				
				<td>資料ID</td>
				<td>貸出年月日</td>
				<td>返却期日(年月日)</td>				
				<td>備考</td>
				<td></td>
			</tr>
			
			<c:forEach items="${lentBooks}" var="lentBook">
				<tr>
					<td>${lentBook.memberId}</td>					
					<td>${lentBook.bookId}</td>
					<td>${lentBook.lendDate}</td>
					<td>${lentBook.returnDueDate}</td>
					<td>This is sample</td>
					<td>	<form action="/library/BookServlet?action=returnBook" method="post">		
								<input type="hidden" name="bookId" value="${lentBook.bookId}">
								<input type="hidden" name="memberId" value="${lentBook.memberId}">
								<input type="submit" value="返却" style="font-size: 20px">
							</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<br>
		
		<c:if test="${lendAble eq true}">
			<form action="/library/BookServlet?action=lendBook" method="post">
			資料ID: <input type="text" name="bookId">		
			<input type="submit" value="貸出" style="font-size: 20px">
			</form>
		</c:if>
		
		<c:if test="${lendAble eq false }">
			貸出中の冊数が5冊を超えているか,貸出中の資料の返却期日が過ぎています。
		</c:if>
	   



</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>