package predavanja.predavanje09.Slikar;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Krog extends Lik {
    double r;

    public Krog(double x, double y, double r, Color barva) {
        super(x, y, barva);
        this.r = r;
    }

    public Krog(double x, double y, double r) {
        super(x, y, Color.black);
        this.r = r;
    }

    @Override
    public void narisi() {
        StdDraw.setPenColor(this.barva);
        StdDraw.circle(this.x, this.y, this.r);

    }

    @Override
    public void spremeniVelikost(double faktor) {
        this.r *= faktor;
    }

    @Override
    public boolean pripada(double x, double y) {
        return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 1) <= Math.pow(this.r, 2);
    }

    @Override
    public void narisiOznake() {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.rectangle(this.x - this.r, this.y - this.r, 3, 3);
        StdDraw.rectangle(this.x - this.r, this.y + this.r, 3, 3);
        StdDraw.rectangle(this.x + this.r, this.y + this.r, 3, 3);
        StdDraw.rectangle(this.x + this.r, this.y - this.r, 3, 3);
    }
}
