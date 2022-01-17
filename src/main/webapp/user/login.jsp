<%--
  Created by IntelliJ IDEA.
  User: 1216916137
  Date: 2022-01-17
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<span style="color: red; ">${msg}</span>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">

    用户名：<input type="text" name="username" value=""/><br/>
    密  码：<input type="password" name="password" value=""/><br/>

    <button type="submit">提交</button>
</form>
</body>
</html>
