<%--
  Created by IntelliJ IDEA.
  User: Ciobanu Alexandru
  Date: 6/11/2019
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ro">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biker</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/functions.js"></script>

</head>

<body>
<div class="bg">
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
    <div class="sidenav" id="mySidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="http://localhost:8081/programariAdmin">Programari</a>
        <a href="http://localhost:8081/stoc">Stocuri</a>
        <a href="Comenzi.html">Comenzi</a>
        <a href="index.html">Logout</a>

    </div>


    <form action="/processEmail.jsp?email=<%=request.getParameter("email")%>">
        <label>Raspuns:</label>
        <input type="text" name="raspuns" >
        <input type="submit" name="sendMessage">
    </form>
</div>




</body>
</html>
