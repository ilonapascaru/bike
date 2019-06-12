<%--
  Created by IntelliJ IDEA.
  User: Ciobanu Alexandru
  Date: 6/11/2019
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*" %>
<%
    String result;
    final String to = "dragosn.ariton@gmail.com";//request.getParameter("email");
    final String subject = "Raspuns Automat";
    final String messg = request.getParameter("raspuns");
    final String from = "ciobanualexandru95@gmail.com";
    final String pass = "";
    String host = "smtp.gmail.com";

            Properties props = new Properties();

    props.put("mail.smtp.host", host);
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.user", from);
    props.put("mail.password", pass);
    props.put("mail.port", "457");

    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, pass);
        }
    });

    try {
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));
        message.setSubject(subject);
        message.setText(messg);
        Transport.send(message);
        result = "Mail sent successfully";
    } catch (MessagingException mex) {
        mex.printStackTrace();
        result = "Error: unable to send mail";
    }
%>
%>
<html>
<head>
    <title>Bikes</title>
</head>
<body>
<h2><% out.println(result);%></h2>
</body>
</html>
