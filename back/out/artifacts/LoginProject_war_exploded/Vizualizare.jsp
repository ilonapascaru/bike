<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Ciobanu Alexandru
  Date: 6/19/2018
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Artifacty</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/functions.js"></script>
</head>
<body>

<div class="topnav" id="myTopnav">
    <a href="homeLI.jsp" >Home</a>
    <a href="colectii.jsp">Colectii</a>
    <a href="import.jsp"class="active">Import</a>
    <a href="statistici.jsp">Statistici</a>
    <a href="contact.jsp">Contact</a>
    <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>



    <a class="topbar" href="login.html">Log in</a>
    <a class="topbar" href="register.html">Register</a>

</div>

<br><br>


<%
    Connection dbc = null;
    Class.forName("com.mysql.jdbc.Driver");
    dbc = DriverManager.getConnection("jdbc:mysql://localhost:3306/artifacty", "root", "root");

    try{

        String denumire=(String)request.getAttribute("denumire");

        if(denumire!=null){

            PreparedStatement statement = null;
            ResultSet rs;
            String sql = " select a.denumire, a.valoare, a.descriere, a.perioada_datata,a.link, b.denumire,c.denumire,d.denumire from `artifacty`.`artefacte` a \n" +
                    "         join `artifacty`.`localizari` b on a.id_loc=b.id_loc\n" +
                    "          join `artifacty`.`roluri` c on a.id_rol=c.id_rol\n" +
                    "           join `artifacty`.`tip` d on a.id_tip=d.id_tip\n" +
                    "where a.denumire=?";
            statement= dbc.prepareStatement(sql);
            statement.setString(1, denumire);
            rs=statement.executeQuery();

            rs.next();

            %>

<div class="responsive">
    <div class="gallery">

            <img src="<%=rs.getString("a.link")%>" alt="Casca" width="300" height="200">

        <div class="desc">
            <p ><strong>Denumire: </strong></p><p ><%=rs.getString("a.denumire")%></p>
            <p ><strong>Localizare: </strong></p><p ><%=rs.getString("b.denumire")%></p>
            <p ><strong>Rol: </strong></p><p > <%=rs.getString("c.denumire")%></p>
            <p ><strong>Valoare: </strong></p><p > <%=rs.getString("a.valoare")%></p>
        </div>
        <button class="button1" onClick="ModifyTextForm()">Modify</button>
        <button class="button1" onClick="Save()">Save</button>
    </div>
</div>

<%

        }

    }catch (Exception e){
    e.printStackTrace();
}



%>

</body>
</html>
