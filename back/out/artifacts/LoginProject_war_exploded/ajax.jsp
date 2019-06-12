<%@ page import="java.sql.Connection" %>
<%@ page import="static function.DatabaseFunction.getConnection" %>
<%@ page import="static function.DatabaseFunction.verifyEmail" %>
<%@ page import="static function.DatabaseFunction.verifyUsername" %>
<%@ page import="static function.DatabaseFunction.*" %><%--
  Created by IntelliJ IDEA.
  User: Ciobanu Alexandru
  Date: 6/12/2019
  Time: 12:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Bikes</title>
</head>
<body>
<%
    String username=request.getParameter("username");
    String name=request.getParameter("username");
    String email=request.getParameter("email");
    String password=request.getParameter("password");
    String password2=request.getParameter("password2");
    String tel=request.getParameter("tel");
    long phone = Long.parseLong(tel);
    Connection conn1 = null;
    String status = "Fail";


    try {
        conn1 = getConnection("bike");

        if(!password.equals(password2)){
            request.setAttribute("errorPass", "Passwords do not match");
            //(response).sendRedirect("/register.jsp");
        }
        else if(verifyEmail(email, conn1)==0){
            request.setAttribute("errorEmail", "Email exists");
            //(response).sendRedirect("/register.jsp");
        }
        else if(verifyUsername(username,conn1)==0)
            request.setAttribute("errorUser", "Username exists");
        else {registerUser(username,password,name, email, Integer.parseInt(tel),conn1);
            status="Success";}
    }catch (Exception e) {
        e.printStackTrace();
    }
%>
<h3>REGISTRATION ENDED WITH: <%=status%></h3>
<h3>USER NAME IS : <%=username %></h3>
<h3>EMAIL ID IS : <%=email %></h3>
<h3>PHONE NUMBER IS : <%=phone %></h3>
</body>
</html>

