<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br>
<a href="hello-servlet">Hello Servlet</a><br>
<a href="${pageContext.request.contextPath}/test">点击我去往Test页面</a><br>
<a href="${pageContext.request.contextPath}/home">点击我去往Home页面</a><br>
</body>
</html>