package pages;

import dto.Mesaj;
import dto.Stocuri;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static function.DatabaseFunction.*;

@WebServlet("/stoc")
public class StocServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        List<Stocuri> stocuri= new ArrayList<Stocuri>();

        Connection conn = null;
        try {
            conn = getConnection("bike");
            stocuri = getStoc(conn);
            request.setAttribute("stocuri",stocuri);
            RequestDispatcher dispatcher= request.getRequestDispatcher("Stocuri.jsp");
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        closeConn(conn);
    }
}
