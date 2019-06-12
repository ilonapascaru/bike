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
      <a href="index.html">Home</a>
      <a href="Contact.jsp">Contact</a>
      <a href="About.html">About</a>
    </div>
  <form id="myForm" method="POST" action="/login">
  <ul class="myList">
    <li>
      <label for="username">Email:</label><br>
      <input type="text" name="username" id="username" placeholder="example@mail.com" required/>
    </li>
    <li>
      <label for="password">Password:</label><br>
      <input type="password" name="password" id="password" placeholder="*****" required/>
    </li>
    <li>
      <input type="submit" class="loginbutton" value="LOGIN"/>
    </li>
  </ul>
      <%
          String login_msg1=(String)request.getAttribute("error");
          if(login_msg1!=null)
              out.println("<font color=red size=3px>"+login_msg1+"</font>");
      %>
</form>

</div>

  </body>
</html>