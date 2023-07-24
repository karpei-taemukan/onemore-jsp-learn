<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#header{
display: flex;
justify-content: space-around;
align-items:center;
}

#wrap{
border: 1px solid black;
width: 500px;
margin: 100px auto;
padding: 10px;
border-radius: 20px;
}
#contents{
display: flex;
flex-direction:column;
align-items: center;
border: 2px solid yellow;
background-color: olive;
}
.formInput > input{
border-radius: 20px;
border:none;
outline: none;
text-align: center;
margin: 0 10px;
}
button{
border:none;
outline: none;
border-radius: 20px;
padding: 5px;
font-size: 20px;
cursor: pointer;
}
.formTextArea{ 
margin-top: 20px;
}
.formTextArea>textarea {
	border-radius: 20px;
}
.contentWrap{
display: flex;
flex-direction: column;
align-items: center;
}
.formBtn{
display: flex;
justify-content: space-around;
}
</style>
</head>

<body style="background-color:darkgreen">
<div id="wrap">

    <div id="header">
   <h1>BillBoard Content Page</h1>
	
</div>

    <div id="contents">
    <h1>BOARD CONTENT</h1>
    <form action="${pageContext.request.contextPath}/boardWrite">
    
    <div class="contentWrap">
    <div class="formInput">
    <input readonly name="boardTitle" style="background-color:green" type="text" placeholder="title"
     value="${board.btitle}">
    </div>
    
     <div class="formInput">
    <input readonly name="boardTitle" style="background-color:green" type="text" placeholder="title"
     value="${board.bwriter}">
       <input readonly name="boardTitle" style="background-color:green" type="text" placeholder="title"
     value="${board.bhits}">
    </div>
    
    
    <div class="formTextArea">
	<textarea readonly style="background-color:green; height:200px; width: 200px" placeholder="content">
	${board.bcontent}
	</textarea>
	<%--<p>로 쓰면 공백 줄바꿈 적용 안됨 --%>
    </div>
	
    </div>
	<div class="formBtn">
	<c:if test="${sessionScope.loginMemberId == board.bwriter}">
	<button style="background-color:green" type="button">Update</button>
	<%--
	 bstate = 1 => bstate = 0 을 수정
	 
	 1. 요청 URL: /boardDelete
	 2. 글삭제 처리후 '??번 글 삭제 됨' 출력
	 3. 게시판(글목록 페이지로 이동)
	 --%>
	<button style="background-color:green" type="button" onclick="location.href = '${pageContext.request.contextPath}/boardDelete?delBno=${board.bno}'">Delete</button>
	</c:if>
	<button style="background-color:green" type="button" onclick="location.href = '${pageContext.request.contextPath}/boardList'">Board List</button>
    </div>
    
    </form>

    </div>
</div>
</body>
</html>