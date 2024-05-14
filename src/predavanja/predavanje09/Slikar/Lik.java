package predavanja.predavanje09.Slikar;

import java.awt.*;

public abstract class Lik {
    double x; // x koordinata centra
    double y; // x koordinata centra
    Color barva;
    public boolean oznacen;

    public Lik(double x, double y, Color barva) {
        this.x = x;
        this.y = y;
        this.barva = barva;
        this.oznacen = false;
    }

    public abstract void narisi();

    public abstract void spremeniVelikost(double faktor);

    public void premakni(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public abstract boolean pripada(double x, double y);

    public abstract void narisiOznake();

    public void paint() {
        narisi();
        if (oznacen) narisiOznake();
    }
}
