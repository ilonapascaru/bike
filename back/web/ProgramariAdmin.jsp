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
        <div class="calendar">
        <div class="month">      
            <ul>
            <li class="prev">&#10094;</li>
            <li class="next">&#10095;</li>
            <li>
            Iunie<br>
            <span style="font-size:18px">2019</span>
            </li>
           </ul>
        </div>

    <ul class="weekdays">
      <li>Mo</li>
      <li>Tu</li>
      <li>We</li>
      <li>Th</li>
      <li>Fr</li>
      <li>Sa</li>
      <li>Su</li>
    </ul>

    <ul class="days">  
      <li>1</li>
      <li>2</li>
      <li>3</li>
      <li>4</li>
      <li>5</li>
      <li>6</li>
      <li>7</li>
      <li>8</li>
      <li>9</li>
      <li>10</li>
      <li>11</li>
      <li>12</li>
      <li>13</li>
      <li>14</li>
      <li><span class = "active">15</span></li>
      <li>16</li>
      <li>17</li>
      <li>18</li>
      <li>19</li>
      <li>20</li>
      <li>21</li>
      <li>22</li>
      <li>23</li>
      <li>24</li>
      <li>25</li>
      <li>26</li>
      <li>27</li>
      <li>28</li>
      <li>29</li>
      <li>30</li>
      <li> </li>
      <li> </li>
      <li> </li>
      <li> </li>
      <li> </li>
    </ul>
        </div>
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
