package logout;

import mysql.DatabaseDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import static function.DatabaseFunction.closeConn;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseDetails db = (DatabaseDetails) request.getSession().getAttribute("database_details");
        Connection conn = db.getConn();
        closeConn(conn);
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "index.jsp");
    }

}
