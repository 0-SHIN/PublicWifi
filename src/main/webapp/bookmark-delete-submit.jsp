<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BookmarkDao" %>
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
		BookmarkDao bookmarkGroupDao = new BookmarkDao();
		bookmarkGroupDao.deleteBookmarkData(id);
	%>
	<script>
		alert("북마크 정보를 삭제하였습니다");
		location.href="bookmark-list.jsp";
	</script>
</body>
</html>