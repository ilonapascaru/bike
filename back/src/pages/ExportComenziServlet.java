package pages;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import dto.Comenzi;
import dto.Stocuri;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
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

        PrintWriter out= response.getWriter();
        Connection conn1 = null;
        try {

            ArrayList<Comenzi> comenzi= new ArrayList<Comenzi>();

            conn1 = getConnection("bike");
            PreparedStatement statement = null;
            ResultSet rs;
            String sql = " select p.nume,p.model, p.pret, p.an, s.bucati, s.data_livrare, f.nume 'f'  from piesa p join comenzi s on p.id_piesa=s.id_piesa join furnizori f on p.id_furnizor=f.id_furnizor";
            statement = conn1.prepareStatement(sql);
            rs = statement.executeQuery();

            File file = new File("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisierC.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Stocuri.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String xmlString=null;
            StringWriter sw = new StringWriter();

            while (rs.next()) {
                Comenzi c =new Comenzi();
                c.setNume(rs.getString("nume"));
                c.setModel(rs.getString("model"));
                c.setBucati(rs.getInt("bucati"));
                c.setPret(rs.getInt("pret"));
                c.setAn(rs.getInt("an"));
                c.setDataLivrare(rs.getDate("data_livrare"));
                c.setFurnizor(rs.getString("f"));

                jaxbMarshaller.marshal(c, file);
                jaxbMarshaller.marshal(c, sw);
                xmlString = sw.toString();
                comenzi.add(c);
            }

            if(request.getParameter("export").equals("PDF")) {
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "attachment; filename=file.pdf");
                Document document = new Document();
                try
                {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisierC.pdf"));
                    document.open();

                    PdfPTable table = new PdfPTable(5);
                    for(Comenzi c:comenzi){
                        PdfPCell cell1 = new PdfPCell(new Paragraph(c.getNume()));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(c.getModel()));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(c.getFurnizor()));
                        table.addCell(cell1);
                        table.addCell(cell2);
                        table.addCell(cell3);
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
                String csvFile = "fileC.csv";
                response.setHeader("Content-disposition", "attachment; filename=fileC.csv");
                FileWriter writer = new FileWriter(csvFile);

                for(Comenzi s:comenzi) {
                    List<String> l= new ArrayList<String>();
                    l.add(s.getModel());
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
                mapper.writeValue(new File("D:\\Projects\\TWworkspace\\Artifacty-master\\web\\fisierC.json"), comenzi);
                String jsonInString = mapper.writeValueAsString(comenzi);
                System.out.println(jsonInString);
                out.print(jsonInString); }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("http://localhost:8081/comenzi");


        closeConn(conn1);

    }}

