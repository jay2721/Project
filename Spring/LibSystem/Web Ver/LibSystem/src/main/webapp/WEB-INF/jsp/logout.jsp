<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<body>
<%

        out.println("<script>alert('로그아웃 되었습니다. '); </script>");
		session.invalidate();
        out.println("<script>location.href='/LibSystem/login'</script>");

%>
</body>
</html>