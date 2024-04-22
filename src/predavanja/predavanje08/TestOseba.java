package predavanja.predavanje08;

public class TestOseba {
    public static void main(String[] args) {
        Oseba o = new Oseba("Janez");
        o.izpisi();
        Clovek c = new Clovek();
        c.setIme("Peter");
        c.izpisi();
    }
}

class Clovek extends Oseba {

}
