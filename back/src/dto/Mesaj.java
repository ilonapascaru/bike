package dto;

public class Mesaj {
    private int idMesaj;
    private String name;
    private String emial;
    private String text;
    private boolean read;
    private int telefon;

    public int getIdMesaj() {
        return idMesaj;
    }

    public void setIdMesaj(int idMesaj) {
        this.idMesaj = idMesaj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }
}
