<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Registration Confirm</title>
</head>
<body>	
	<div id="header">
		<h1>新宿図書館 SHINJUKU LIBRARY</h1>
	</div>
	<br>
	<br>
	<jsp:include page="/menu.jsp"/>		
		<br><br>
		<div id="form3">
			ISBN番号: ${book.isbn}<br>
			資料名：${book.name}<br>		
			分類コード：${book.categoryCode}<br>
			著者: ${book.author}<br>
			出版社: ${book.publisher}<br>
			出版日: ${book.publishDate}<br>
		<form action="/library/BookServlet?action=registConfirmBook" method="post">
		<br>
		<input type="submit" value="この内容で資料登録する" style="font-size: 20px">
		</form>	
		</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>