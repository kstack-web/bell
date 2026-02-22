<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Kanja" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約ページ</title>
<h3>当日診察予約</h3>

<p>
<%= ((Kanja)session.getAttribute("loginUser")).getKanjaName() %> さん
</p>

<hr>


</head>
<body>




<style>
  .menu-item {
    margin-bottom: 15px;
  }
</style>

<p class="menu-item">
  <a href="${pageContext.request.contextPath}/yoyakuCheck">
    1. 診察予約
  </a>
</p>

<p class="menu-item">
  <a href="${pageContext.request.contextPath}/YoyakuConfirm">
    2. 予約確認・取消
  </a>
</p>

<p class="menu-item">
  <br><a href="logout">ログアウト</a>
</p>

</body>
</html>
