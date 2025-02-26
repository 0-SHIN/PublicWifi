<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.WifiDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>와이파이 정보 구하기</title>
</head>
<body>
<%
	int cnt = WifiDao.insertWifiData();
%>
<div style="text-align:center">
	<h2><%=cnt%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
	<a href="/">홈 으로 가기</a>
</div>
</body>
</html>