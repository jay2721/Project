<%@ page info = "jlist.jsp" contentType="text/html; charset=utf-8" %>
<%@ page import="java.text.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.sqlx.*" %>
<%request.setCharacterEncoding("utf-8");%>
<head>
<font color=#FFAAAA><center>
	
	<h2>글 수정</h2>
</center></font>
</head>
<script>

function output(form,no){

form.submit();
};
</script>

<%
request.setCharacterEncoding("utf-8");
int num,view,postnum;
String atitle, acontent,aid, apw, userip,date;
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
pstmt.setString(1,listnum);
  rs = pstmt.executeQuery();
rs.next();

num = rs.getInt("num");
aid = rs.getString("id");
apw = rs.getString("pw");
atitle = rs.getString("title");
acontent = rs.getString("content");
userip = rs.getString("userip");
date = rs.getString("date");
view=rs.getInt("view");





%>	

<body>

	<div>

<form method="POST" action="jrewritecheck.jsp">
<table width="850" height="500" border="1" cellpadding="3" align="center" >
            <tr>
                <td bgcolor=#FFAAAA>제목 : </td>
                <td><input type="text" name="title" maxlength="50"value="<%=atitle%>"></td></tr>
 
            <tr><td bgcolor=#FFAAAA>내용 : </td>
            <td><textarea rows="10" cols="100" name="content"><%=acontent%></textarea></td></tr>
	
                <td bgcolor=#FFAAAA>ID :</td>
                <td><input type="id" name="myid" maxlength="10"></td></tr>
         <tr>
                <td bgcolor=#FFAAAA>PW : </td>
                <td><input type="password" name="mypw" maxlength="10"></td></tr>
<input type="hidden" name="postnum" value="<%=ll%>">
            <tr bgcolor=#FFAAAA><td colspan="2" align="center">
					
                <input type=submit value="작성" onclick="output(this.form)">
                <input type="reset" value="취소"  onclick="location.href='jlist.jsp'"></td> </tr>
</table>
		
	</form>
	</div>

</body>

<%
} catch(Exception e){
    e.printStackTrace();
out.println(e.getMessage());

}
finally {
  if(pstmt != null)try{pstmt.close();}catch(SQLException sqle){}
  if(conn != null)try{conn.close();}catch(SQLException sqle){}
}

%>	
