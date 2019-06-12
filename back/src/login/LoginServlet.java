package login;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        LogFunction logger = new LogFunction();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("password", password);


        Connection conn1 = null;


        try {
            conn1 = getConnection("bike");

            if (verifyUser(username, password, conn1)) {
                if(verifyType(username,conn1)){
                response.sendRedirect("/admin");}
                else{
                    response.sendRedirect("/programari");}

            } else {
                request.setAttribute("error", "Invalid username or password!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
                closeConn(conn1);

        RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
        rd.include(request, response);

        }
    }

