package pages;

import function.LogFunction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static function.DatabaseFunction.closeConn;
import static function.DatabaseFunction.getConnection;
import static function.DatabaseFunction.verifyUser;

import function.LogFunction;
import mysql.DatabaseDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import static function.DatabaseFunction.*;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        LogFunction logger = new LogFunction();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String telefon = request.getParameter("telefon");
        String name = request.getParameter("name");

        Connection conn1 = null;


        try {
            conn1 = getConnection("bike");

            if(!password.equals(password2)){
             request.setAttribute("errorPass", "Passwords do not match");
                //(response).sendRedirect("/register.jsp");
                 }
            else if(verifyEmail(email, conn1)==0){
                request.setAttribute("errorEmail", "Email exists");
                //(response).sendRedirect("/register.jsp");
                     }
            else if(verifyUsername(username,conn1)==0)
                request.setAttribute("errorUser", "Username exists");
            else {registerUser(username,password,name, email, Integer.parseInt(telefon),conn1);
            (response).sendRedirect("/Login.jsp");}
        }catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.include(request, response);
        closeConn(conn1);

    }
}

