package kvizi;


public class Kviz1 {
    public static void main(String[] args){
        jeFibonaccijevo(34);
    }
    static void java(){
        System.out.println("   J    a   v     v  a\n"
                + "   J   a a   v   v  a a\n"
                + "J  J  aaaaa   V V  aaaaa\n"
                + " JJ  a     a   V  a     a");
    }

    static void kalkulator(int a, int b){
        if (b != 0) {
            System.out.println(a + " + " + b + " = " + (a + b));
            System.out.println(a + " - " + b + " = " + (a - b));
            System.out.println(a + " x " + b + " = " + (a * b));
            System.out.println(a + " / " + b + " = " + (int)(a / b));
            System.out.println(a + " % " + b + " = " + (a % b));
        }
        else {
            System.out.println("Napaka: deljenje z 0");
        }
    }

    static void nicli(int a, int b, int c){
        if (b % a == 0 && c % a == 0) {
            b /= a;
            c /= a;
            a = 1;
        }
        double D = Math.pow(b, 2) - 4 * a * c;
        if (D < 0){
            System.out.println("Napaka: nicli enacbe ne obstajata");
        }
        else {
            double x1 = (-b + Math.sqrt(D)) / 2;
            double x2 = (-b - Math.sqrt(D)) / 2;
            System.out.println("x1=" + String.format("%.2f", x1)
                            + ", x2=" + String.format("%.2f", x2));
        }
    }

    static void krog(double r, int d){
        if (r < 0){
            System.out.println("Napaka: negativen polmer");
        } else if (d < 0) {
            System.out.println("Napaka: negativen d");
        }
        else {
            double obseg = 2 * Math.PI * r;
            double ploscina = Math.PI * Math.pow(r, 2);
            System.out.println("Obseg kroga s polmerom r="
                            + String.format("%.2f", r) + " je "
                            + String.format("%." + d + "f", obseg));
            System.out.println("Ploscina kroga s polmerom r="
                    + String.format("%.2f", r) + " je "
                    + String.format("%." + d + "f", ploscina));
        }
    }

    static String pretvoriSekunde(int sekunde){
        if (sekunde < 0){
            return "Število sekund ne more biti negativno";
        }
        int minute = 0;
        int ure = 0;
        if (sekunde > 60){
            minute = sekunde / 60;
            sekunde %= 60;
        }
        if (minute > 60) {
            ure = minute / 60;
            minute %= 60;
        }
        return String.format("%02d", ure) + ":"
                + String.format("%02d", minute) + ":"
                + String.format("%02d", sekunde);
    }

    static void javaJavaJava(int n){
        String vrstica1 = "     J    a   v     v  a   ";
        String vrstica2 = "     J   a a   v   v  a a  ";
        String vrstica3 = "  J  J  aaaaa   V V  aaaaa ";
        String vrstica4 = "   JJ  a     a   V  a     a";
        if (n < 0){
            System.out.println("Napaka: negativen n");
            return;
        }
        System.out.println(vrstica1.repeat(n));
        System.out.println(vrstica2.repeat(n));
        System.out.println(vrstica3.repeat(n));
        System.out.println(vrstica4.repeat(n));
    }
    static boolean jeFibonaccijevo(int n){
        int f = 1;
        int fP = 1;
        while (f <= n){
            int temp = fP;
            fP = f;
            f += temp;
            if (f == n) {return true;}
        }
        return false;
    }
}
