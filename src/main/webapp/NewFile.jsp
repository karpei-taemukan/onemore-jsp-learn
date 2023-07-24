<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
Connection con  = null;

String url="jdbc:oracle:thin:@//localhost:1521/xe"; 
String userid="jsp_board"; 
String userpw="1111";

try {
		Class.forName("oracle.jdbc.OracleDriver");
		 con = DriverManager.getConnection(url, userid, userpw);
}
catch (ClassNotFoundException e) {
		e.printStackTrace();
}
catch (SQLException e) {
	e.printStackTrace();
}


%>

</body>
</html>