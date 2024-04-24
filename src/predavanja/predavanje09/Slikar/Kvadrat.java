package predavanja.predavanje09.Slikar;

import java.awt.*;

public class Kvadrat extends Pravokotnik {
    public Kvadrat(double x, double y, double a, Color barva) {
        super(x, y, a, a, barva);
    }

    public Kvadrat(double x, double y, double a) {
        super(x, y, a, a);
    }
}
