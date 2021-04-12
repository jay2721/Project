<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
    import="java.sql.*" %>
<html>
<%
request.setCharacterEncoding("utf-8");

String num=request.getParameter("postnum");
int ll=Integer.parseInt(num);
String rcon=request.getParameter("rcontent");
String rrpw=request.getParameter("rpw");
String rrid=request.getParameter("rid");

String URL = "jdbc:mysql://localhost/jeonayg1067";
String USER = "jeonayg1067", PASS="dkdud2721*";
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try {

  Class.forName("com.mysql.jdbc.Driver");
  conn = DriverManager.getConnection(URL, USER, PASS);   
  String sql="insert into reply(postnum,rid,rpw,rcontent) values(?,?,?,?)";
  pstmt = conn.prepareStatement(sql);

pstmt.setInt(1,ll);
pstmt.setString(2,rrid);
pstmt.setString(3,rrpw);
pstmt.setString(4,rcon);
pstmt.executeUpdate();

  out.println("<script>alert('댓글이 등록되었습니다.');history.back();</script>"); 

} catch(Exception e){
    e.printStackTrace();
 out.println(e.getMessage());


}
finally {
  if(pstmt != null)try{pstmt.close();}catch(SQLException sqle){}
  if(conn != null)try{conn.close();}catch(SQLException sqle){}
}




%>		
</html>
