package predavanja.predavanje09.Slikar;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Slikar {

    public static void main(String[] args) {
        StdDraw.setScale(-100, 100);
        Lik[] liki = new Lik[20];
        int stLikov = 0;
        StdDraw.enableDoubleBuffering();

        liki[stLikov++] = new Krog(0, 0, 30, Color.RED);
        liki[stLikov++] = new Pravokotnik(30, 30, 20, 30, Color.BLUE);
        liki[stLikov++] = new Kvadrat(-30, -30, 20, Color.GREEN);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                switch (key) {
                    case 'w':
                    case 'a':
                    case 's':
                    case 'd':
                        for (int i = 0; i < stLikov; i++) {
                            if (!liki[i].oznacen) continue;
                            double dx = (key == 'a') ? -5 : ((key == 'd') ? 5 : 0);
                            double dy = (key == 's') ? -5 : ((key == 'w') ? 5 : 0);
                            liki[i].premakni(dx, dy);
                        }
                        break;
                    case '+':
                    case '-':
                        for (int i = 0; i < stLikov; i++) {
                            if (!liki[i].oznacen) continue;
                            double faktor = key == '+' ? 1.1 : 0.9;
                            liki[i].spremeniVelikost(faktor);
                        }

                }
            }

            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                for (int i = 0; i < stLikov; i++) {
                    liki[i].oznacen = liki[i].pripada(x, y);
                }
            }
            StdDraw.clear();
            for (int i = 0; i < stLikov; i++) {
                liki[i].paint();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}

