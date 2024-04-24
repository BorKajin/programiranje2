package predavanja.predavanje09;

import zbirke.Funkcija;

public class Nicle {
    public static void main(String[] args) {
        System.out.println(Newton.nicla(3, new Sinus(), 3));
        System.out.println("---------------------");
        System.out.println(Newton.nicla(2, new Poli1(), 10));
        System.out.println("---------------------");
        System.out.println(Newton.nicla(2, new Funkcija() {
            @Override
            public double vrednost(double x) {
                return x * x - 3 * x - 5;
            }

            @Override
            public double odvod(double x) {
                return 2 * x - 3;
            }
        }, 20));
        System.out.println("---------------------");
    }
}

class Poli1 extends zbirke.Funkcija {

    @Override
    public double vrednost(double x) {
        return x * x - 3 * x - 4;
    }

    @Override
    public double odvod(double x) {
        return 2 * x - 3;
    }
}
