<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Registration</title>

<link href="style.css" rel="stylesheet" type = "text/css">

</head>
<body>
	<div id="header">
		<h1>新宿図書館 SHINJUKU LIBRARY</h1>
	</div>
	<br>
	<br>
	<div id="form3">
	
	<jsp:include page="/menu.jsp"/>
		<br><br>
	
	<form action="/library/BookServlet?action=memberBookSearch" method="post">
		会員ID: <input type="text" name="id">		
		<input type="submit" value="検索" style="font-size: 20px">
	</form>
	
	</div>	
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>