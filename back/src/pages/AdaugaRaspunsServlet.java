package pages;

import javax.servlet.RequestDispatcher;
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
import static function.DatabaseFunction.insertRaspunsProgramare;

@WebServlet("/adaugaRaspunsProgramare")
public class AdaugaRaspunsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String tip = (String) session.getAttribute("admin");

        if (username.isEmpty() && tip.isEmpty()) {
            response.sendRedirect("http://localhost:8081/Login.jsp");
        } else {
            String idProgramare = request.getParameter("id");
            String mesaj = request.getParameter("raspuns");

            Connection conn = null;
            try {
                conn = getConnection("bike");
                insertRaspunsProgramare(conn, Integer.parseInt(idProgramare), mesaj);
                response.sendRedirect("/programariAdmin");
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                closeConn(conn);
            }
        }
    }
}