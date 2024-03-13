package vaje;

public class V02_Fakulteta {
    public static void main(String[] args) {
        primerjavaL(10);
        primerjavaD(10);
        primerjajPi(10);
    }
    static long fakultetaL(int n) {
        long rezultat = 1;
        for (int i = 1; i <= n; i++) {
            rezultat *= i;
        }
        return rezultat;
    }

    static long stirlingL(int n) {
        return Math.round(Math.sqrt(2 * Math.PI * n)*Math.pow(n / Math.E, n));
    }
    static void primerjavaL(int n) { //Največ 20, potem overflow
        System.out.printf("%3s  %9s%10s  %15s%4s  %10s\n", "n", "n!", " ", "Stirling(n)", " ", "Napaka (%)");
        System.out.println("-".repeat(57));
        for (int i = 1; i < n; i++) {
            System.out.printf("%3d  %19d  %19d  %10.7f\n", i, fakultetaL(i), stirlingL(i),
                    1 - (double) stirlingL(i)/fakultetaL(i));
        }
        System.out.println("-".repeat(57));
    }

    static double fakultetaD(int n) {
        double rezultat = 1.0d;
        for (int i = 1; i <= n; i++) {
            rezultat *= i;
        }
        return rezultat;
    }

    static double stirlingD(int n) {
        return Math.sqrt(2 * Math.PI * n)*Math.pow(n / Math.E, n);
    }

    static void primerjavaD(int n) { //Največ 20, potem overflow
        System.out.printf("%3s  %9s%6s  %15s  %10s\n", "n", "n!", " ", "Stirling(n)", "Napaka (%)");
        System.out.println("-".repeat(49));
        for (int i = 1; i < n; i++) {
            System.out.printf("%3d  %15e  %15e  %10.7f\n", i, fakultetaD(i), stirlingD(i),
                    1 - stirlingD(i)/fakultetaD(i));
        }
        System.out.println("-".repeat(49));
    }

    static double izracunajPiNilakantha(int k) {
        double rezultat = 3;
        int stevka = 2;
        for (int i = 2; i < k; i++) {
            rezultat += Math.pow(-1,i) * 4/(stevka++*stevka++*stevka);
        }
        return rezultat;
    }

    static void primerjajPi(int k) {
        System.out.printf("%3s  %10s%7s  %14s  %16s\n", "k", "Math.PI", " ", "Nilakanth(k)", "razlika");
        System.out.println("-".repeat(61));
        for (int i = 1; i < k; i++) {
            System.out.printf("%3d  %17.15f  %17.15f  %18.15f\n", i, Math.PI, izracunajPiNilakantha(i),
                    Math.PI-izracunajPiNilakantha(i));
        }
        System.out.println("-".repeat(61));
    }
}
