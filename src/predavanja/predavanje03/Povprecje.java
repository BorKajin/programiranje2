package predavanja.predavanje03;

import java.util.Scanner;

public class Povprecje {
    public static void main(String[] args) {
        povprecje();
    }
    private static void povprecje() {
        Scanner sc = new Scanner(System.in);
        int steviloVnosov = -1;
        int vsota = 0;
        int x;
        do{
            System.out.print("Vpisi oceno:");
            x = sc.nextInt();
            vsota += x;
            steviloVnosov++;
        }while (x != 0);
        sc.close();
        double povprecje = 0;
        if (steviloVnosov != 0) {
            povprecje = (double) vsota /steviloVnosov;
        }
        System.out.printf("Povprecje %d prebranih ocen je %.2f",steviloVnosov,povprecje);
    }
}
