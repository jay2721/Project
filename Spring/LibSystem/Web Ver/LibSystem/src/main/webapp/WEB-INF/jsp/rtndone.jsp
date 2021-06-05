<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<body>
<%
    String checking = (String) request.getAttribute("check");
    if(checking.equals("ok")){
        out.println("<script>alert('반납에 성공했습니다.'); </script>");
        out.println("<script>location.href='/LibSystem/main'</script>");
    }
    else if(checking.equals("no")){
        out.println("<script>alert('반납에 실패했습니다.'); </script>");
        out.println("<script>location.href='/LibSystem/main'</script>");
    }
%>
</body>
</html>