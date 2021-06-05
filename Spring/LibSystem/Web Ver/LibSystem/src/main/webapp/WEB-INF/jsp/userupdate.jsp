<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="libsys.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>회원정보 수정</title>

<style>
@font-face {
    font-family: 'SLEIGothicTTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SLEIGothicTTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.update {
	width: 600px;
	padding: 1%;
	margin: auto;
}

.form-box {
	position: relative;
	background: #FFFFFF;
	height: 660px;
	padding: 5px;
	text-align: center;
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
	border-radius: 5px 5px;
	z-index: 1;
}

.form-box img {
	width: 45px;
}

.form-box h1 {
	font-family: "SLEIGothicTTF", sans-serif;
	padding: 10px;
}

.form-box input {
	font-family: "SLEIGothicTTF", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	border-radius: 5px 5px;
	font-size: 15px;
}

.form-box button {
	font-family: "SLEIGothicTTF", sans-serif;
	background: #919192;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 15px;
	cursor: pointer;
	border-radius: 5px 5px;
}

.form-box button:hover, .form button:active, .form button:focus {
	background: #ffab42;
}

.form-box .cancel {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form-box .cancel a {
	color: #ffab42;
	text-decoration: none;
}

body {
	background: url(https://i.pinimg.com/originals/70/40/e3/7040e33ff154e8d690aff0d1e0654990.png);
        background-size: cover;
	font-family: "SLEIGothicTTF", sans-serif;
}
</style>

</head>
<%
    session=request.getSession();
    session=request.getSession(true);
    Member member = (Member)session.getAttribute("member");
%>

<script type="text/javascript">
    
        function checkValue(){
            if(!document.userInfo.name.value){
                alert("이름을 입력하세요.");
                return false;
            }
            
            if(!document.userInfo.pwd.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            if(!document.userInfo.pwdchk.value){
                alert("비밀번호 확인을 입력하세요.");
                return false;
            }
            if(!document.userInfo.email.value){
                alert("이메일을 입력하세요.");
                return false;
            }
            
            if(!document.userInfo.addr.value){
                alert("주소를 입력하세요.");
                return false;
            }
            
            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(document.userInfo.pwd.value != document.userInfo.pwdchk.value ){
                alert("비밀번호 확인이 다릅니다.");
                return false;
            }

        }
        
        function back(){
        	location.href="/LibSystem/main";
        }
 
    </script>


<body>
	<div class="update">
		<div class="form-box">
	<div class="logo">
		<h1>회원정보</h1>
	</div>
	<hr>
	<form class="user-info" action="userupdatedone" method="post" name="userInfo"
		onsubmit="return checkValue()">
		<table width="450" height="150" align="center">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" maxlength="50"
					value="<%out.print(member.getName());%>" /></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><% out.print(member.getId());%>
				<input Type ="hidden" name ="id" value="<%out.print(member.getId());%>">
				</td>
					 
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" maxlength="50"
					value="<%out.print(member.getPwd());%>" /></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pwdchk" maxlength="50" /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" maxlength="50"
					value="<%out.print(member.getEmail());%>" /></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="addr" maxlength="100"
					value="<%out.print(member.getAddr());%>" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button>수정하기</button>
				<p class="cancel">
					<a href="main">취소하기</a>
				</p>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</body>
<br>

</html>
