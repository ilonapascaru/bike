package pages;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import dto.Stocuri;
import org.codehaus.jackson.map.ObjectMapper;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utile.CSVUtils;

import static function.DatabaseFunction.closeConn;

import static function.DatabaseFunction.getConnection;

@WebServlet("/exportStoc")
public class ExportStocServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out= response.getWriter();
        Connection conn1 = null;
        try {

            ArrayList<Stocuri> stocuri= new ArrayList<Stocuri>();

            conn1 = getConnection("bike");
            PreparedStatement statement = null;
            ResultSet rs;
            String sql = " select p.nume,p.model, p.pret, p.an, s.cantitate from piesa p join stocuri s on p.id_piesa=s.id_piesa";
            statement = conn1.prepareStatement(sql);
            rs = statement.executeQuery();

            File file = new File("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisier.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Stocuri.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String xmlString=null;
            StringWriter sw = new StringWriter();

            while (rs.next()) {
                Stocuri stoc=new Stocuri();
               stoc.setNume(rs.getString("nume"));
               stoc.setModel(rs.getString("model"));
               stoc.setCantitate(rs.getInt("cantitate"));
               stoc.setPret(rs.getInt("pret"));
               stoc.setAn(rs.getInt("an"));

                jaxbMarshaller.marshal(stoc, file);
                jaxbMarshaller.marshal(stoc, sw);
                 xmlString = sw.toString();
            stocuri.add(stoc);
            }

            if(request.getParameter("export").equals("PDF")) {
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "attachment; filename=file.pdf");
                Document document = new Document();
                try
                {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisier.pdf"));
                    document.open();

                    PdfPTable table = new PdfPTable(5);
                    for(Stocuri stoc:stocuri){
                        PdfPCell cell1 = new PdfPCell(new Paragraph(stoc.getNume()));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(stoc.getModel()));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(stoc.getPret()));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(stoc.getAn()));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(stoc.getCantitate()));
                        table.addCell(cell1);
                        table.addCell(cell2);
                        table.addCell(cell3);
                        table.addCell(cell4);
                        table.addCell(cell5);
                        document.add(table);
                }

                    document.close();
                    writer.close();
                } catch (DocumentException e)
                {
                    e.printStackTrace();
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }

            if(request.getParameter("export").equals("CSV")) {
                response.setContentType("text/csv");
                String csvFile = "file.csv";
                response.setHeader("Content-disposition", "attachment; filename=file.csv");
                FileWriter writer = new FileWriter(csvFile);

                for(Stocuri s:stocuri) {
                    List<String> l= new ArrayList<String>();
                    l.add(s.getModel());
                    l.add(s.getCantitate()+"");
                    l.add(s.getAn()+"");
                    l.add(s.getNume());

                    CSVUtils.writeLine(writer,l, ',', '"');

                }

                writer.flush();
                writer.close();
            }


            if(request.getParameter("export").equals("JSON")) {
                response.setContentType("application/json");
                response.setHeader("Content-disposition", "attachment; filename=file.json");
            //scriere obiect in json
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisier.json"), stocuri);
            String jsonInString = mapper.writeValueAsString(stocuri);
            System.out.println(jsonInString);
            out.print(jsonInString); }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("http://localhost:8081/stoc");


    closeConn(conn1);

}}

