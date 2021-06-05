<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>

<style>
@font-face {
    font-family: 'SLEIGothicTTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SLEIGothicTTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.login {
	width: 320px;
	padding: 7%;
	margin: auto;
	margin-right: 180px;
}

.form-box {
	position: relative;
	background: #FFFFFF;
	height: 420px;
	padding: 40px;
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

.form-box .register {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form-box .register a {
	color: #ffab42;
	text-decoration: none;
}

body {
	background: linear-gradient(
            to right,
            rgba(20, 20, 20, 0) 10%,
            rgba(20, 20, 20, 0.25) 25%,
            rgba(20, 20, 20, 0.5) 50%,
            rgba(20, 20, 20, 0.75) 75%,
            rgba(20, 20, 20, 1) 100%
          ), url(https://i.pinimg.com/originals/70/40/e3/7040e33ff154e8d690aff0d1e0654990.png);
        background-size: cover;
	font-family: "SLEIGothicTTF", sans-serif;
}
</style>
</head>

<script>
function checkValue(){
            if(!document.loginform.id.value){
                alert("아이디를 입력하세요.");
                return false;
            }
            
            if(!document.loginform.pwd.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
     
        }
</script>
<body>
	<div class="login">
		<div class="form-box">
			<div class="logo">
				<img src="img\book.png" />
				<h1>
					Library System
				</h1>
			</div>
			<Form class="login-form" Action="logindone" Method="post" name="loginform"
				onsubmit="return checkValue()">
				<Input Type="Text" Name="id" placeholder="아이디">
				<Input Type="PassWord" Name="pwd" placeholder="비밀번호">
				<button>로그인</button>
				<p class="register">
					회원이 아니신가요? <a href="register">회원가입</a>
				</p>
			</Form>
		</div>
	</div>
</body>
</html>