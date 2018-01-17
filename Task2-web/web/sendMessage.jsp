<%@ page import="main.java.com.agentsystem.MessagingSystem" %><%--
  Created by IntelliJ IDEA.
  User: rodemic
  Date: 17/01/2018
  Time: 04:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Message</title>
</head>
<body>
<%
    String sessionKey = request.getParameter("sessionKey");
    String targetAgentID = request.getParameter("TargetAgentField");
    String message = request.getParameter("MessageField");
    Boolean sent = MessagingSystem.sendMessage(sessionKey,targetAgentID,message);
    if(sent){
        String msg = "<div id=\"sendMessageResponse\">Message Successfully Sent<\\div>";
    }else{
        response.sendRedirect("index.jsp");
    }

    if(sessionKey == null||targetAgentID==null||message==null)
        response.sendRedirect("Error.jsp");
%>
$('#inset_form').html('<form action="MessagingSystem.jsp" name="form" method="post" style="display:none;">
                        <input type="hidden" name="sessionKey" value="<%=sessionKey%>" />
                        <input type="hidden" name="msg" value="<%=message%>"/></form>');
document.forms['form'].submit();

</body>
</html>
