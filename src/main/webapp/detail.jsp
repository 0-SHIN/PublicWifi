<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.WifiDao" %>
<%@ page import="dto.WifiDto" %>
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
	<h2>와이파이 상세 정보</h2>
	<a href="/">홈</a> | 
	<a href="history.jsp">위치 히스토리 목록</a> | 
	<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
	<a href="bookmark-list.jsp">북마크 보기</a> | 
	<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	
	<%
		String mgrNo = request.getParameter("mgrNo");
		WifiDao wifiDao = new WifiDao();
		WifiDto wifi = wifiDao.selectWifi(mgrNo);
		BookmarkGroupDao bookmarkGroupDao = new BookmarkGroupDao();
		ArrayList<BookmarkGroupDto> bookmarkGroupList = bookmarkGroupDao.selectBookmarkGroupList();
		
	%>
	<form method="post" action="bookmark-add-submit.jsp">
	<select name="bookmark" id="bookmark-select">
		<option value="">북마크 그룹 이름 선택</option>
		<%
			for (BookmarkGroupDto bookmarkGroup: bookmarkGroupList) {
				
		%>
		<option value="<%=bookmarkGroup.getId()%>">
			<%=bookmarkGroup.getName() %>
		</option>
		<%
			}
		%>
	</select>
	<button>북마크 추가하기</button>
	<input type="hidden" name="mgrNo" value="<%=mgrNo%>">
	</form>
	<table class="table-horizon">
		<tr>
			<th>거리(Km)</th>
			<td><%=wifi.getDistance()%></td>
		</tr>
		<tr>
			<th>관리번호</th>
			<td><%=wifi.getMgrNo()%></td>
		</tr>
		<tr>
			<th>자치구</th>
			<td><%=wifi.getWrdofc()%></td>
		</tr>
		<tr>
			<th>와이파이명</th>
			<td>
				<a href="detail.jsp?mgrNo=<%=wifi.getMgrNo()%>">
	            		<%=wifi.getMainNm()%>
	            </a>
	        </td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td><%=wifi.getAdres1()%></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%=wifi.getAdres2()%></td>
		</tr>
		<tr>
			<th>설치위치(층)</th>
			<td><%=wifi.getInstlFloor()%></td>
		</tr>
		<tr>
			<th>설치유형</th>
			<td><%=wifi.getInstlTy()%></td>
		</tr>
		<tr>
			<th>설치기관</th>
			<td><%=wifi.getInstlMby()%></td>
		</tr>
		<tr>
			<th>서비스구분</th>
			<td><%=wifi.getSvcSe()%></td>
		</tr>
		<tr>
			<th>망종류</th>
			<td><%=wifi.getCmcwr()%></td>
		</tr>
		<tr>
			<th>설치년도</th>
			<td><%=wifi.getCnstcYear()%></td>
		</tr>
		<tr>
			<th>실내외구분</th>
			<td><%=wifi.getInoutDoor()%></td>
		</tr>
		<tr>
			<th>WIFI접속환경</th>
			<td><%=wifi.getRemars3()%></td>
		</tr>
		<tr>
			<th>X좌표</th>
			<td><%=wifi.getLat()%></td>
		</tr>
		<tr>
			<th>Y좌표</th>
			<td><%=wifi.getLnt()%></td>
		</tr>
		<tr>
			<th>작업일자</th>
			<td><%=wifi.getWorkDttm()%></td>
		</tr>
	</table>
</body>
</html>