package predavanja.predavanje08;

public class Oseba {
    private String ime;

    public Oseba() {
        ime = "";
    }

    public Oseba(String ime) {
        this.ime = ime;
    }

    public void izpisi() {
        System.out.println("Oseba:" + this.ime);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
