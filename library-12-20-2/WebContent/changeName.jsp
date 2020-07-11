<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Registration</title>
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
		<form action="/library/MemberServlet?action=changeMemberName" method="post">
		新しい名前: <input type="text" name="name">		
		<input type="submit" value="この名前で登録する" style="font-size: 20px">
	</form>
	</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>