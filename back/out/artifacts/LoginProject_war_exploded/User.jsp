<!DOCTYPE html>
<%@page language="java" import="java.util.*" %>
<%@ page import="dto.Programari" %>
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
      <a href="User.jsp">Programari</a>
      <a href="Contact.jsp">Contact</a>
      <a href="About.html">About</a>
      <a href="index.html">Logout</a>

    </div>
    <div class="userMain">
      <h2>Bine ai venit la "Atelierul de biciclete!"</h2>
      <span class="adauga-buton">
        <a href="AdaugareProgramare.jsp" class="adaugare">Adaugare programare noua</a>
      </span>
      <table>
          <tr>
            <th>Id rezervare</th>
            <th>Problema</th>
            <th>Data</th>
            <th>Ora</th>
            <th>Pret estimativ</th>
            <th>Status</th>
          </tr>
          <%Iterator itr;%>
          <% List<Programari> p= (ArrayList<Programari>)request.getAttribute("programari");
              for (Programari pr:p)
              {
          %>
          <tr>
              <td><%=pr.getIdProgramare()%></td>
              <td><%=pr.getDescriere()%></td>
              <td><%=pr.getData()%></td>
              <td><%=pr.getOra()%></td>
              <td><%=pr.getPret()%></td>
              <td><%=pr.getTextRaspuns()%></td>
          </tr>
          <%}%>
        </table>

      </div>
</div>
  </body>
</html>