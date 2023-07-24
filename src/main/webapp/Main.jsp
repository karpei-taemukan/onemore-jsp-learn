<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
</head>

<body style="background-color: green">
    <div id="header">
    Main Page
    </div>
 <%@ include file="Menu.jsp" %>
    <div id="contents">
       <h1>contents</h1>
       <h2>로그인 아이디: ${sessionScope.loginMemberId}</h2>
    </div>
</body>
</html>