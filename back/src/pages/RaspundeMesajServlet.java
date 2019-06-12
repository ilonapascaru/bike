package pages;

import com.mysql.cj.Session;
import javax.mail.Authenticator;
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

@WebServlet("/raspundeMesaj")
public class RaspundeMesajServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String emailTo = request.getParameter("email");
        final String emailFrom = "ciobanualexandru95@gmail.com";
        final String password = "";


        Connection conn = null;
        try {
            conn = getConnection("bike");

        }catch (Exception e){
            e.printStackTrace();
        }

        closeConn(conn);
    }
}
