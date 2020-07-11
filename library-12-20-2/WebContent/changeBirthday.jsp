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
		<form action="/library/MemberServlet?action=changeMemberBirthday" method="post" >
		新しい生年月日: 
		<select name= "birth1">
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
日&nbsp; <br>
		

		<input type="submit" value="この生年月日で登録する" style="font-size: 20px">
	</form>
	</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>