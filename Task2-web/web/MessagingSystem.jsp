<%@ page import="main.java.com.agentsystem.MessagingSystem" %>
<%@ page import="main.java.com.agentsystem.SessionToken" %><%--
  Created by IntelliJ IDEA.
  User: rodemic
  Date: 17/01/2018
  Time: 01:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messaging System</title>
</head>
<body>
<%
    String loginKey = request.getParameter("LoginKeyInput");
    String agentID = request.getParameter("agentID");
    SessionToken st = MessagingSystem.login(loginKey,agentID);

    if(st == null)
        response.sendRedirect("Error.jsp");
%>

<script>function toggleHide(divID){
    var x = document.getElementById(divID);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}</script>

<button onclick="toggleHide('SendMessageBlk')" name="SendMessageButton">Send Message</button>
<button onclick="toggleHide('ReadMessagesBlk')" name="ReadMessageButton">Read Messages</button>
<form action="index.jsp">
    <input type="submit" value="Log out" name="LogOutButton"/>
</form>

</body>
</html>
