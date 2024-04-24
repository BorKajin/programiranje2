package predavanja.predavanje09;

import zbirke.Funkcija;

public class Newton {
    public static double nicla(double x0, Funkcija f, int n) {
        for (int i = 0; i < n; i++) {
            x0 = x0 - f.vrednost(x0) / f.odvod(x0);
        }
        return x0;
    }
}
