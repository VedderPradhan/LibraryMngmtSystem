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
		<form action="/library/MemberServlet?action=confirm" method="post">
		名前: <input type="text" name="name"><br>
		住所：<input type="text" name="address"><br>
		電話番号：<input type="text" name="tel"><br>
		E-Mail: <input type="text" name="email"><br>	
		生年月日: <select name= "birth1">
<%for(int i=2019; i >= 1900; i--) {%>
<option value = "<%=i %>"><%=i %>
</option>
<%} %></select>
年&nbsp; 
<select name="birth2">
<%for(int i=1; i <= 12; i++) {%>
<option value = "<%= i %>"><%=i %>
</option>
<%} %>
</select>
月&nbsp; 
<select name="birth3">
<%for(int i=1; i <= 31; i++) {%>
<option value = "<%= i %>"><%=i %>
</option>
<%} %>
</select>
日&nbsp; <br><br>
		
	
		<input type="submit" value="登録" style="WIDTH: 80px; HEIGHT: 40px; font-size:20px;" >

	</form>
	</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>