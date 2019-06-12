package dto;

import java.util.Date;

public class Comenzi extends Piesa {

    private int idComanda;
    private int idPiesa;
    private String furnizor;
    private Date dataLivrare;
    private int bucati;

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdPiesa() {
        return idPiesa;
    }

    public void setIdPiesa(int idPiesa) {
        this.idPiesa = idPiesa;
    }

    public String getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(String furnizor) {
        this.furnizor = furnizor;
    }

    public Date getDataLivrare() {
        return dataLivrare;
    }

    public void setDataLivrare(Date dataLivrare) {
        this.dataLivrare = dataLivrare;
    }

    public int getBucati() {
        return bucati;
    }

    public void setBucati(int bucati) {
        this.bucati = bucati;
    }
}
