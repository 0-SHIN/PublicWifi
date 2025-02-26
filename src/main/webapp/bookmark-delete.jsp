<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BookmarkDao" %>
<%@ page import="dto.BookmarkDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h2>북마크 삭제</h2>
	<div style=margin-bottom:10px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
		<div style=margin-bottom:20px>
		북마크를 삭제하시겠습니까?
	</div>
	<%
		String id = request.getParameter("id");
		BookmarkDao bookmarkDao = new BookmarkDao();
		BookmarkDto bookmark = bookmarkDao.selectBookmarkData(id);
	%>
	<form method="post" action="bookmark-delete-submit.jsp">
		<table class="table-vertical">
			<tr>
				<th>북마크 이름</th>
				<td><%=bookmark.getBookmarkGroupName()%></td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td><%=bookmark.getMainNm()%></td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td><%=bookmark.getRegistDt()%></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
				<a href="bookmark-list.jsp">돌아가기</a> |
				<button>삭제</button>
				<input type="hidden" name="id" value="<%=bookmark.getId()%>">
			</tr>
		</table>
	</form>
</body>
</html>
