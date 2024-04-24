package predavanja.predavanje09;

public class TestOseba {
    public static void main(String[] args) {
        Oseba o = new Oseba("Janez");
        o.izpisi();

        Oseba o2 = new Oseba("Micka") {
            void izpisi() {
                System.out.println("Ime: " + this.ime);
            }
        };
        o2.izpisi();
    }
}
