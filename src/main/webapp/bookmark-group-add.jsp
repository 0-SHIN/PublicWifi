<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h2>북마크 그룹 추가</h2>
	<div style=margin-bottom:10px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<form method="post" action="bookmark-group-add-submit.jsp" autocomplete=off>
		<table class="table-vertical">
			<tr>
				<th>북마크 이름</th>
				<td>
					<input type="text" name="name" >
				</td>
			</tr>
			<tr>
				<th>순서</th>
				<td>
					<input type="text" name="order">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
				<a href="bookmark-group.jsp">돌아가기</a> |
				<button>추가</button>
			</tr>
		</table>
	
	</form>
</body>
</html>
