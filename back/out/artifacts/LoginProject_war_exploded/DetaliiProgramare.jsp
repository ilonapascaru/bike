<%@ page import="dto.DetaliiProgramari" %>
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
        <%DetaliiProgramari detaliiProgramari = (DetaliiProgramari)request.getAttribute("detalii");%>

    <div class="detalii-programare">
      <div class="programare-header">
        <div class="header-item" id="data"><%=detaliiProgramari.programari.getData()%> <%=detaliiProgramari.programari.getOra()%>:00</div>
      </div>
      <div class="programare-body">
        <div class="body-item" id="detalii-programare-body"><%=detaliiProgramari.programari.getDescriere()%></div>
        <div class="body-item" id="detalii-utilizator">
          <ul>
              <li>Nume: <td><%=detaliiProgramari.user.getName()%></td></li>
              <li> Telefon: <td><%=detaliiProgramari.user.getTelefon()%> </li>
              <li> Email: <td><%=detaliiProgramari.user.getEmail()%></li>
              <li> Model: <td><%=detaliiProgramari.programari.getModel()%></li>
            <%@ page import="java.sql.*"%>
            <%@ page import="java.io.*"%>
            <%@ page import="static function.DatabaseFunction.getConnection" %>
              <%
                Blob image = null;
                Connection con = null;
                byte[ ] imgData = null ;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try{
                  con = getConnection("bike");
                  stmt = con.prepareStatement("select atachament_name from programari where id_programare=?");
                  stmt.setInt(1,detaliiProgramari.programari.getIdProgramare());
                  if(rs.next()){
                      image = rs.getBlob(1);
                      imgData = image.getBytes(1,(int)image.length());
                  }
                  else{
                      out.println("No image");
                      return;
                  }
                  response.setContentType("image/jpg");
                  OutputStream o = response.getOutputStream();
                  o.write(imgData);
                  o.flush();
                  o.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                %>
         </ul>
        </div>
      </div>

      <div class="programare-raspuns">
        <form id="programare-raspuns" action="/adaugaRaspunsProgramare?id=<%=detaliiProgramari.programari.getIdProgramare()%>" method="post" name="adaugaRaspuns">
        <label for="raspuns">Raspuns:</label><br>
      <input type="text" name="raspuns" id="raspuns"></input><br>
      <input type="submit" class="buton-acc" value="Trimite"/>
    </form>
      </div>
    </div>
</div>
  </body>
</html>