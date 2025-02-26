<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.BookmarkGroupDao" %>
<%@ page import="dto.BookmarkGroupDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h2>북마크 그룹</h2>
	<div style=margin-bottom:10px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<button type="button" onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	<table id="table">
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>순서</th>
			<th>등록일자</th>
			<th>수정일자</th>
			<th>비고</th>
		</tr>
		<%
			BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
			ArrayList<BookmarkGroupDto> bookmarkGroupList = bookmarkGroupDao.selectBookmarkGroupList();
			
			if (bookmarkGroupList.isEmpty()) {
		%>
		<tr>
			<td colspan="6" style="text-align:center;">
				북마크 그룹이 존재하지 않습니다.
			</td>
		</tr>
		<%
			} else {
				for (BookmarkGroupDto bookmarkGroup: bookmarkGroupList) {
		%>
		<tr>
			<td><%=bookmarkGroup.getId() %></td>
			<td><%=bookmarkGroup.getName() %></td>
			<td><%=bookmarkGroup.getOrderNum() %></td>
			<td><%=bookmarkGroup.getRegistDt() %></td>
			<td><%=bookmarkGroup.getEditDt() %></td>
			<td style="text-align:center;">
				<a href="bookmark-group-edit.jsp?id=<%=bookmarkGroup.getId()%>">수정</a>
				<a href="bookmark-group-delete.jsp?id=<%=bookmarkGroup.getId()%>">삭제</a>
			</td>
		</tr>
		<%
				}
			}
		%>
	</table>
	
	
</body>
</html>