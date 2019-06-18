package pages;


import com.google.gson.Gson;
import dto.Programari;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import static function.DatabaseFunction.*;

@WebServlet("/programariAdmin")
public class ProgramariAdminServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String tip = (String) session.getAttribute("admin");

        if (username.isEmpty() && tip.isEmpty()) {
            response.sendRedirect("http://localhost:8081/Login.jsp");
        } else {
            List<Programari> programari = new ArrayList<Programari>();

            Connection conn = null;
            try {
                conn = getConnection("bike");
                programari = getProgramariAdmin(conn);
                request.setAttribute("programariAdmin", programari);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ProgramariAdmin.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

            closeConn(conn);
        }
    }
}