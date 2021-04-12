
<html>
<head>
학번: 8301067 이름: 전아영
</head>
<body>
<%@ page info = "assg8.jsp" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*,java.text.*"%>
<%
Date nowTime = new Date();
	SimpleDateFormat ss = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss",Locale.KOREA);

%>
<br>현재 시각은 <%= ss.format(nowTime) %> 입니다.<br><br>
<form name=form1 method=post action="assg8.jsp">
당신의 이름은 ?       <input type=text name=myname ><br>
가위바위보를 선택하세요.<br>
<select id= "game" name="game">
<option value="가위">가위</option>
<option value="바위">바위</option>
<option value="보">보</option>
</select>
<input type=submit value=확인 ><br>
</form>

<%

int crand=(int)(Math.random()*2);
 request.setCharacterEncoding("utf-8");

String username=request.getParameter("myname");
String user=request.getParameter("game");
String computer[]={"가위","바위","보"};

 if(!((null==username)||"".equals(username))) {


out.println(username+"씨는");
out.println("<font color=orange>" +user +"</font>"+"를 냈습니다."+"<br>");


out.println("컴퓨터는 ");
out.println("<font color=orange>"+computer[crand]+"</font>"+"를 냈습니다."+"<br>");


if("가위".equals(user)){
if(crand==0)
out.println("<font color=green>"+"비겼습니다."+"</font>");
else if(crand==1)
out.println("<font color=red>"+"당신이 졌습니다."+"</font>");
else if(crand==2)
out.println("<font color=blue>"+"당신이 이겼습니다."+"</font>");
}
else if("바위".equals(user)){
if(crand==0)
out.println("<font color=blue>"+"당신이 이겼습니다."+"</font>");
else if(crand==1)
out.println("<font color=green>"+"비겼습니다."+"</font>");
else if(crand==2)
out.println("<font color=red>"+"당신이 졌습니다."+"</font>");
}
else if("보".equals(user)){
if(crand==0)
out.println("<font color=red>"+"당신이 졌습니다."+"</font>");
else if(crand==1)
out.println("<font color=blue>"+"당신이 이겼습니다."+"</font>");
else if(crand==2)
out.println("<font color=green>"+"비겼습니다."+"</font>");
}

}
else if(username==null||(username.equals("null"))||(username.equals(""))||"".equals(username))
out.println("이름을 입력하세요");
%>
</body></html>