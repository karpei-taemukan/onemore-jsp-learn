<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.formInput>input[type='submit']{
padding: 5px;
width:90%;
font-size: 20px;
}
.formTextArea{ 
margin-top: 20px;
}
.formTextArea>textarea {
	border-radius: 20px;
	text-align: center;
}
</style>
</head>

<body style="background-color:darkgreen">
<div id="wrap">

    <div id="header">
   <h1>BillBoard Page</h1>
<%@ include file="Menu.jsp" %>
</div>

    <div id="contents">
    <h1>BOARD LIST</h1>
    <form action="${pageContext.request.contextPath}/boardWrite" method="post">
    
    <div class="formInput">
    <input name="boardTitle" style="background-color:green" type="text" placeholder="title">
    </div>
    
    <div class="formTextArea">
	<textarea name="boardContent" style="background-color:green; height:200px; width: 200px" placeholder="content"></textarea>
    </div>
	
	<div class="formInput">
	<input style="background-color:green" type="submit" value="assign">
    </div>
    
    </form>

    </div>
</div>
</body>
</html>