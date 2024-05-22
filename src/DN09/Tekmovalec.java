package DN09;

public class Tekmovalec {
    private String drzava;
    private String izvajalec;
    private String naslovPesmi;

    public Tekmovalec(String drzava, String izvajalec, String naslovPesmi) {
        this.drzava = drzava;
        this.izvajalec = izvajalec;
        this.naslovPesmi = naslovPesmi;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getIzvajalec() {
        return izvajalec;
    }

    public String getNaslovPesmi() {
        return naslovPesmi;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s - %s", drzava, izvajalec, naslovPesmi);
    }
}
