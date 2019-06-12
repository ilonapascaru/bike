package pages;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.Stocuri;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import utile.CSVUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static function.DatabaseFunction.closeConn;
import static function.DatabaseFunction.getConnection;

@WebServlet("/importStoc")
public class ImportStocServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out= response.getWriter();
        Connection conn1 = null;

        ObjectMapper objectMapper = new ObjectMapper();
        List<Stocuri> stocuri = objectMapper.readValue(new File("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisier.json"), new TypeReference<List<Stocuri>>(){});

        System.out.println(stocuri);

        response.sendRedirect("http://localhost:8081/stoc");


        closeConn(conn1);

    }}