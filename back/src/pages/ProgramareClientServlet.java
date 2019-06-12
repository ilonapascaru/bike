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

@WebServlet("/programari")
public class ProgramareClientServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        List<Programari> programari= new ArrayList<Programari>();

        Connection conn = null;
        try {
            conn = getConnection("bike");
             programari = getProgram(username, conn);
            request.setAttribute("programari",programari);
            RequestDispatcher dispatcher= request.getRequestDispatcher("User.jsp");
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

        closeConn(conn);
    }
}
