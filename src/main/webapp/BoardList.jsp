<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
<style type="text/css">
.boardWrap{
padding: 10px;
background-color: darkgreen;
border-radius: 20px;
border: none;
}

table{
width: 100%;
border: 4mm ridge rgba(211, 220, 50, .6);
border-collapse: separate;
}
th,td{
border: thick double #2D4356;
}
td{
padding-top: 5px;
padding-bottom: 7px;
text-align: center;
}
.btitle{
text-align: left;
padding-left: 3px;
}
.boardUtil{
border: none;
padding-top: 10px;
}
</style>
</head>

<body style="background-color: green">
<div id="wrap">

    <div id="header">
    BillBoard Page
    </div>
    
<%@ include file="Menu.jsp" %>

    <div id="contents">
    <h1>BOARD LIST ${params.searchTitle}</h1>
    
    <div class="boardWrap">
    
	<table>
	<thead>
	<tr>
		<th style="width: 20px">Number</th>
		<th style="width: 100px">Title</th>
		<th style="width: 80px">Writer</th>
		<th style="width: 80px">View</th>
	</tr>
	</thead>
	<tbody>
	<%-- bList 반복 시작  --%>
	<c:forEach items="${bList}" var="board">
	<tr>
		<td>${board.bno}</td>
		<td class="btitle">
		
		<a href="${pageContext.request.contextPath}/boardView?viewBno=${board.bno}">${board.btitle}</a>
		</td>
		<td>${board.bwriter}</td>
		<td>${board.bhits}</td>

	</tr>
	</c:forEach>
	
	<%-- bList 반복 끝 --%>
	</tbody>
	</table>
	
	<div class="boardUtil">
	<%-- 로그인이 되면 출력 --%>
	<c:if test="${sessionScope.loginMemberId != null}">
	<button onclick="location.href='${pageContext.request.contextPath}/boardWriteForm'">Write</button>
	</c:if>
	<form action="${pageContext.request.contextPath}/boardSearch" method="get">
	<input type="text" name="searchTitle" placeholder="제목 검색" />
	<input type="submit" value="Search">
	</form>
	</div>
	
    </div>
    </div>
</div>

</body>
</html>