package pages;

import dto.DetaliiProgramari;

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
import static function.DatabaseFunction.getDetaliiProgramari;

@WebServlet("/detaliiProgramare")
public class DetaliiProgramareServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String idProgramare = request.getParameter("id");
        Connection conn = null;
        DetaliiProgramari detaliiProgramari;

        try{
            conn = getConnection("bike");
            detaliiProgramari = getDetaliiProgramari(conn, Integer.parseInt(idProgramare));
            request.setAttribute("detalii",detaliiProgramari);
            RequestDispatcher dispatcher= request.getRequestDispatcher("DetaliiProgramare.jsp");
            dispatcher.forward(request,response);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
        }
    }
}
