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
<h3>会員情報管理</h3>

		
		<table border="1" align="center">
			<tr>
				<td>ID</td><td>${memberSearchInfo.id}</td>
			</tr>
			<tr>
				<td>氏名</td>
				<td>${memberSearchInfo.name}</td>
				<td><form action="/library/changeName.jsp" method="post">		
						<input type="submit" value="変更" style="font-size: 20px"><br>
					</form>
				</td>
			</tr>
			<tr>
				<td>住所：</td><td>${memberSearchInfo.address}</td>
				<td><form action="/library/changeAddress.jsp" method="post">		
						<input type="submit" value="変更" style="font-size: 20px"><br>
					</form>
				</td>
			</tr>
			<tr>
				<td>電話番号</td><td>${memberSearchInfo.tel}</td>
				<td><form action="/library/changeTel.jsp" method="post">		
						<input type="submit" value="変更" style="font-size: 20px"><br>
					</form>
				</td>
			</tr>
			<tr>
				<td>E-Mail</td><td>${memberSearchInfo.email}</td>
				<td><form action="/library/changeEmail.jsp" method="post">		
						<input type="submit" value="変更" style="font-size: 20px"><br>
					</form>
				</td>
			</tr>
			<tr>
				<td>生年月日</td><td>${memberSearchInfo.birthday}</td>
				<td><form action="/library/changeBirthday.jsp" method="post">		
						<input type="submit" value="変更" style="font-size: 20px"><br>
					</form>
				</td>
			</tr>
		</table>

		<br>
		退会年月日: ${memberSearchInfo.withdrawDate} <br>
		貸出冊数：${numberOfBooksLent}
		<form action="/library/MemberServlet?action=memberWithdraw" method="post">	
		
		<input type="hidden" name="id" value="${withdrawMemberId}">
		<input type="submit" value="退会する" style="font-size: 20px"><br>		
		</form>	
		<c:if test="${isMemeberWithdrawAllowed eq false}">
      		<p style = "color:red">貸出中の資料があるため退会できません</p>
    	</c:if>
		
	   <form action="/library/BookServlet?action=memberBookSearch2" method="post">
		<input type="submit" value="貸出中本の一覧" style="font-size: 20px"><br>		
		</form>
		
	   <form action="/library/MemberServlet?action=allLentBooks" method="post">	
		<input type="submit" value="履歴" style="font-size: 20px"><br>		
		</form>
		</div>



</body>
</html>