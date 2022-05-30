<%@ page import="com.ChenZirong.week10.StringBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo-2-week 10</title>

</head>
<body>
    <h1>use java code to access String bean calss</h1>
    <jsp:useBean id="bean" class="com.ChenZirong.week10.StringBean" />
    <jsp:setProperty name="bean" property="message" value='<%= request.getParameter("message")%>' />
    <%

    %>
    Message:<%=bean.getMessage()%>

    <h2>use userBean to access StringBean calss in jap</h2>
    message(using getproperty):<jsp:getProperty name="bean" property="message"/><br>
</body>
</html>
