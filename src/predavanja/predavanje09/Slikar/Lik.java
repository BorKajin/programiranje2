package predavanja.predavanje09.Slikar;

import java.awt.*;

public abstract class Lik {
    double x; // x koordinata centra
    double y; // x koordinata centra
    Color barva;

    public Lik(double x, double y, Color barva) {
        this.x = x;
        this.y = y;
        this.barva = barva;
    }

    public abstract void narisi();

    public void premakni(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
