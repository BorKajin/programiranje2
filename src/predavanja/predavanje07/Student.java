package predavanja.predavanje07;

public class Student {
    private final String ime;
    private final int id;

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