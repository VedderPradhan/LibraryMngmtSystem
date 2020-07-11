<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/shopping/CartServlet?action=add" method="post">
		<input type = "hidden" name="item_code" value ="${item.code}">
		ID：Sample<b>${item.code }</b><br>
		氏名：Sample<b>${item.name }</b>
		<input type="submit" value="変更"><br>
		住所：Sample<b>${item.price }</b>
		<input type="submit" value="変更"><br>
		電話番号：Sample<b>${item.price }</b>
		<input type="submit" value="変更"><br>
		E-Mail：Sample<b>${item.price }</b>
		<input type="submit" value="変更"><br>
		生年月日：Sample<b>${item.price }</b>
		<input type="submit" value="変更"><br>
		退会年月日：Sample<br>
		貸出冊数：Sample<b>${item.price }</b>
		<input type="submit" value="退会">
		
		
	</form>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
<HTML>
      <HEAD>
 <TITLE>Date Of Birth Form</TITLE>
     </HEAD>
 <BODY>
        <form>
 <label for="inputdob">Date Of Birth</label><input type="date">
 <input type="submit">
        </form>
 </BODY>
 </HTML>
	 
	 
</body>
</html>