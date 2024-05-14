package predavanja.predavanje07;

@SuppressWarnings("unused")
public class Student {
    private static final int MAX_OCEN = 10;
    private final int id;
    private final int[] ocene;
    private String ime;
    private int stOcen;
    private String status;

    public Student(int id, String ime) {
        this.id = id;
        this.ime = ime;
        this.ocene = new int[MAX_OCEN];
        this.stOcen = 0;
        this.status = "Dober";
    }

    public int getId() {
        return this.id;
    }

    public String getIme() {
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double povprecje() {
        if (this.stOcen == 0) {
            return 0;
        }
        double vsota = 0;
        for (int i = 0; i < this.stOcen; i++) {
            vsota += this.ocene[i];
        }
        return vsota / this.stOcen;
    }

    public void dodajOceno(int ocena) {
        if (this.stOcen > MAX_OCEN) {
            return;
        }
        this.ocene[this.stOcen++] = ocena;
        this.status = povprecje() > 9 ? "Odličen" : "Dober";
    }

    public String getStatus() {
        return this.status;
    }

}