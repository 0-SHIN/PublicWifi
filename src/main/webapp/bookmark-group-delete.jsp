<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BookmarkGroupDao" %>
<%@ page import="dto.BookmarkGroupDto" %>
<%@ page import="dao.BookmarkDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h2>북마크 그룹 삭제</h2>
	<div style=margin-bottom:20px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<%
		String id = request.getParameter("id");
		BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
		BookmarkGroupDto bookmarkGroup = bookmarkGroupDao.selectBookmarkGroup(id);
		BookmarkDao bookmarkDao = new BookmarkDao();
		int cnt = bookmarkDao.checkExistBookmark(id);
		if (cnt != 0) {
	%>
	<script>
		alert("북마크 정보가 등록되어 있는 북마크 그룹은 삭제하실 수 없습니다.");
		location.href="bookmark-group.jsp";
	</script>
	<%
		} else {
	%>
	<div style=margin-bottom:20px>
		북마크 그룹 이름을 삭제하시겠습니까?
	</div>
	
	<form method="get" action="bookmark-group-delete-submit.jsp">
		<table class="table-vertical">
			<tr>
				<th>북마크 이름</th>
				<td><%=bookmarkGroup.getName()%></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><%=bookmarkGroup.getOrderNum()%></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
				<a href="bookmark-group.jsp">돌아가기</a> |
				<button>삭제</button>
				<input type="hidden" name="id" value="<%=bookmarkGroup.getId()%>">
			</tr>
		</table>
	</form>
	<%
		} 
	%>
</body>
</html>
