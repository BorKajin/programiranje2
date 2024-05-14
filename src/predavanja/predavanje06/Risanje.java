package predavanja.predavanje06;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class Risanje {
    public static void main(String[] args) {
        graf(-10, 10, -20, 2, 0.1);
    }

    public static void tarca(double stranica, int steviloKrogov) {
        StdDraw.setScale(-stranica / 2, stranica / 2);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.YELLOW);
        for (double i = 0; i < stranica / 2; i += stranica / Math.max((steviloKrogov * 2), 1)) {
            StdDraw.circle(0, 0, i);
        }
    }

    public static void barvniKvadratki() {
        StdDraw.setScale(0, 255);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                StdDraw.setPenColor(0, i, j);
                StdDraw.filledRectangle(i + 0.5, j + 0.5, 0.5, 0.5);
            }
        }
        StdDraw.show();
    }

    public static void kvadratnaSpirala() {
        StdDraw.setScale(-100, 100);
        int[][] smeri = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        int trSmer = 0;
        double trX = 0, trY = 0;
        for (double trDolzina = 0; trDolzina < 200; trDolzina += 2) {
            double nX = trX + smeri[trSmer][0] * trDolzina;
            double nY = trY + smeri[trSmer][1] * trDolzina;
            StdDraw.line(trX, trY, nX, nY);
            trX = nX;
            trY = nY;
            trSmer = (trSmer + 1) % 4;
        }
    }

    public static void nRoze(int n) {
        StdDraw.setScale(-100, 100);
        double dolzina = 10;
        double trX = 0, trY = 0;
        double kot = 0;
        double nX;
        double nY;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                nX = trX + Math.cos(Math.toRadians(kot)) * dolzina;
                nY = trY + Math.sin(Math.toRadians(kot)) * dolzina;
                StdDraw.line(trX, trY, nX, nY);
                kot += 360d / n;
                trX = nX;
                trY = nY;
            }
            nX = trX + Math.cos(Math.toRadians(kot)) * dolzina * 2;
            nY = trY + Math.sin(Math.toRadians(kot)) * dolzina * 2;
            StdDraw.line(trX, trY, nX, nY);
            trX = nX;
            trY = nY;
        }
    }

    public static void spirala() {
        StdDraw.setScale(-100, 100);
        double r = 0;
        double kot = 0;
        double trX = 0, trY = 0;
        for (int i = 0; i < 1000; i++) {
            double nX = r * Math.cos(Math.toRadians(kot));
            double nY = r * Math.sin(Math.toRadians(kot));
            StdDraw.line(trX, trY, nX, nY);
            trX = nX;
            trY = nY;
            r = 1.1 * Math.toRadians(kot);
            kot += 5;
        }
    }

    public static void radar() {
        StdDraw.setScale(-100, 100);
        double r = 50;
        double kot = 0;
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.enableDoubleBuffering();
        while (!StdDraw.hasNextKeyTyped() || StdDraw.nextKeyTyped() != ' ') {
            StdDraw.clear(Color.BLACK);
            for (int i = 0; i < 3; i++) {
                StdDraw.circle(0, 0, 10 + 20 * i);
            }
            kazalec(r, kot);
            kot -= 3;
            StdDraw.show();
            StdDraw.pause(1000 / 30);
        }
    }

    private static void kazalec(double r, double kot) {
        kazalec(r, kot, 0.002);
    }

    private static void kazalec(double r, double kot, double debelina) {
        StdDraw.setPenRadius(debelina);
        double x = r * Math.cos(Math.toRadians(kot));
        double y = r * Math.sin(Math.toRadians(kot));
        StdDraw.line(0, 0, x, y);
        StdDraw.setPenRadius();
    }

    public static void ura() {
        StdDraw.setScale(-100, 100);
        double r = 50;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime cajt;
        StdDraw.enableDoubleBuffering();
        while (!StdDraw.hasNextKeyTyped() || StdDraw.nextKeyTyped() != ' ') {
            StdDraw.clear();
            cajt = LocalDateTime.now();
            String cas = cajt.format(f);
            stevilcnica();
            StdDraw.text(-80, 90, cas);
            int SS = cajt.getNano();
            double ss = cajt.getSecond() + SS / 1000000000d;
            double mm = cajt.getMinute() + ss / 60;
            double hh = cajt.getHour() + mm / 60;
            kazalec(r - 15, -30 * hh + 90, 0.02);
            kazalec(r - 5, -6 * mm + 90, 0.01);
            StdDraw.setPenColor(Color.RED);
            kazalec(r, -6 * ss + 90);
            StdDraw.setPenColor();
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    private static void stevilcnica() {
        for (int i = 0; i < 360; i += 6) {
            if ((i - 90) % 30 == 0) {
                double x = Math.cos(Math.toRadians(i - 60));
                double y = -Math.sin(Math.toRadians(i - 60));
                StdDraw.text(x * 60, y * 60, Integer.toString(i / 30 + 1));
                StdDraw.setPenRadius(0.01);
            }
            double x = Math.cos(Math.toRadians(i));
            double y = -Math.sin(Math.toRadians(i));
            StdDraw.line(x * 55, y * 55, x * 50, y * 50);
            StdDraw.setPenRadius();
        }
    }

    public static void graf(double X1, double X2, double Y1, double Y2, double korak) {
        StdDraw.setScale(0, 200);
        StdDraw.clear();
        StdDraw.line(0, 200 * (-Y1) / (Y2 - Y1), 200, 200 * (-Y1) / (Y2 - Y1));
        StdDraw.line(200 * (-X1) / (X2 - X1), 0, 200 * (-X1) / (X2 - X1), 200);
        StdDraw.enableDoubleBuffering();
        double pj = 0;
        for (double i = 0; i <= 200; i += korak) {
            double x = (X2 - X1) * i / 200 + X1;
            double y = Math.log(x);
            double j = 200 * (y - Y1) / (Y2 - Y1);
            try {
                StdDraw.line(i - korak, pj, i, j);
            } catch (Exception ignored) {
            }
            pj = j;
        }
        StdDraw.show();
    }
}
