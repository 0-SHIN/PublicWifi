<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BookmarkGroupDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%	
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		
		BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
		bookmarkGroupDao.deleteBookmarkGroup(id);
	%>
	<script>
		alert("북마크 그룹 정보를 삭제하였습니다.");
		location.href="bookmark-group.jsp";
	</script>
</body>
</html>