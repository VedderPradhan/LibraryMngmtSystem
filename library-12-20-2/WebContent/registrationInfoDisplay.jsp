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
		<div id="form3">
		<br><br>会員ID: ${memberRegisteredInfo.id} で以下の会員情報を登録しました！<br>
		名前: ${memberRegisteredInfo.name} <br>
		住所：${memberRegisteredInfo.address} <br>
		電話番号：${memberRegisteredInfo.tel} <br>
		E-Mail: ${memberRegisteredInfo.email} <br>
		生年月日: ${memberRegisteredInfo.birthday} <br>
		入会年月日: ${memberRegisteredInfo.joinedDate} <br>
	
	</div>
		<div id="form4">
  		Designed and Developed by: J-LAN
	</div>
</body>
</html>