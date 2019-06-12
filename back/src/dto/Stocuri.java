package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stocuri extends Piesa {
    private int idStoc;
    private int idPiesa;
    private int cantitate;

    public int getIdStoc() {
        return idStoc;
    }

    @XmlElement
    public void setIdStoc(int idStoc) {
        this.idStoc = idStoc;
    }

    public int getIdPiesa() {
        return idPiesa;
    }
    @XmlElement
    public void setIdPiesa(int idPiesa) {
        this.idPiesa = idPiesa;
    }

    public int getCantitate() {
        return cantitate;
    }
    @XmlElement
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
