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
    <div id="header">
    Join Page
    </div>
   
<%@ include file="Menu.jsp" %>
 
    <div id="contents">
    <%-- 아이디(중복확인--ajax), 비밀번호, 이름, 생년월일, 주소(다음 우편 서비스) --%>
    
    
    
        <div class="formWrap">
        <form action="${pageContext.request.contextPath}/memberJoin" method="post" onsubmit="return joinFormCheck(this)">
        
        <div class="formInput">
        	<input onblur="idCheck(this)" type="text" name="joinId" placeholder="ID" style="background-color:green"/>
        </div>              
        <p class="checkMsg" id="idMsg">중복체크 확인 메세지</p>
       
         <div class="formInput">
        	<input type="text" name="joinPw" placeholder="PW" style="background-color:green"/>
        	</div>
        	
        	
         <div class="formInput">
        	<input type="text" name="joinName" placeholder="NAME" style="background-color:green"/>
        	</div>
        	
        	
        	 <div class="formInput">
        	<input type="date" name="joinBirth" placeholder="BIRTH" style="background-color:green"/>
        	</div>
        	
        	
        		<div class="formInput">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
        			<input type="text" name="postcode" id="sample6_postcode" placeholder="우편번호" style="background-color:green">
					<input type="text" name="address" id="sample6_address" placeholder="주소" style="background-color:green"><br>
					<input type="text" name="detailAddress" id="sample6_detailAddress" placeholder="상세주소" style="background-color:green">
					<input type="text" name="extraAddress" id="sample6_extraAddress" placeholder="참고항목" style="background-color:green">
        		</div>
        		<div class="formSubmit">
        		<input type="submit" value="JOIN"/>
        		</div>
        </form>
        </div>
        <hr style="height: 20px; background-color: black;">
      
        
    </div>
</body>
<script src="${pageContext.request.contextPath}/JS/Main.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
<!-- 아이디 중복 확인 -->

<script type="text/javascript">
function idCheck(idTag) {
console.log("입력한 아이디: "+ idTag.value);	
/*
 * ajax - 아이디 확인 요청 전송
 */

 $.ajax({
	 type: "get", // 서버 요청 방식
     url: "memberIdCheck",	// 요청 URL
	 data: {
		 "inputId":idTag.value              // 서버에 보낼 data (키(input태그 name과 같은 역할):값 형식)
		 },
	// dataType: "text",                    // 응답받은 데이터 타입		 
	 success: function(result){
		 if(result == "Y"){
			 console.log("사용가능 아이디");
			 $("#idMsg").css("color", "blue").text("사용가능한 아이디입니다.");
			 $(idTag).parent().removeClass("formInputErr");
		 }
		 else{
			 console.log("중복된 아이디");
			 $("#idMsg").css("color", "red").text("이미 가입된 아이디입니다.");
		 	 $(idTag).parent().addClass("formInputErr");
		 }
	 },               // 서버에 보낸 요청 전송이 성공
 	// error: function() {}    					// 서버에 보낸 요청 전송이 실패
 });
}
 
</script>

<!-- 아이디 중복확인 끝 -->


<!-- 회원가입 양식 JS 시작 -->
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



function joinFormCheck(formObj){
	// 아이디 주소 모두 입력 -> submit 실행
	// 하나라도 미입력 -> submit 중지
	// 미입력된 항목으로 포커스
	
	if(formObj.joinId.value == ""){
		alert("enter ID");
		formObj.joinId.focus();
		return false;
	}
	
	
}
</script>
</html>