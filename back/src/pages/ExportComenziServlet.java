package pages.comenzi;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import dto.Comenzi;
import dto.Stocuri;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utile.CSVUtils;

import static function.DatabaseFunction.closeConn;

import static function.DatabaseFunction.getConnection;

@WebServlet("/exportComenzi")
public class ExportComenziServlet extends HttpServlet {
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
        try {

            ArrayList<Comenzi> comenzi= new ArrayList<Comenzi>();

            conn1 = getConnection("bike");
            PreparedStatement statement = null;
            ResultSet rs;
            String sql = " select p.nume,p.model, p.pret, p.an, s.bucati, s.data_livrare, s.furnizor 'f'  from piesa p join comenzi s on p.id_piesa=s.id_piesa";
            statement = conn1.prepareStatement(sql);
            rs = statement.executeQuery();

            JAXBContext jaxbContext = JAXBContext.newInstance(Stocuri.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();

            while (rs.next()) {
                Comenzi c =new Comenzi();
                c.setNume(rs.getString("nume"));
                c.setModel(rs.getString("model"));
                c.setBucati(rs.getInt("bucati"));
                c.setPret(rs.getInt("pret"));
                c.setAn(rs.getInt("an"));
                c.setDataLivrare(rs.getString("data_livrare"));
                c.setFurnizor(rs.getString("f"));

                jaxbMarshaller.marshal(c, sw);
                comenzi.add(c);
            }

            if(request.getParameter("export").equals("PDF")) {
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "attachment; filename=file.pdf");
                Document document = new Document();
                try
                {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Projects\\TWworkspace\\back\\web\\fisierC.pdf"));
                    document.open();

                    PdfPTable table = new PdfPTable(7);
                    for(Comenzi c:comenzi){
                        PdfPCell cell1 = new PdfPCell(new Paragraph(c.getNume()));
                        table.addCell(cell1);

                        PdfPCell cell2 = new PdfPCell(new Paragraph(c.getModel()));
                        table.addCell(cell2);

                        PdfPCell cell3 = new PdfPCell(new Paragraph(c.getPret()+""));
                        table.addCell(cell3);

                        PdfPCell cell4 = new PdfPCell(new Paragraph(c.getAn()+""));
                        table.addCell(cell4);

                        PdfPCell cell5 = new PdfPCell(new Paragraph(c.getBucati()+""));
                        table.addCell(cell5);

                        PdfPCell cell6 = new PdfPCell(new Paragraph(c.getDataLivrare()+""));
                        table.addCell(cell6);

                        PdfPCell cell7 = new PdfPCell(new Paragraph(c.getFurnizor()+""));
                        table.addCell(cell7);

                    }
                    document.add(table);
                    document.close();
                    writer.close();

                    String filePath = "D:\\Projects\\TWworkspace\\back\\web\\fisierC.pdf";
                    File downloadFile = new File(filePath);
                    FileInputStream inStream = new FileInputStream(downloadFile);

                    // if you want to use a relative path to context root:
                    String relativePath = getServletContext().getRealPath("");
                    System.out.println("relativePath = " + relativePath);

                    // obtains ServletContext
                    ServletContext context = getServletContext();

                    // gets MIME type of the file
                    String mimeType = context.getMimeType(filePath);
                    if (mimeType == null) {
                        // set to binary type if MIME mapping not found
                        mimeType = "application/octet-stream";
                    }
                    System.out.println("MIME type: " + mimeType);

                    // modifies response
                    response.setContentType(mimeType);
                    response.setContentLength((int) downloadFile.length());

                    // forces download
                    String headerKey = "Content-Disposition";
                    String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                    response.setHeader(headerKey, headerValue);

                    // obtains response's output stream
                    OutputStream outStream = response.getOutputStream();

                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }

                    inStream.close();
                    outStream.close();

                } catch (DocumentException e)
                {
                    e.printStackTrace();
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }

            if(request.getParameter("export").equals("CSV")) {
                String csvFile = "D:\\Projects\\TWworkspace\\back\\web\\fileC.csv";
                FileWriter writer = new FileWriter(csvFile);

                for(Comenzi s:comenzi) {
                    List<String> l= new ArrayList<String>();
                    l.add(s.getModel());
                    l.add(s.getNume());
                    l.add(s.getBucati()+"");
                    l.add(s.getAn()+"");
                    l.add(s.getPret()+"");
                    l.add(s.getFurnizor());
                    l.add(s.getDataLivrare());

                    CSVUtils.writeLine(writer,l, ',');

                }

                writer.flush();
                writer.close();

                String filePath = "D:\\Projects\\TWworkspace\\back\\web\\fileC.csv";
                File downloadFile = new File(filePath);
                FileInputStream inStream = new FileInputStream(downloadFile);

                // if you want to use a relative path to context root:
                String relativePath = getServletContext().getRealPath("");
                System.out.println("relativePath = " + relativePath);

                // obtains ServletContext
                ServletContext context = getServletContext();

                // gets MIME type of the file
                String mimeType = context.getMimeType(filePath);
                if (mimeType == null) {
                    // set to binary type if MIME mapping not found
                    mimeType = "application/octet-stream";
                }
                System.out.println("MIME type: " + mimeType);

                // modifies response
                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());

                // forces download
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);

                // obtains response's output stream
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inStream.close();
                outStream.close();

            }


            if(request.getParameter("export").equals("JSON")) {
                PrintWriter out= response.getWriter();
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(comenzi);
                System.out.println(jsonInString);
                out.print(jsonInString); }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //sresponse.sendRedirect("http://localhost:8081/comenzi");


        closeConn(conn1);

    }}}

