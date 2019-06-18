package pages.comenzi;

import dto.Comenzi;
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

@WebServlet("/comenzi")
public class ComenziServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Comenzi> comenzi= new ArrayList<Comenzi>();
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String tip = (String) session.getAttribute("admin");

        if (username.isEmpty() && tip.isEmpty()) {
            response.sendRedirect("http://localhost:8081/Login.jsp");
        } else {
        Connection conn = null;
        try {
            conn = getConnection("bike");
            comenzi = getComenzi(conn);
            request.setAttribute("comenzi",comenzi);
            RequestDispatcher dispatcher= request.getRequestDispatcher("Comenzi.jsp");
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        closeConn(conn);
    }}
}
