<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約確認・取消</title>
</head>
<body>
    <h3>予約確認・取消</h3>

<p><c:out value="${loginKanja.kanjaName}" />様<br></p>

現在予約内容<br>
<c:set var="t" value="${loginYoyaku.time}" />
<c:choose>
  <c:when test="${fn:length(t) == 4}">
    0${fn:substring(t,0,1)}:00～${fn:substring(t,2,4)}:00
  </c:when>
  <c:otherwise>
    ${fn:substring(t,0,2)}:00～${fn:substring(t,3,5)}:00
  </c:otherwise>
</c:choose>

<!-- ★ ここを追加 -->
<p><span style="color: red;">上記予約を取り消しますか？</span></p>

<p>
<form action="yoyakucancel" method="post">
    <button type="submit">取消</button>
</form>
</p>

<p><a href="logout">ログアウト</a></p>
</body>
</html>
