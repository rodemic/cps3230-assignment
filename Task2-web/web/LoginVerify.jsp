<%@ page import="main.java.com.agentsystem.LoginToken" %>
<%@ page import="main.java.com.agentsystem.Supervisor" %>
<%@ page import="main.java.com.agentsystem.Agent" %><%--
  Created by IntelliJ IDEA.
  User: rodemic
  Date: 17/01/2018
  Time: 01:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Verify</title>
</head>
<body>
<%
    String agentID=request.getParameter("agentID");
    Agent a = new Agent(agentID);
    LoginToken lt = Supervisor.getLoginKey(a);

    if(lt != null)
    {
        out.print("Login Token is: <div id = \"LoginKeyBox\"> "+lt.getLoginKey()+"</div>");
    }
    else
        response.sendRedirect("Error.jsp");
%>
<form action="MessagingSystem.jsp" method="post">
    Login Key: <input name="LoginKeyInput" type="text">
    <input type="submit" name="submit" value="Submit">
</form>

</body>
</html>
