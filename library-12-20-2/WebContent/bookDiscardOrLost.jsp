<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="style.css" rel="stylesheet" type = "text/css">

<title>Discard</title>
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
			資料ID: ${discardBookInfo.id}<br>
			ISBN番号: ${discardBookInfo.isbn} <br>
			資料名：${discardBookInfo.name}<br>		
			入荷日: ${discardBookInfo.arrivalDate}<br>
			廃棄日: ${discardBookInfo.disposalDate}(何も書いてない場合は、本はまだ廃棄されていない状態です。)<br>
			備考: ${discardBookInfo.remarks}<br>
			<form action="/library/BookServlet?action=registBookIdDiscardOrLost" method="post">
				備考: <input type="text" name="remarks">
				<input type="submit" value="廃棄・紛失">
			</form>	
			
			</div>
	<div id="form4">
  		Designed and Developed by: J-LAN
	</div>		
</body>
</html>