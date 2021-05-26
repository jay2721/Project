<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.sql.*" %>
<head>

<font color=#FFAAAA><center>
	
<h2>게시글</h2>
</center></font>
</head>




<%
request.setCharacterEncoding("utf-8");
int num,view,postnum;
String title, content,id, pw, userip,date;
String listnum=request.getParameter("num");

int ll=Integer.parseInt(listnum);


String URL = "jdbc:mysql://localhost/jeonayg1067";
String USER = "jeonayg1067", PASS="dkdud2721*";
Connection conn = null;
PreparedStatement pstmt = null;
//Statement stmt = null;
ResultSet rs = null;
try {
  Class.forName("com.mysql.jdbc.Driver");
  conn = DriverManager.getConnection(URL, USER, PASS);   
  String sql;
  sql = "SELECT * FROM board where num=?";
  pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,ll);
  rs = pstmt.executeQuery();
rs.next();

num = rs.getInt("num");
id = rs.getString("id");
title = rs.getString("title");
content = rs.getString("content");
userip = rs.getString("userip");
date = rs.getString("date");
view=rs.getInt("view");
postnum=rs.getRow();
rs.last();
view=view+1;

String sql1 = "update board set view=? where num=?";
  pstmt = conn.prepareStatement(sql1);
pstmt.setInt(1,view);
pstmt.setInt(2,ll);
 int result=pstmt.executeUpdate();



%><script>
function output(form){
form.submit();
};

function postdelete(){
var pd=prompt("비밀번호를 입력하세요.",'');
if(pd==null)
location.href='jread.jsp?num=<%=ll%>';
else {
location.href='jdelete.jsp?num=<%=ll%>&password='+pd;}
};

function rd(rno){
var rdp=prompt("비밀번호를 입력하세요.",'');
if(rdp==null)
location.href='jread.jsp?num=<%=ll%>';

else
location.href='jredel.jsp?rpw='+rdp+'&num=<%=ll%>&rno='+rno;

};

function rrr(rno,rcon){
var rrcon=prompt("댓글 수정 폼",rcon);
var rrpw=prompt("비밀번호를 입력하세요.",'');

if(rrcon==null)
location.href='jread.jsp?num=<%=ll%>';
else if(rrpw==null)
location.href='jread.jsp?num=<%=ll%>';
else
location.href='jrrw.jsp?rrcon='+rrcon+'&rpw='+rrpw+'&rno='+rno+'&num=<%=ll%>';
};

</script>
<body>

	<div>
		
<table width="850" height="400" border="1" cellpadding="3" align="center">
            <tr height="80">
                <td ><h4>제목 : <%=title%></h4> <div align="right">조회수:  <%=view%>
              / 글쓴이id : <%=id%> / 글쓴이ip : <%=userip%> /작성일 : <%=date%></div></td> </tr>
      
            <tr>
            <td> <%=content%>
			</td></tr>
     
	<form method='post'>
            <tr height="50"><td colspan="2" align="center">
                <input type="button" value="글삭제" onclick="postdelete()">
                			<input type="button" value="글수정" onclick="location.href='jrewrite.jsp?num=<%=num%>'"> <input type="button" value="글목록" onclick="location.href='jlist.jsp'"> </td> </tr>
		
</table>
		</form>
	
	</div>



<div style="margin-left:320; margin-right:320;">

<h3>댓글목록</h3><hr>
<% int no;
String rid,rpw,rcontent;

 sql = "SELECT * FROM reply where postnum=?";
  pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,ll);
  rs = pstmt.executeQuery();

 while( rs.next() ) {
no = rs.getInt("no");
rid = rs.getString("rid");
rpw=rs.getString("rpw");
rcontent = rs.getString("rcontent");


%>
			<div><b><%=rid%></b></div>
			<div><%=rcontent%></div>
			<div>
		<form method="post">
				<input type="button" value="댓글수정"  onclick="rrr(<%=no%>,'<%=rcontent%>')">
				<input type="button" value="댓글삭제" onclick="rd(<%=no%>)">
		</form>
<hr>
		</div>
		<% }
   
  
}catch(SQLException e) {
  out.println(e.getMessage());
}
finally {
  if(rs != null)try{rs.close();}catch(SQLException ex){}
  if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
  if(conn != null)try{conn.close();}catch(SQLException ex){}
}
%>	
	<div>
		<form method="post" action="jreply.jsp">

			<input type="text" name="rid" id="rid" class="rid" size="15" placeholder="아이디">
			<input type="password" name="rpw" id="rpw" class="rpw" size="15" placeholder="비밀번호">
<input type="hidden" name="postnum" value="<%=ll%>">
			<div style="margin-top:10px; ">
				<textarea name="rcontent" class="rcontent" id="rcontent" ></textarea>
				<input type="button" value="댓글" onclick="output(this.form)">
			</div>
		</form>
	</div> 
</div>



</body>
