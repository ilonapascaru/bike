<%--
  Created by IntelliJ IDEA.
  User: Ciobanu Alexandru
  Date: 6/12/2019
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bikes</title>

    <script type="text/javascript">
        function loadAjax(){
            var username= document.getElementById("username").value;
            var name= document.getElementById("name").value;
            var email= document.getElementById("email").value;
            var tel= document.getElementById("tel").value;
            var password= document.getElementById("password").value;
            var password2= document.getElementById("password2").value;


            var url="ajax.jsp?username="+username +"&name="+name+"&email="+email+"&password="+password+"&password2="+password2+"&tel="+tel;
            alert(url);

            if(window.XMLHttpRequest){

                request = new XMLHttpRequest();

            }else if(window.ActiveXObject){

                request = new ActiveXObject("Microsoft.XMLHTTP");
            }

            try{
                request.onreadystatechange=sendInfo;
                request.open("POST",url,true);
                request.send();

            }catch(e){
                alert("Unable to connect server");
            }

        }

        function sendInfo(){
            var p =		document.getElementById("print");

            if(request.readyState ==1){
                var text = request.responseText;
                p.innerHTML="Please Wait.....";
                console.log("1");
            }

            if(request.readyState ==2){
                var text = request.responseText;
                console.log("2");

            }
            if(request.readyState ==3){
                var text = request.responseText;
                console.log("3");

            }
            if(request.readyState ==4){
                var text = request.responseText;
                p.innerHTML=" Request Processed  "+text;
            }
        }
    </script>
</head>
<body>
<div class="sidenav" id="mySidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="Contact.jsp">Contact</a>
    <a href="About.html">About</a>
    <a href="index.html">Home</a>
</div>
<h1>ExampleForm</h1>
<form>
    <p><label>YOUR USERNAME</label>
        <input type="text" name="username" id="username" required="required"></p>
    <p><label>YOUR NAME</label>
        <input type="text" name="username" id="name" required="required"></p>
    <p><label>YOUR EMAIL</label>
        <input type="email" name="email" id="email" required="required"></p>
    <p><label>YOUR PHONE</label>
        <input type="tel" name="tel" id="tel" required="required"></p>
    <p><label>YOUR PASSWORD</label>
    <input type="password" name="password" required="required" id="password"></p>
    <p><label>REPEAT PASSWORD</label>
        <input type="password" name="password2" required="required" id="password2"></p>
    <button type="button" onclick="loadAjax()">REGISTER</button>
</form>
<p id="print"></p>
</body>
</html>

