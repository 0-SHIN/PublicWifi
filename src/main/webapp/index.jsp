<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dbmanager.DBManager" %>
<%@ page import="dao.WifiDao" %>
<%@ page import="dto.WifiDto" %>
<%@ page import="dao.HistoryDao" %>


<%!
	public void jspInit() {
		DBManager.truncateTable();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h2>와이파이 정보 구하기</h2>
	<div style=margin-bottom:20px>
		<a href="/">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | 
		<a href="bookmark-list.jsp">북마크 보기</a> | 
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
		<% 
			String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
			String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
		%>
	<form method="post" action="/" id="location">
		<div style=margin-bottom:10px>
			LAT:
			<input type="text" name="lat" value="<%=lat%>">
			LNT:
			<input type="text" name="lnt" value="<%=lnt%>">
			
			<button type="button" onclick="getLocation()">
				내 위치 가져오기
			</button>
			<button>
				근처 WIFI 정보 찾기
			</button>
		</div>
	</form>
	<table id="table">
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
            
	            <%
	            	if (lat.equals("0.0") && lnt.equals("0.0")) { 
	            %>
	            <tr>
		            <td colspan="17" style="text-align:center;">
		            	위치 정보를 입력한 후에 조회해 주세요
		            </td>
	            </tr>
	            <%
		           } else {
		        	    lat = request.getParameter("lat");
		            	lnt = request.getParameter("lnt");
		            	
		            	HistoryDao historyDao = new HistoryDao();
		            	WifiDao wifiDao = new WifiDao();
		            	ArrayList<WifiDto> wifiList = wifiDao.getNearbyWifiInfo(lat,lnt);
		            	if (wifiList.size() != 0) {
		            		historyDao.insertHistoryData(lat, lnt);
		            		for (WifiDto wifi: wifiList) {
	            %>
	            <tr>
	            	<td><%=wifi.getDistance()%></td>
	            	<td><%=wifi.getMgrNo()%></td>
	            	<td><%=wifi.getWrdofc()%></td>
	            	<td>
	            		<a href="detail.jsp?mgrNo=<%=wifi.getMgrNo()%>">
	            			<%=wifi.getMainNm()%>
	            		</a>
	            		</td>
	            	<td><%=wifi.getAdres1()%></td>
	            	<td><%=wifi.getAdres2()%></td>
	            	<td><%=wifi.getInstlFloor()%></td>
	            	<td><%=wifi.getInstlTy()%></td>
	            	<td><%=wifi.getInstlMby()%></td>
	            	<td><%=wifi.getSvcSe()%></td>
	            	<td><%=wifi.getCmcwr()%></td>
	            	<td><%=wifi.getCnstcYear()%></td>
	            	<td><%=wifi.getInoutDoor()%></td>
	            	<td><%=wifi.getRemars3()%></td>
	            	<td><%=wifi.getLat()%></td>
	            	<td><%=wifi.getLnt()%></td>
	            	<td><%=wifi.getWorkDttm()%></td>
            	</tr>
	            <%		}
		           	} else {
		         %>
		         <tr>
		            <td colspan="17" style="text-align:center;">
		            	위치 정보를 입력한 후에 조회해 주세요
		            </td>
	            </tr>
	            <%
		           	}
		         }
		        %>       
	</table>
	<script>
		function getLocation() {
			if("geolocation" in navigator) {
				navigator.geolocation.getCurrentPosition(success, error);
			} else {
				status.textContent = "위치 정보를 가져오는데 실패하였습니다.";
			}
		}
		
		function success(position) {
			document.forms.location.lat.value = position.coords.latitude;
			document.forms.location.lnt.value = position.coords.longitude;
		}
		
		function error(){
			status.textContent = "위치 정보를 가져오는데 실패하였습니다.";
		}
	</script>
</body>
</html>