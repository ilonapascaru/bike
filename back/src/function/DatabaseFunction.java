package function;

import dto.*;
import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFunction {

    public static Connection getConnection(String baza_date) {
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver");

            String userBD = "root";
            String passBD = "root";
            String urlBD = "jdbc:mysql://localhost:3306/bike";
            conn = DriverManager.getConnection(urlBD, userBD, passBD);
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException nf) {
            nf.printStackTrace();
            return null;
        }
    }

    public static boolean verifyUser(String email, String parola, Connection conn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  username , password FROM USER where username=? and password=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, parola);
            rs = stmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeRs(rs);
            closePs(stmt);
        }
    }

    public static boolean verifyType(String username, Connection conn){

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int type;
        try {
            String sql = "SELECT type FROM USER where username=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()) {
                type = rs.getInt("type");
                if (type==1)
                    return false;
                else
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeRs(rs);
            closePs(stmt);
        }
        return true;
    }
    public static List<Programari> getProgram(String username, Connection conn){

        List<Programari> programari= new ArrayList<Programari>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT p.model,p.descriere, p.data, p.ora,p.text_raspuns,p.id_programare, p.pret FROM PROGRAMARI p join USER u on u.id_user=p.id_user where u.username=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Programari programare= new Programari();
                programare.setModel(rs.getString("model"));
                programare.setIdProgramare(rs.getInt("id_programare"));
                programare.setData(rs.getDate("data"));
                programare.setOra(rs.getInt("ora"));
                programare.setDescriere(rs.getString("descriere"));
                programare.setTextRaspuns(rs.getString("text_raspuns"));
                programare.setPret(rs.getInt("pret"));
                programari.add(programare);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeRs(rs);
            closePs(stmt);
        }
        return programari;
    }

    public static List<Programari> getProgramariAdmin(Connection conn){

        List<Programari> programari= new ArrayList<Programari>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT p.model,p.descriere, p.data, p.ora,p.text_raspuns,p.id_programare, p.pret FROM PROGRAMARI p join USER u on u.id_user=p.id_user";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Programari programare= new Programari();
                programare.setModel(rs.getString("model"));
                programare.setIdProgramare(rs.getInt("id_programare"));
                programare.setData(rs.getDate("data"));
                programare.setOra(rs.getInt("ora"));
                programare.setDescriere(rs.getString("descriere"));
                programare.setTextRaspuns(rs.getString("text_raspuns"));
                programare.setPret(rs.getInt("pret"));
                programari.add(programare);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeRs(rs);
            closePs(stmt);
        }
        return programari;
    }

    public static List<Mesaj> getMess(Connection conn) throws SQLException {
        List<Mesaj> mesaje = new ArrayList<Mesaj>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "select * from mesaj";
            stmt = conn.prepareStatement(sql);
            rs= stmt.executeQuery();
            while(rs.next()){
                Mesaj mes= new Mesaj();
                mes.setEmial(rs.getString("email"));
                mes.setIdMesaj(rs.getInt("id_mesaj"));
                mes.setName(rs.getString("nume"));
                mes.setText(rs.getString("text"));
                mes.setRead(rs.getBoolean("citit"));
                mes.setTelefon(rs.getInt("telefon"));
                mesaje.add(mes);

            }
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        } finally {
            closeRs(rs);
            closePs(stmt);
        }
        return mesaje;
    }
    public static DetaliiProgramari getDetaliiProgramari(Connection conn, int id){
        DetaliiProgramari detaliiProgramari = new DetaliiProgramari();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "select u.name, u.email, u.telefon, p.model, p.descriere, p.data, p.ora from user u  join programari p ON " +
                    "u.id_user=p.id_user where p.id_programare=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            while (rs.next()){
                detaliiProgramari.user.setName(rs.getString("name"));
                detaliiProgramari.user.setEmail(rs.getString("email"));
                detaliiProgramari.user.setTelefon(rs.getInt("telefon"));
                detaliiProgramari.programari.setModel(rs.getString("model"));
                detaliiProgramari.programari.setDescriere(rs.getString("descriere"));
                detaliiProgramari.programari.setData(rs.getDate("data"));
                detaliiProgramari.programari.setOra(rs.getInt("ora"));
                detaliiProgramari.programari.setIdProgramare(id);


            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeRs(rs);
            closePs(stmt);
        }


        return detaliiProgramari;
    }
    public static List<Stocuri> getStoc(Connection conn) throws SQLException {
        List<Stocuri> stocuri = new ArrayList<Stocuri>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = " select p.nume,p.model, p.pret, p.an, s.cantitate from piesa p join stocuri s on p.id_piesa=s.id_piesa";
            stmt = conn.prepareStatement(sql);
            rs= stmt.executeQuery();
            while(rs.next()){
                Stocuri stoc=new Stocuri();
                stoc.setNume(rs.getString("nume"));
                stoc.setModel(rs.getString("model"));
                stoc.setCantitate(rs.getInt("cantitate"));
                stoc.setPret(rs.getInt("pret"));
                stoc.setAn(rs.getInt("an"));
                stocuri.add(stoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        } finally {
            closeRs(rs);
            closePs(stmt);
        }
        return stocuri;
    }

    public static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("inchis");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void SendMesaj(String name, String email, String text, int phone, Connection conn){
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        int mesajId;

        try{
            String sql = "SELECT COUNT(*) AS ID FROM Mesaj";
            stmt = conn.prepareStatement(sql);
            rs=stmt.executeQuery();
            rs.next();
            mesajId = rs.getInt("ID");

            String sql2 = "INSERT INTO Mesaj(`id_mesaj`,`nume`,`email`,`text`,`telefon`) " +
                    "VALUES (?,?,?,?,?)";
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1,mesajId+1);
            stmt2.setString(2,name);
            stmt2.setString(3,email);
            stmt2.setString(4,text);
            stmt2.setInt(5, phone);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Comenzi> getComenzi(Connection conn) throws SQLException {
        List<Comenzi> comenzi = new ArrayList<Comenzi>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            String sql = "select p.nume,p.model, p.pret, p.an, s.bucati, s.data_livrare, f.nume 'f' from piesa p join comenzi s on p.id_piesa=s.id_piesa join furnizori f on p.id_furnizor=f.id_furnizor";
            stmt = conn.prepareStatement(sql);
            rs= stmt.executeQuery();
            while(rs.next()){
                Comenzi c=new Comenzi();
                c.setNume(rs.getString("nume"));
                c.setModel(rs.getString("model"));
                c.setBucati(rs.getInt("bucati"));
                c.setPret(rs.getInt("pret"));
                c.setAn(rs.getInt("an"));
                c.setDataLivrare(rs.getDate("data_livrare"));
                c.setFurnizor(rs.getString("f"));
                comenzi.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeRs(rs);
            closePs(stmt);
        }
        return comenzi;
    }

    public static void registerUser(String username, String password, String name, String email, int telefon, Connection conn){
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        int userId;


        try {
            String sql2="SELECT COUNT(id_user) as ID FROM `bike`.`user`";
            stmt2 = conn.prepareStatement(sql2);
            rs2 = stmt2.executeQuery();
            rs2.next();
            userId = rs2.getInt("ID");

            String sql = "INSERT INTO `bike`.`user` (`id_user`, `username`, `password`, `name`, `email`, `type`, `telefon`) VALUES (?,?,?,?,?,1,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId+1);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, name);
            stmt.setString(5,email);
            stmt.setInt(6, telefon);
            stmt.executeUpdate();
            //return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        } finally {
            closeRs(rs);
            closePs(stmt);
        }


    }



    public static int verifyUsername(String username, Connection conn){

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String user;
        try {
            String sql="SELECT username FROM USER";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                user = rs.getString("username");
                if (user.equals(username))
                    return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeRs(rs);
            closePs(stmt);
        }


        return 1;


    }


    public static int verifyEmail(String email, Connection conn){

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String Email;
        try {
            String sql="SELECT email FROM USER";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Email = rs.getString("email");
                if (Email.equals(email))
                    return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeRs(rs);
            closePs(stmt);
        }


        return 1;


    }

    public static int getUserId(String username, Connection conn){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int userId = -1;
        try{

            String getUserIdSQL = "SELECT id_user AS ID FROM `bike`.`user` WHERE username=?";
            stmt = conn.prepareStatement(getUserIdSQL);
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            rs.next();
            userId = rs.getInt("ID");

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeRs(rs);
            closePs(stmt);
        }
        return userId;
    }

    public static int getProgramareID( Connection conn){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int programareId = -1;
        try{
            String getIdCountSQL = "SELECT COUNT(ID_PROGRAMARE) AS ID FROM `bike`.`programari`";
            stmt = conn.prepareStatement(getIdCountSQL);
            rs = stmt.executeQuery();
            rs.next();
            programareId = rs.getInt("ID");

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closeRs(rs);
            closePs(stmt);
        }
        return programareId;
    }

    public static void insertRaspunsProgramare(Connection conn, int idProgramare, String raspuns){
        PreparedStatement stmt = null;
        try{
            String sqlUpdateRaspuns = "UPDATE `bike`.`programari`" +
                    "SET text_raspuns=?" +
                    "WHERE id_programare=?";
            stmt = conn.prepareStatement(sqlUpdateRaspuns);
            stmt.setString(1,raspuns);
            stmt.setInt(2,idProgramare);
            stmt.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            closePs(stmt);
        }
    }
    public static void insertProgramare(Connection conn, Programari progr){
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            String insertSQL = "INSERT INTO `bike`.`programari`" +
                    "(`id_programare`, `id_user`, `model`, `descriere`, `data`, `ora`, `atachament_name`) " +
                    "VALUES(?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, progr.getIdProgramare()+1);
            stmt.setInt(2, progr.getIdUser());
            stmt.setString(3, progr.getModel());
            stmt.setString(4, progr.getDescriere());
            stmt.setDate(5, (Date) progr.getData());
            stmt.setInt(6, progr.getOra());
            stmt.setBlob(7,progr.getAttachmentName());
            stmt.executeUpdate();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeRs(rs);
            closePs(stmt);
            closeConn(conn);
        }


    }



    public static void insertArtefact(String localizare,String tip1,String rol, String tip, String secol,
                                      String valoare,String denumire, String descriere,String link, Connection conn){
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmtTip = null;
        PreparedStatement stmtLoc = null;
        PreparedStatement stmtRol=null;


        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3=null;
        ResultSet rs4=null;
        ResultSet rs5=null;
        int artefactId;
        int tipId=0;
        int locId=0;
        int rolId=0;


        try {

            String sql2="SELECT COUNT(id_artefact) as ID FROM `artifacty`.`artefacte`";
            stmt2 = conn.prepareStatement(sql2);
            rs2 = stmt2.executeQuery();
            rs2.next();
            artefactId = rs2.getInt("ID");

            String sql3="SELECT id_tip as ID FROM `artifacty`.`tip` WHERE denumire=? ";
            stmtTip = conn.prepareStatement(sql3);
            stmtTip.setString(1, tip1);
            rs3 = stmtTip.executeQuery();

            while (rs3.next()) {
                tipId = rs3.getInt("ID");
            }

            String sql4="SELECT id_loc as ID FROM `artifacty`.`localizari` WHERE denumire=?";
            stmtLoc = conn.prepareStatement(sql4);
            stmtLoc.setString(1, localizare);
            rs4 = stmtLoc.executeQuery();
            while (rs4.next()) {
                locId = rs4.getInt("ID");
            }

            String sql5="SELECT id_rol as ID FROM `artifacty`.`roluri` WHERE denumire=?";
            stmtRol = conn.prepareStatement(sql5);
            stmtRol.setString(1, rol);
            rs5 = stmtRol.executeQuery();
            while (rs5.next()) {
                rolId = rs5.getInt("ID");
            }

            String sql = "INSERT INTO `artifacty`.`artefacte` (`id_artefact`, `id_user`, `denumire`, `id_tip`," +
                    " `id_rol`, `id_loc`, `valoare`, `licenta`, `termeni_cheie`,`descriere`,`autor`,`perioada_datata`,`link`) " +
                    "VALUES (?,1,?,?,?,?,?,'','',?,'',?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, artefactId+1);
            stmt.setString(2, denumire);
            //stmt.setString(3, denumire);
            stmt.setInt(3, tipId);
            stmt.setInt(4, rolId);
            stmt.setInt(5, locId);
            stmt.setString(6,valoare);
            stmt.setString(7, descriere);
            stmt.setString(8, secol);
            stmt.setString(9, link);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        } finally {
            closeRs(rs);
            closePs(stmt);
        }


    }


}


