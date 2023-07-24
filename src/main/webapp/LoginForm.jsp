<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Main.css"/>
   <script src="https://kit.fontawesome.com/442b712813.js" crossorigin="anonymous"></script>
<style>
#formWrap{
border: 1px solid black;
width: 500px;
margin: 100px auto;
padding: 10px;
border-radius: 20px;
}
input[type='text']{
border:none;
outline: none;
width: 100%;
height: 30px;
box-sizing: border-box;
}
.formInput{
    border: 2px solid #dfdfdf;
    border-radius: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
    display: flex;
    align-items: center;
}


input[type='submit']{
background-color: grey;
border-radius: 10px;
width: 100%;
padding: 10px;
font-size: 20px;
cursor: pointer;
font-weight: bold;
}

input[type='submit']:hover{
background-color:darkgrey;
color:orange;
cursor: pointer;
}


.checkMsg{
    margin-top: 5px;
    margin-bottom: 15px;
    color:red;
    font-size: 13px;
}
.d-none{
display: none;
}
.formIcon{
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 3px;
}

.formInputOn{
border: 2px solid yellow;
}

.formInputErr{
border: 2px  solid red !important;
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
</style>
</head>



    <body style="background-color:darkgreen">
    
    <div id="header">
    Login Page
    </div>
    
 <%@ include file="Menu.jsp" %>
 
    <div id="formWrap">
        <form action="${pageContext.request.contextPath}/memberLogin" method="post" onsubmit="return loginFormCheck(this)">
            <div class="formInput">
                <!-- <input type="text" onfocus="focusInput(this)" onblur="blurInput(this)" placeholder="ID"/> -->
             
               <span class="formIcon">
                   <i class="fa-solid fa-user"></i>
               </span>
               
                <input type="text" style="background-color:green" name="loginId" class="userInput" placeholder="ID"/>
           
            </div>
            <p class="checkMsg d-none" id="idMsg">enter the Id</p>
            
            <div class="formInput">
                <!-- <input type="text" onfocus="focusInput(this)" onblur="blurInput(this)" placeholder="PASSWORD"/> -->
               
                <span class="formIcon">
                    <i class="fa-solid fa-key"></i>
                </span>
                
                <input type="text" style="background-color:green" name="loginPw" class="userInput" placeholder="PASSWORD"/>
           
            </div>
            
            <p class="checkMsg d-none" id="pwMsg">enter the P.W</p>
            
       <div class="formSubmit">
                <input type="submit" value="login"/>
            </div>
        </form>
    </div>

   
   
</body>

<script src="${pageContext.request.contextPath}/JS/Main.js"></script>
<script type="text/javascript">

let inputEls = document.querySelectorAll(".formInput>input");
console.log(inputEls.length);

for(let obj of inputEls){
	obj.addEventListener("focus", (e)=>{
		obj.parentElement.classList.add("formInputOn");
	});
	
	obj.addEventListener("blur", (e)=>{
		obj.parentElement.classList.remove("formInputOn");
	});
}

function loginFormCheck(formObj){
	// 아이디 주소 모두 입력 -> submit 실행
	// 하나라도 미입력 -> submit 중지
	// 미입력된 항목으로 포커스
	let checkForm = true;
	if(formObj.loginId.value == ""){
		document.getElementById("idMsg").classList.remove("d-none");
		checkForm=false;
	}
	else{
		document.getElementById("idMsg").classList.addmove("d-none");
	}
	
	if(formObj.loginPw.value == ""){
		document.getElementById("pwMsg").classList.remove("d-none");
		checkForm=false;
	}
	else{
		document.getElementById("pwMsg").classList.addmove("d-none");
	}
	return checkForm;
}
</script>
</html>