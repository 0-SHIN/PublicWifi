<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
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
	<h2>북마크 목록</h2>
	<div style=margin-bottom:10px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	
	<table id="table">
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>와이파이명</th>
			<th>등록일자</th>
			<th>비고</th>
		</tr>
		<%
			BookmarkDao bookmarkDao = new BookmarkDao();
			ArrayList<BookmarkDto> bookmarkList = bookmarkDao.selectBookmarkList();
			
			if (bookmarkList.isEmpty()) {
		%>
		<tr>
			<td colspan="5" style="text-align:center;">
				북마크가 존재하지 않습니다.
			</td>
		</tr>
		<%
			} else {
				for (BookmarkDto bookmark: bookmarkList) {
		%>
		<tr>
			<td><%=bookmark.getId() %></td>
			<td><%=bookmark.getBookmarkGroupName() %></td>
			<td><%=bookmark.getMainNm() %></td>
			<td><%=bookmark.getRegistDt() %></td>
			<td style="text-align:center;">
				<a href="bookmark-delete.jsp?id=<%=bookmark.getId()%>">삭제</a>
			</td>
		</tr>
		<%
				}
			}
		%>
	</table>
</body>
</html>