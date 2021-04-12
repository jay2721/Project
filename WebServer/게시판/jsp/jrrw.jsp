<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.sql.*" %>
<html>
<%
request.setCharacterEncoding("utf-8");
int rnum;
String pw;
int num=Integer.parseInt(request.getParameter("num"));
int rrno=Integer.parseInt(request.getParameter("rno"));
String rrcon=request.getParameter("rrcon");
String rpw=request.getParameter("rpw");

String URL = "jdbc:mysql://localhost/jeonayg1067";
String USER = "jeonayg1067", PASS="dkdud2721*";
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try {

  Class.forName("com.mysql.jdbc.Driver");
  conn = DriverManager.getConnection(URL, USER, PASS);   
  String sql;
  sql = "SELECT * FROM reply where no=?";
  pstmt = conn.prepareStatement(sql);
pstmt.setInt(1,rrno);
  rs = pstmt.executeQuery();
rs.next();

rnum = rs.getInt("no");
pw = rs.getString("rpw");

rs.last();


if(rpw.equals(pw)){
String sql1 = "update reply set rcontent=? where no=?";
  pstmt = conn.prepareStatement(sql1);
pstmt.setString(1,rrcon);
pstmt.setInt(2,rrno);
 int result=pstmt.executeUpdate();


out.println("<script>alert('댓글 수정되었습니다.');history.back();</script>");
}
else{
out.println("<script>alert('댓글 수정 실패했습니다.');history.back();</script>");
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
