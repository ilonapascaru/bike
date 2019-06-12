package dto;

import javafx.scene.input.InputMethodTextRun;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

public class Programari {
    private int idProgramare;
    private int idUser;
    private String model;
    private String descriere;
    private java.sql.Date data;
    private int ora;
    private String textRaspuns;
    private InputStream attachmentName;
    private int pret;


    public int getIdProgramare() {
        return idProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public String getTextRaspuns() {
        return textRaspuns;
    }

    public void setTextRaspuns(String textRaspuns) {
        this.textRaspuns = textRaspuns;
    }

    public InputStream getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(InputStream attachmentName) {
        this.attachmentName = attachmentName;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }
}
