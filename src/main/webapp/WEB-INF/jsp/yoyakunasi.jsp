<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認・取消</title>
</head>
<body>
	<h3>予約確認・取消</h3>
	<c:out value="${loginUser.kanjaName}" />様の予約はありません。
<br>
	<p><a href="yoyaku">診察予約</a></p>
	<p><a href="logout">ログアウト</a></p>
</body>
</html>