<%@ page import="dto.Mesaj" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
      <a href="http://localhost:8081/comenzi">Comenzi</a>
      <a href="index.html">Logout</a>

    </div>
        <% List<Mesaj> p= (ArrayList<Mesaj>)request.getAttribute("mesaj");%>
    <div class="adminMain">
      <br><br><br><h3>Aveti <%=p.size()%> mesaje noi</h3><hr><br><br><br>
       

      <table>
        <tr>
          <th>Id mesaj</th>
          <th>Nume</th>
          <th>Email</th>
          <th>Text</th>
          <th>Telefon</th>
        </tr>
        <%
          for (Mesaj ms:p)
          {
        %>
        <tr>
          <td><%=ms.getIdMesaj()%></td>
          <td><%=ms.getName()%></td>
          <td><%=ms.getEmial()%></td>
          <td><%=ms.getText()%></td>
          <td><%=ms.getTelefon()%></td>
            <td>
                <form>
                  <button type="submit" value="Submit"><a href="RaspundeMesaj.jsp?email=<%=ms.getEmial()%>">Raspunde</a></button>
                </form>
            </td>
        </tr>
        <%}%>
      </table>
    </div>
</div>
  </body>
</html>