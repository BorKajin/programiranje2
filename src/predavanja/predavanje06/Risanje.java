package predavanja.predavanje06;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Risanje {
    public static void main(String[] args) {
        graf(-Math.PI * 2, Math.PI * 2, -2, 2);
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
        double x = r * Math.cos(Math.toRadians(kot));
        double y = r * Math.sin(Math.toRadians(kot));
        StdDraw.line(0, 0, x, y);
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
            for (int i = 1; i <= 12; i++) {
                double x = Math.cos(Math.toRadians(i * 30 - 90));
                double y = -Math.sin(Math.toRadians(i * 30 - 90));
                StdDraw.text(x * 60, y * 60, Integer.toString(i));
            }
            for (int i = 1; i <= 60; i++) {
                if (i % 5 == 0) {
                    StdDraw.setPenRadius(0.01);
                } else {
                    StdDraw.setPenRadius();
                }
                double x = Math.cos(Math.toRadians(i * 6));
                double y = -Math.sin(Math.toRadians(i * 6));
                StdDraw.line(x * 55, y * 55, x * 50, y * 50);
            }
            StdDraw.text(-80, 90, cas);
            StdDraw.setPenRadius(0.02);
            kazalec(r - 15, -30 * Integer.parseInt(cas.split(":")[0]) + 90);
            StdDraw.setPenRadius(0.01);
            kazalec(r - 5, -6 * Integer.parseInt(cas.split(":")[1]) + 90);
            StdDraw.setPenRadius();
            StdDraw.setPenColor(Color.RED);
            kazalec(r, -6 * Integer.parseInt(cas.split(":")[2]) + 90);
            StdDraw.setPenColor();
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }

    public static void graf(double X1, double X2, double Y1, double Y2) {
        StdDraw.setScale(0, 200);
        StdDraw.clear();
        StdDraw.line(0, 200 * (-Y1) / (Y2 - Y1), 200, 200 * (-Y1) / (Y2 - Y1));
        StdDraw.line(200 * (-X1) / (X2 - X1), 0, 200 * (-X1) / (X2 - X1), 200);
        StdDraw.enableDoubleBuffering();
        for (double i = 0; i < 200; i += 0.001) {
            double x = (X2 - X1) * i / 200 + X1;
            double y = Math.cos(x);
            double j = 200 * (y - Y1) / (Y2 - Y1);
            try {
                StdDraw.line(i, j, i, j);

            } catch (Exception ignored) {
            }
        }
        StdDraw.show();
    }
}
