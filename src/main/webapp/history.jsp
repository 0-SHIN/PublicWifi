<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.HistoryDao" %>
<%@ page import="dto.HistoryDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h2>위치 히스토리 목록</h2>
	<div style=margin-bottom:10px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<form method="post" action="history-delete.jsp">
		<table id="table">
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
			<%	
				HistoryDao historyDao = new HistoryDao();
				ArrayList<HistoryDto> historyList = historyDao.selectHistoryData();
				if (historyList.isEmpty()) {
			%>
			<tr>
				<td colspan="5" style="text-align:center;">
			            	위치 정보 조회 이력이 없습니다
			    </td>
			</tr>
			<% 
				} else { 
					for (HistoryDto history: historyList) {
			%>
			<tr>
				<td><%=history.getId()%></td>
				<td><%=history.getLat()%></td>
				<td><%=history.getLnt()%></td>
				<td><%=history.getViewDt()%></td>
				<td>
				<button>삭제</button>
				<input type="hidden" name="id" value="<%=history.getId()%>">
				</td>
			</tr>
			<% 		}
				}
			%>
		</table>
	</form>
</body>
</html>