package pages.comenzi;

import dto.Comenzi;
import dto.Stocuri;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static function.DatabaseFunction.*;

@WebServlet("/importComanda")
@MultipartConfig
public class ImportComenziServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String tip = (String) session.getAttribute("admin");

        if (username.isEmpty() && tip.isEmpty()) {
            response.sendRedirect("http://localhost:8081/Login.jsp");
        } else {
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        List<Comenzi> comenzi= new ArrayList<Comenzi>();
        Connection conn1 = null;
        conn1 = getConnection("bike");
        InputStream fileContent = filePart.getInputStream();
        if(fileName.contains(".json")) {

            ObjectMapper objectMapper = new ObjectMapper();
            comenzi = objectMapper.readValue(fileContent, new TypeReference<List<Comenzi>>() {
            });
        }

        if (fileName.contains(".csv")){
            String theString = IOUtils.toString( filePart.getInputStream(), StandardCharsets.UTF_8);
            System.out.println(theString);
            String[] lines = theString.split(System.getProperty("line.separator"));
            for(String line:lines){
               String[] s = line.split("\\s*,\\s*");
                Comenzi st =new Comenzi();
               st.setNume(s[1]);
               st.setModel(s[0]);
               st.setAn(Integer.parseInt(s[3]));
               st.setBucati(Integer.parseInt(s[2]));
               st.setPret(Integer.parseInt(s[4]));
               st.setFurnizor(s[5]);
               st.setDataLivrare(s[6]);
                comenzi.add(st);
            }
        }
            for (Comenzi s : comenzi) {
                insertComenzi(s, conn1);
            }

        response.sendRedirect("http://localhost:8081/comenzi");


        closeConn(conn1);

    }}}