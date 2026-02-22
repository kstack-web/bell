<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="model.Kanja" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約確認</title>

<style>
.question {
    font-weight: 300;
    color: #555;
}

.time {
    font-size: 16px;
    margin-bottom: 12px;   /* 少しだけ詰める */
}

.btn {
    margin-top: 12px;     /* pタグの余白を半分くらい */
}
</style>
<h3>当日診察予約</h3>

<p>
<%= ((Kanja)session.getAttribute("loginUser")).getKanjaName() %> さん
</p>

<hr>

</head>

<body>

<p class="question">以下の時間で予約しますか？</p>

<c:set var="t" value="${time}" />

<p class="time">
予約時間：
${fn:substringBefore(t, "-")}:00～
${fn:substringAfter(t, "-")}:00
</p>

<p class="btn">
    <form action="yoyakuExec" method="post">
        <input type="hidden" name="time" value="${t}">
        <input type="submit" value="予約する">
    </form>
</p>

<p class="btn">
    <a href="yoyaku">診察予約へ戻る</a>
</p>

</body>
</html>