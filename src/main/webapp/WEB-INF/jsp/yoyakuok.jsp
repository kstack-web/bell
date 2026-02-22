<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Kanja" %>

<html>
<head><meta charset="UTF-8"><title>予約完了</title>
<h3>当日診察予約</h3>
<p>
<%= ((Kanja)session.getAttribute("loginUser")).getKanjaName() %> さん
</p>

<hr>
</head>
<body>

<p>
<%
String date = (String)request.getAttribute("reservedDate");
String[] d = date.split("-");
String jpDate = Integer.parseInt(d[1]) + "月" + Integer.parseInt(d[2]) + "日";

String time = (String)request.getAttribute("reservedTime");
String[] t = time.split("-");
String jpTime = t[0] + ":00～" + t[1] + ":00";
%>
</p>

<p>
    <%= jpDate %> <%= jpTime %> の枠で<br>予約を受付ました。<br>
    <br>優先予約のためお待たせする場合がございます。<br>
    気を付けてご来院ください。<br>
</p>

</body>
</html>



 <a href="logout">ログアウト</a>

</body>
</html>