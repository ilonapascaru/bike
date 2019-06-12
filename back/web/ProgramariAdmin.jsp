<%@ page import="java.util.Iterator" %>
<%@ page import="dto.Programari" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
      <a href="ProgramariAdmin.jsp">Programari</a>
      <a href="Stocuri.jsp">Stocuri</a>
      <a href="Comenzi.html">Comenzi</a>
      <a href="index.html">Logout</a>

    </div>
    
    <div class="adminMain">
    <h2>Lista programari:</h2>
    <div class="main-program">
        
      <div class="programari">
        <table>
          <tr>
            <th>Id rezervare</th>
            <th>Problema</th>
            <th>Ora</th>
            <th>Status</th>
          </tr>
          <%Iterator itr; %>
            <%List<Programari> p = (ArrayList<Programari>)request.getAttribute("programariAdmin");
                for (Programari pr:p){
            %>
          <tr>
              <td><%=pr.getIdProgramare()%></td>
            <<td><a href="http://localhost:8081/detaliiProgramare?id=<%=pr.getIdProgramare()%>"><%=pr.getDescriere()%></a></td>
              <td><%=pr.getData()%> <%=pr.getOra()%>:00</td>
              <td><%=pr.getTextRaspuns()%></td>
          </tr>
          <%}%>
        </table>
      </div>
    </div>
</div>
</div>
  </body>
</html>
