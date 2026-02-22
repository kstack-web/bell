<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<title>確認・取消</title>
</head>
<body>
    <h3>予約確認・取消</h3>
    <c:out value="${loginKanja.kanjaName}" />様の予約を取り消しました。
    <br>
    <p>
    <a href="logout">ログアウト</a>
    </p>
</body>
</html>
