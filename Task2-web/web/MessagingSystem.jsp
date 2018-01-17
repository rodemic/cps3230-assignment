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
    String sessionKey = null;
    if(request.getParameter("LoginKeyInput")!=null && request.getParameter("agentID")!= null) {
        String loginKey = request.getParameter("LoginKeyInput");
        String agentID = request.getParameter("agentID");
        SessionToken st = MessagingSystem.login(loginKey, agentID);
        if(st == null)
            response.sendRedirect("Error.jsp");
        else
            sessionKey = st.getSessionKey();
    }else if(request.getParameter("sessionKey")!= null && request.getParameter("msg") != null){
        sessionKey = request.getParameter("sessionKey");
        String msg = request.getParameter("msg");
        out.print(msg);
    }

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

<div id="SendMessageBlk" style="display: none">
    <form action="sendMessage.jsp" method="post">
        To: <input type="text" name="TargetAgentField">
    Message: <input type="text" name="MessageField">
        <input type="text" value="<%= sessionKey%>" style="display: none" name="sessionKey">
    <input type="submit" name="SubmitMessage">
    </form>
</div>

</body>
</html>
