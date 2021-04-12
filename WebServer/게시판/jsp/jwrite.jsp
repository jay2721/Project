<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.sql.*" %>
<%@ page import="java.util.*,java.text.*"%>
<%@page import="java.util.Date" %>

<head>
<font color=#FFAAAA><center>
	
	<h2>글 작성</h2>
</center></font>
		

</head>

<body>

	<div>


<table width="850" height="500" border="1" cellpadding="3" align="center" ><form method=post action="jwrite.jsp">
            <tr>
                <td bgcolor=#FFAAAA>제목 : </td>
                <td><input type="text" name=title maxlength="50" placeholder="제목을 입력하세요." /></td></tr>
 
            <tr><td bgcolor=#FFAAAA>내용 : </td>
            <td><textarea rows="10" cols="100" name=content placeholder="내용을 입력하세요."></textarea></td></tr>
	
                <td bgcolor=#FFAAAA>ID :</td>
                <td><input type="text" name=myid maxlength="10" placeholder="아이디를 입력하세요."/></td></tr>
         <tr>
                <td bgcolor=#FFAAAA>PW : </td>
                <td><input type="password" name=mypw maxlength="10" placeholder="비밀번호를 입력하세요."/></td></tr>

            <tr bgcolor=#FFAAAA><td colspan="2" align="center">
	
                <input type=submit value="작성">
                <input type="reset" value="취소"  onclick="location.href='jlist.jsp'"></td> </tr></form>
</table>
		
	
	</div>

<%
request.setCharacterEncoding("utf-8");

String wtitle=request.getParameter("title");
String wcontent=request.getParameter("content");
String wid=request.getParameter("myid");
String wpw=request.getParameter("mypw");
int wview=0;
Date nowtime= new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
String wdate = sf.format(nowtime);
String wip = request.getRemoteAddr();

String URL = "jdbc:mysql://localhost/jeonayg1067";
String USER = "jeonayg1067", PASS="dkdud2721*";
Connection conn = null;
PreparedStatement pstmt = null;

try {

  Class.forName("com.mysql.jdbc.Driver");
  conn = DriverManager.getConnection(URL, USER, PASS);   
  String sql="INSERT board (id,pw,title,content,date,view,userip) values (?,?,?,?,?,?,?)";
  pstmt = conn.prepareStatement(sql);

pstmt.setString(1,wid);
pstmt.setString(2,wpw);
pstmt.setString(3,wtitle);
pstmt.setString(4,wcontent);
pstmt.setString(5,wdate);
pstmt.setInt(6,wview);
pstmt.setString(7,wip);
pstmt.executeUpdate();

  out.println("<script>alert('작성한 글이 등록되었습니다.');location.href='jlist.jsp';</script>"); 

} catch(Exception e){
    e.printStackTrace();
 

}
finally {
  if(pstmt != null)try{pstmt.close();}catch(SQLException sqle){}
  if(conn != null)try{conn.close();}catch(SQLException sqle){}
}




%>	
</body>

