<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.sql.*" %>
<html>
<%
request.setCharacterEncoding("utf-8");
String pw;
String id;
String num=request.getParameter("postnum");
int ll=Integer.parseInt(num);

String wtitle=request.getParameter("title");
String wcontent=request.getParameter("content");
String wid=request.getParameter("myid");
String wpw=request.getParameter("mypw");

String URL = "jdbc:mysql://localhost/jeonayg1067";
String USER = "jeonayg1067", PASS="dkdud2721*";
Connection conn = null;
PreparedStatement pstmt = null;
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
id=rs.getString("id");
pw = rs.getString("pw");

rs.last();


if(wpw.equals(pw)&&wid.equals(id)){
String sql1 = "update board set title=?, content=? where num=?";
  pstmt = conn.prepareStatement(sql1);

pstmt.setString(1,wtitle);
pstmt.setString(2,wcontent);
pstmt.setInt(3,ll);
 int result=pstmt.executeUpdate();


  out.println("<script>alert('글이 수정되었습니다.');location.href='jlist.jsp';</script>"); 
}
 else{ out.println("<script>alert('수정 실패했습니다.');history.back();'jlist.jsp';</script>");
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
</html>
