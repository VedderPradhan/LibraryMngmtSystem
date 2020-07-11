<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Registration</title>


<link href="style.css" rel="stylesheet" type = "text/css">
</head>
<body>
		<div id="header">
		<h1>新宿図書館 SHINJUKU LIBRARY</h1>
	</div>
	<br>
	<br>
	<jsp:include page="/menu.jsp"/>
		<br><br><br>
		
<div id="form3">
		<form action="/library/BookServlet?action=registBook" method="post">
			ISBN番号: <input type="text" name="isbn"><br>
			資料名：<input type="text" name="bookName"><br>
	
		
			分離コード：	<select name="genreCode">
								<option value="0">総記(0)
								<option value="1">哲学(1)
								<option value="2">歴史(2)
								<option value="3">社会科学(3)
								<option value="4">自然科学(4)
								<option value="5">技術(5)
								<option value="6">産業(6)
								<option value="7">芸術(7)
								<option value="8">言語(8)
								<option value="9">文学(9)					
							</select><br>						
			著者: <input type="text" name="writer"><br>	
			出版社: <input type="text" name="publisher"><br>
			出版日: <input type="text" name="publishedDate"><br><br>
			<input type="submit" value="資料登録" style="font-size: 20px">
	
		</form>
	</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>