package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Piesa {
    private int idPiesa;
    private String nume;
    private String model;
    private int an;
    private int idFurnizor;
    private int pret;

    public int getIdPiesa() {
        return idPiesa;
    }
    @XmlElement
    public void setIdPiesa(int idPiesa) {
        this.idPiesa = idPiesa;
    }

    public String getNume() {
        return nume;
    }
    @XmlElement
    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getModel() {
        return model;
    }
    @XmlElement
    public void setModel(String model) {
        this.model = model;
    }

    public int getAn() {
        return an;
    }
    @XmlElement
    public void setAn(int an) {
        this.an = an;
    }

    public int getIdFurnizor() {
        return idFurnizor;
    }
    @XmlElement
    public void setIdFurnizor(int idFurnizor) {
        this.idFurnizor = idFurnizor;
    }

    public int getPret() {
        return pret;
    }
    @XmlElement
    public void setPret(int pret) {
        this.pret = pret;
    }
}
