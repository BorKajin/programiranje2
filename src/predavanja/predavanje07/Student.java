package predavanja.predavanje07;

public class Student {
    private String ime;
    private int id;

    public Student() {
        this(0, "");
    }

    public Student(int id, String ime) {
        super();
        this.id = id;
        this.ime = ime;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

}