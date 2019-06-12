package pages;
import dto.Programari;
import function.LogFunction;
import mysql.DatabaseDetails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Part;


import static function.DatabaseFunction.*;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/adaugaProgramari")
public class AdaugaProgramareServlet extends HttpServlet {

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html");
        LogFunction logger = new LogFunction();
        Programari progr = new Programari();
        InputStream inputStream = null;

        progr.setModel(request.getParameter("model"));
        progr.setDescriere(request.getParameter("descriere"));
        //progr.setAttachmentName(request.getParameter("fisier"));
        Part filePart = request.getPart("fisier");
        if(filePart!=null){
            inputStream = filePart.getInputStream();
        }
        progr.setAttachmentName(inputStream);
        progr.setOra(Integer.parseInt(request.getParameter("ora")));
        String dataString = request.getParameter("an") + "-" + request.getParameter("luna") + "-" + request.getParameter("ziua");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date data = format.parse(dataString);
            java.sql.Date sqlDate = new java.sql.Date(data.getTime());
            progr.setData(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        Connection conn = null;

        try{
            conn = getConnection("bike");
            progr.setIdUser(getUserId(username,conn));
            progr.setIdProgramare(getProgramareID(conn));
            insertProgramare(conn, progr);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeConn(conn);
        }
        response.sendRedirect("/programari");
    }
}
