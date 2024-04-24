package predavanja.predavanje09;

import zbirke.Funkcija;

public class Sinus extends Funkcija {

    @Override
    public double vrednost(double x) {
        return Math.sin(x);
    }

    @Override
    public double odvod(double x) {
        return Math.cos(x);
    }
}
