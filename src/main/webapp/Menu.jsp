<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   <div id="nav">
        <ul>
        	<li><a href="${pageContext.request.contextPath}/boardList">billboard</a></li>
        	
        	<c:choose>
        		<c:when test="${sessionScope.loginMemberId == null}"> <%-- if 조건문 --%>
        			<%-- 로그인 x --%>
            <li><a href="${pageContext.request.contextPath}/memberJoinForm">join</a></li>
            <li><a href="${pageContext.request.contextPath}/memberLoginForm">login</a></li>
        		</c:when>
        	
        			<c:otherwise>
        			<%-- 모든 조건 불일치 시, 실행  --%>
		        	<%-- 로그인 o  --%>
		        	<li><a href="${pageContext.request.contextPath}/myInfo">${sessionScope.loginMemberId}</a></li>
				<li><a href="${pageContext.request.contextPath}/memberLogout">logout</a></li>
        			</c:otherwise>
        	</c:choose>
        	
        	
        	<%-- 로그인이 되어있지 않은 경우 --%>
			<%-- 로그인이 되어있는 경우 --%>
			
        </ul>
    </div>
    