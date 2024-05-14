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

    @Override
    public void spremeniVelikost(double faktor) {
        this.a *= faktor;
        this.b *= faktor;
    }

    @Override
    public boolean pripada(double x, double y) {
        return (Math.abs(x - this.x) <= (this.a / 2)) && (Math.abs(y - this.y) <= (this.b / 2));
    }

    @Override
    public void narisiOznake() {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.rectangle(this.x - this.a / 2, this.y - this.b / 2, 3, 3);
        StdDraw.rectangle(this.x - this.a / 2, this.y + this.b / 2, 3, 3);
        StdDraw.rectangle(this.x + this.a / 2, this.y + this.b / 2, 3, 3);
        StdDraw.rectangle(this.x + this.a / 2, this.y - this.b / 2, 3, 3);
    }
}
