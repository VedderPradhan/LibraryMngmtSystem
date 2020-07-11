<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>貸出履歴</title>
</head>
<body>
	<div id="header">
		<h1>新宿図書館 SHINJUKU LIBRARY</h1>
	</div>
	<br>
	<br>
<jsp:include page="/menu.jsp" />

	<div id="form3">		

<h3>貸出履歴</h3>


		<table border="1" align="center">
			<tr>
				<td>会員ID</td>				
				<td>資料ID</td>
				<td>貸出年月日</td>
				<td>返却期日(年月日)</td>		
				<td>返却年月日</td>		
				<td>備考</td>
				<td></td>
			</tr>
			
			<c:forEach items="${bookList}" var="lentBook">
				<tr>
					<td>${lentBook.memberId}</td>					
					<td>${lentBook.bookId}</td>
					<td>${lentBook.lendDate}</td>
					<td>${lentBook.returnDueDate}</td>
					<td>${lentBook.returnDate}</td>
					<td>This is sample</td>
				</tr>
			</c:forEach>
		</table>
		
		<br>
</div>
	   



	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>