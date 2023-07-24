<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>

<style type="text/css">

.formWrap{
margin-bottom: 0px;
width: 400px;
border: 3px solid black;
padding: 10px;
border-radius: 10px;
margin: 0 auto;

}
.formInput{
margin-bottom: 0px;
border: 1px solid #dfdfdf;
border-radius: 7px;
padding: 7px;
display: flex;
align-items: center;
flex-wrap: wrap;
}

.formInput > input[type='text'], .formInput > input[type='date']{
   border-radius: 20px;
   width: 100%;
   box-sizing: border-box;
   border: none;
   outline: none;
   padding: 3px;
   font-size: 10px;
}

.formInput > input[type='date']{
font-family: auto;
}
.formInput > input[type='button']{
width: 100%;
border-radius: 7px;
background-color: black;
cursor: pointer;
color:white;
margin-bottom: 3px;
}

.formSubmit{
border: none;
margin-top: 10px;
margin-bottom: 10px;
}

.formSubmit > input{
background-color: grey;
border-radius: 10px;
width: 100%;
padding: 10px;
font-size: 20px;
cursor: pointer;
font-weight: bold;
}

.formInputOn{
border: 2px solid yellow;
}

.formInputErr{
border: 2px  solid red !important;
}
input[type='submit']:hover{
background-color:darkgrey;
color:orange;
cursor: pointer;
}
.checkMsg{
margin-top: 5px;
margin-left: 10px;
margin-bottom: 5px;
font-size: 13px;
}

table{
margin: 0 auto;
}

</style>
</head>

<body style="background-color: green">
    <div id="header">My Information Page</div>
   
<%@ include file="Menu.jsp" %>
 
    <div id="contents">
    <%-- 아이디(중복확인--ajax), 비밀번호, 이름, 생년월일, 주소(다음 우편 서비스) --%>
    
    
    
        <div class="formWrap">
       
        <div class="formInput">
        	<input type="text" name="joinId" placeholder="ID" value="${mInfo.mid}" readonly style="background-color:green"/> <!-- disabled --> 
        </div>              
       
         <div class="formInput">
        	<input type="text" name="joinPw" placeholder="PW" value="${mInfo.mpw}" style="background-color:green" readonly/>
        	</div>
        	
        	
         <div class="formInput">
        	<input type="text" name="joinName" placeholder="NAME" value="${mInfo.mname}" style="background-color:green" disabled/>
        	</div>
        	
        	
        	 <div class="formInput">
        	<input type="date" name="joinBirth" placeholder="BIRTH" value="${mInfo.mbirth}" style="background-color:green" disabled/>
        	</div>
        	
        	
        		<div class="formInput">
        			<input type="text" name="postcode" value="${mInfo.maddr}" placeholder="우편번호" style="background-color:green" disabled />	
        		</div>
        	
     
        </div>
      
        
    </div>
</body>

</html>