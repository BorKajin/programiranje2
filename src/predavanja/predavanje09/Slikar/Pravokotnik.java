package predavanja.predavanje09.Slikar;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Pravokotnik extends Lik {
    double a;
    double b;

    public Pravokotnik(double x, double y, double a, double b, Color barva) {
        super(x, y, barva);
        this.a = a;
        this.b = b;
    }

    public Pravokotnik(double x, double y, double a, double b) {
        super(x, y, Color.black);
        this.a = a;
        this.b = b;
    }

    @Override
    public void narisi() {
        StdDraw.setPenColor(this.barva);
        StdDraw.rectangle(this.x, this.y, this.a / 2, this.b / 2);
    }
}
