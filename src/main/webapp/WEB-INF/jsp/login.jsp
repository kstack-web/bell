<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>ログイン</title></head>
<body>

<h3>当日診察予約</h3>

<form action="login" method="post">
    患者番号：<input type="text" name="kanjaID" size="10"><br>
    生年月日：<input type="text" name="bday" size="10"><br>
    <input type="submit" value="次へ" style="margin-top:20px;">
</form>
<img src="images/すず子.png" alt="すず子" width="180">
<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>


</body>
</html>
