<!DOCTYPE html>
<html>
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


<br><br>

<article>
          <form action="/register" method="POST">

              <table class="register_table" align="center">

                  <tr>
                      <td colspan=2>
                          <span><b>Registration Form</b></span>
                      </td>
                  </tr>
                  <tr>
                      <td>Username</td>
                      <td>
                          <input type="text" name="username" id="firstname" size="30">
                      </td>
                  </tr>
                  <tr>
                      <td>Nume</td>
                      <td>
                          <input type="text" name="name" id="name" size="30">
                      </td>
                  </tr>
                  <tr>
                      <td>Password</td>
                      <td>
                          <input type="password" name="password" id="password" size="30">
                      </td>

                  </tr>

                  <tr>
                      <td>Repeat password</td>
                      <td>
                          <input type="password" name="password2" id="password2" size="30">
                      </td>
                  </tr>




                  <tr>
                      <td>Email</td>
                      <td>
                          <input type="text" name="email" id="emailid" size="30">
                      </td>
                  </tr>

				  
				  <tr>
                      <td>Telefon</td>
                      <td>
                          <input type="text" name="telefon" id="telefon" size="30">
                      </td>
                  </tr>
				  
                  <tr>
                      <td colspan="2">
                          <input type="submit" value="Submit Form" /><br><br>
                          <%
                              String register_msg1=(String)request.getAttribute("errorPass");
                              if(register_msg1!=null)
                                  out.println("<font color=red size=3px>"+register_msg1+"</font>");
                          %>
                          <br><br>
                          <%
                              String register_msg2=(String)request.getAttribute("errorEmail");
                              if(register_msg2!=null)
                                  out.println("<font color=red size=3px>"+register_msg2+"</font>");
                          %>
                          <br><br>
                          <%
                              String register_msg3=(String)request.getAttribute("errorUser");
                              if(register_msg3!=null)
                                  out.println("<font color=red size=3px>"+register_msg3+"</font>");
                          %>

                          <br><br>
                          <%
                              String register_msg4=(String)request.getAttribute("campGol");
                              if(register_msg4!=null)
                                  out.println("<font color=red size=3px>"+register_msg4+"</font>");
                          %>
                      </td>
                  </tr>
              </table>



          </form>

        </article>

<br><br>
<br><br>
<br><br>



<footer>Copyright &copy; Proiect TW</footer>

</body>
</html>
