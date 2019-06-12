<%@ page import="java.util.Iterator" %>
<%@ page import="dto.Comenzi" %>
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
      <a href="ProgramariAdmin.html">Programari</a>
      <a href="Stocuri.jsp">Stocuri</a>
      <a href="Comenzi.jsp">Comenzi</a>
      <a href="index.html">Logout</a>

    </div>

    <div class="comenzi">
      <h2>Comenzi</h2>
       <div class="cautare">
        <table>
          <tr>
            <th>Piesa</th>
            <th>Model</th>
            <th>An</th>
            <th>Pret total</th>
            <th>Bucati</th>
            <th>Data livrare</th>
            <th>Furnizor</th>
          </tr>
          <%Iterator itr;%>
          <% List<Comenzi> s= (ArrayList<Comenzi>)request.getAttribute("comenzi");
          %>
          <%
            for (Comenzi st:s)
            {
          %>
          <tr>
            <td><%=st.getNume()%></td>
            <td><%=st.getModel()%></td>
            <td><%=st.getAn()%></td>
            <td><%=st.getPret()%></td>
            <td><%=st.getBucati()%></td>
            <td><%=st.getDataLivrare()%></td>
            <td><%=st.getFurnizor()%></td>


          </tr>
          <%}%>
        </table>
      </div>
      <input type="file" class="buton-import"/>
        <form  method="POST" action="/exportComenzi">
            <select name="export">
                <option value="CSV">CSV</option>
                <option value="PDF">PDF</option>
                <option value="JSON">JSON</option>
            </select>
            <input type="submit" class="buton-export" value="Export"/>
        </form>
    </div>
</div>
  </body>
</html>