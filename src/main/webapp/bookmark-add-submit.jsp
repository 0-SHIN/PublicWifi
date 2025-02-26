<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BookmarkDao" %>
<%@ page import="dto.BookmarkDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		String mgrNo = request.getParameter("mgrNo");
		String bookmarkId = request.getParameter("bookmark");
		
		if (bookmarkId.equals("None")) {
	        response.sendRedirect(request.getHeader("Referer"));
	        return;
	    }
		
		BookmarkDao bookmarkDao = new BookmarkDao();
		bookmarkDao.insertBookmarkData(bookmarkId, mgrNo);
	%>
	<script>
		alert("북마크 정보를 추가하였습니다.");
		location.href="bookmark-list.jsp";
	</script>
</body>
</html>