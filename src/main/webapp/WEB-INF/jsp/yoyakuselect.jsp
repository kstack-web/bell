<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Kanja" %>
<html>
<head>
<meta charset="UTF-8">
<title>診察予約画面</title>
  <h3>当日診察予約</h3>

<p>
<%= ((Kanja)session.getAttribute("loginUser")).getKanjaName() %> さん
</p>

<hr>
<style>
    .time-btn {
        width: 150px;
        height: 50px;
        font-size: 18px;
        text-align: center;
        margin: 5px 0;
        display: block;
    }
</style>

</head>
<body>
   
    <p>受付可能時間</p>
   <form action="<%= request.getContextPath() %>/yoyakuTimeConfirm" method="post">
  
        <%
            List<String> times = (List<String>)request.getAttribute("availableTimes");
            for(String t : times){

                // "9-10" → ["9", "10"]
                String[] parts = t.split("-");
                String start = parts[0] + ":00";
                String end   = parts[1] + ":00";

                String display = start + "～" + end;
        %>
            <button class="time-btn" type="submit" name="time" value="<%= t %>">
                <%= display %>
            </button>
        <% } %>
    </form>
	<p>時間を選択してください</p>

    <a href="login">ログアウト</a>
</body>
</html>
