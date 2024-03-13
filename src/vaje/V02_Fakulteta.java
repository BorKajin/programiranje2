package vaje;

public class V02_Fakulteta {
    public static void main(String[] args) {
        primerjavaL(20);
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
        System.out.printf("%3s  %19s  %19s  %10s\n", "n", "n!", "Stirling(n)", "Napaka (%)");
        for (int i = 1; i < n; i++) {
            System.out.printf("%3d  %19d  %19d  %10.7f\n", i, fakultetaL(i), stirlingL(i),
                    1 - (double) stirlingL(i)/fakultetaL(i));
        }
    }
}
