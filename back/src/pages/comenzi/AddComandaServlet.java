package pages.comenzi;

import dto.Comenzi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static function.DatabaseFunction.*;

@WebServlet("/addComanda")
public class AddComandaServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String tip = (String) session.getAttribute("admin");

        if (username.isEmpty() && tip.isEmpty()) {
            response.sendRedirect("http://localhost:8081/Login.jsp");
        } else {
        Connection conn1 = null;
        conn1 = getConnection("bike");
        Comenzi com= new Comenzi();
        com.setNume(request.getParameter("piesa"));
        com.setModel(request.getParameter("model"));
       com.setAn(Integer.parseInt( request.getParameter("an")));
       com.setBucati(Integer.parseInt( request.getParameter("bucati")));
        com.setPret( Integer.parseInt( request.getParameter("pret")));
        com.setDataLivrare(request.getParameter("data"));
        com.setFurnizor( request.getParameter("furnizor"));

        insertComenzi(com,conn1);
        System.out.println("ok add");
        response.sendRedirect("http://localhost:8081/comenzi");


        closeConn(conn1);

    }}
}
