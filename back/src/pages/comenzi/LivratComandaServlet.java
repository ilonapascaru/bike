package pages.comenzi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static function.DatabaseFunction.*;

@WebServlet("/livrat")
public class LivratComandaServlet extends HttpServlet {

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
        System.out.println("ok delete");
        String nume= request.getParameter("nume");
        String model= request.getParameter("model");
        int an = Integer.parseInt( request.getParameter("an"));
        int bucati=Integer.parseInt( request.getParameter("bucati"));
        int pret= Integer.parseInt(request.getParameter("pret"));
        insertStoc(nume,model,an,bucati,pret,conn1);
        deleteComanda(nume,model,an,conn1);
        response.sendRedirect("http://localhost:8081/comenzi");


        closeConn(conn1);

    }}
}
