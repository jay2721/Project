
 
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.sql.*" %>
<html>
<head>
        <meta charset = 'utf-8'>
<head>
<center><font color=#FFAAAA>
	<h2>게시판</h2> </font>
</center>
</head>
</head>
<style>
        

</style>
<body>

<div>
	<table width="1000" border="1" cellpadding="3" align="center" style="text-align:center" >
				<tr bgcolor=#FFD2D2>
				
				<td class="num" height="10" width="80" >번호</td>
					<td class="title" height="10">제목</td>
					<td class="id" height="10" width="150">작성자 id</td>
					<td class="ip" height="10" width="150">작성자 ip</td>
					<td class="date" height="10" width="150">작성일</td>
					<td class="view" height="10" width="100">조회수</td>

				</tr>



<%
request.setCharacterEncoding("utf-8");
int num,view,postnum;
String title, content,id, pw, userip,date;
String filter=request.getParameter("searchfilter");
String search=request.getParameter("searchcontent");
String ss="%"+search+"%";
String URL = "jdbc:mysql://localhost/jeonayg1067";
String USER = "jeonayg1067", PASS="dkdud2721*";
Connection conn = null;
PreparedStatement pstmt = null;
//Statement stmt = null;
ResultSet rs = null;
try {
  Class.forName("com.mysql.jdbc.Driver");
  conn = DriverManager.getConnection(URL, USER, PASS);

if(filter.equals("title")){
  String sql = "SELECT * FROM board where title like ? ORDER BY num DESC";
  pstmt = conn.prepareStatement(sql);
 pstmt.setString(1,ss);
  rs = pstmt.executeQuery();

}

else if(filter.equals("writer")){
  String sql = "SELECT * FROM board where id like ? ORDER BY num DESC";
  pstmt = conn.prepareStatement(sql);
 pstmt.setString(1,ss);
  rs = pstmt.executeQuery();


}


  while( rs.next() ) {
 num = rs.getInt("num");   
		id = rs.getString("id");
      pw = rs.getString("pw");
      title = rs.getString("title");
      content = rs.getString("content");
   
 userip = rs.getString("userip");
 date = rs.getString("date");
view=rs.getInt("view");
postnum=rs.getRow();

%>


	</div>

    <td width="50"><%=postnum%></td>
<td width = "500" >
<a href = "jread.jsp?num=<%=num%>"><%=title%></td>
<td width="100"><%=id%></td> 
	<td width="200"><%=userip%></td>
     <td width="200"><%=date%></td>
 <td width="50"><%=view%></td>

</tr>
  <% 

  }
}catch(SQLException e) {
  out.println(e.getMessage());
}
finally {
  if(rs != null)try{rs.close();}catch(SQLException ex){}
  if(pstmt != null)try{pstmt.close();}catch(SQLException ex){}
  if(conn != null)try{conn.close();}catch(SQLException ex){}
}
%>
</table>
</body>
<br>
<center>
<div>
		<form method='POST' action="jsearch.jsp" >
			<select name="searchfilter">
			<option value='title'>제목
			<option value='writer'>작성자
			</select>
			<input type="text" name="searchcontent">
			<input type=submit value="검색" >

			<input type="button" value="글쓰기"  onclick="location.href='jwrite.jsp'">
<input type="button" value="목록" onclick="location.href='jlist.jsp'">
		</form>

	</div>
</center>
</html>
