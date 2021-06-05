<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="libsys.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>

<style>
@font-face {
    font-family: 'SLEIGothicTTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SLEIGothicTTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.booklist {
	width: 1050px;
	padding: 1%;
	margin: auto;
}

.form-box {
	position: relative;
	background: #FFFFFF;
	height: 800px;
	padding: 20px;
	text-align: center;
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
	border-radius: 5px 5px;
	z-index: 1;
}

img {
	width: 45px;
}

h1 {
	font-family: "SLEIGothicTTF", sans-serif;
	color: #4E4E4E;
	text-align: center;
	font-size: 45px;
	margin-top: 0;
}

.form-box input {
	font-family: "SLEIGothicTTF", sans-serif;
	outline: 0;
	background: #F6F6F6;
	border: 0;
	padding: 5px;
	box-sizing: border-box;
	border-radius: 5px 5px;
	font-size: 15px;
}

select {
	font-family: "SLEIGothicTTF", sans-serif;
	width: 70px;
	padding: .4em .5em;
	border-radius: 5px 5px;
	box-shadow: 0 0 2px 0 rgba(0, 0, 0, 0.2), 0 2px 2px 0
	border: 1px solid F3F3F3;
}

input[type=text] {
	font-family: "SLEIGothicTTF", sans-serif;
	box-shadow: 0 0 2px 0 rgba(0, 0, 0, 0.2), 0 2px 2px 0
		rgba(0, 0, 0, 0.1);
}
input[type=submit] {
	font-family: "SLEIGothicTTF", sans-serif;
	background: #FFBA5E;
	border: 0;
	padding: 5px;
	color: #FFFFFF;
	font-size: 15px;
	cursor: pointer;
	border-radius: 5px 5px;
	box-shadow: 2px 2px #FFAE43;
	transition: all 0.9s, color 0.3s;
}
input[type=submit]:hover {
	background: white;
	color: #ffab42;
}

table {
	border-collapse: collapse;
}
tr {
	border-bottom: 1px solid #E1E1E1;
}
tr:hover
{
    background-color: #EBEBEB;
}
th, td {
	padding: 7px;
}

body {
	background: #EBEBEB;
	font-family: "SLEIGothicTTF", sans-serif;
}
</style>

</head>
<%
    session=request.getSession();
    session=request.getSession(true);
    Member member = (Member)session.getAttribute("member");
%>
<%
    String checking = (String) request.getAttribute("check");
 	if(checking.equals("no")){
        out.println("<script>alert('대여한 도서가 없습니다.'); </script>");
        out.println("<script>location.href='/LibSystem/main'</script>");
    }

%>
<body>
	<div align="right">
	<p>환영합니다, <% out.print(member.getName());%>님</p>
		<table>
			<tr>
				<td>
					<form method='POST' action="main">
						<input type="submit" value="메인화면">
						<input Type="hidden" name="id" value="<%out.print(member.getId());%>">
					</form>
				</td>
				<td>
					<form method='POST' action="userupdate">
						<input type="submit" value="정보수정">
					</form>
				</td>
				<td>
					<form method='POST' action="logout">
						<input type="submit" value="로그아웃">
					</form>
				</td>
			</tr>
		</table>
	</div>
		<center>
			<img src="img\mainbook.png" />
		</center>
		<h1>Library System</h1>

		<div class="booklist">
		<div class="form-box">
				<div>
					<form method='POST' action="search">
						<select name="searchfilter">
							<option value='title'>제목
							<option value='author'>작가
						</select>
						<input type="text" name="searchcontent">
						<input Type="hidden" name="id" value="<%out.print(member.getId());%>">
						<input type=submit value="검색">
					</form>
				</div>
				<br>
		

		<table width="1000" border="0" cellpadding="3" align="center"
			style="text-align: center">
			<tr bgcolor=#FFBA5E>

				<td class="num" height="10" width="80">번호</td>
				<td class="title" height="10">제목</td>
				<td class="author" height="10" width="200">작가</td>
				<td class="publisher" height="10" width="150">출판사</td>
				<td class="rent" height="10" width="150">대여가능여부</td>

			</tr>


			<c:forEach var="book" items="${books}" varStatus="status">
				
				<tr>
					<td width="50">${status.index+1}</td>
					<td width="500">${book.title}</td>
					<td width="100">${book.author}</td>
					<td width="200">${book.publisher}</td>
					<td width="200">
						<form method='POST' action="rtndone">
							<input Type="hidden" name="id"
								value="<%out.print(member.getId());%>"> <input
								type="hidden" name="title" value="${book.title}"> <input
								type="submit" value="반납">
						</form>
					</td>
				</tr>
				
			</c:forEach>
		</table>
	</div>
	</div>
</body>
<br>

</html>
