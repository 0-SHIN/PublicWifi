<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.HistoryDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		HistoryDao historyDao = new HistoryDao();
		String id = request.getParameter("id");
		System.out.println(id);
		historyDao.deleteHistoryData(id);
	%>
	<script>
		alert("히스토리 정보를 삭제하였습니다.");
		location.href="history.jsp";
	</script>
</body>
</html>