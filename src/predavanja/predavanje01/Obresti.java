package predavanja.predavanje01;

import java.util.Scanner;

public class Obresti {
    public static void main(String[] args) {
        double obrestnaMera;
        double steviloLet;
        double glavnica;
        double koncniZnesek;
        Scanner sc = new Scanner(System.in);
        System.out.print("Obrestna mera: ");
        obrestnaMera = sc.nextDouble();
        System.out.print("Število let: ");
        steviloLet = sc.nextDouble();
        System.out.print("Glavnica: ");
        glavnica = sc.nextDouble();
        koncniZnesek = glavnica * Math.pow(1+obrestnaMera/100, steviloLet);
        System.out.println("-----------------------");
        System.out.printf("Končni znesek: %.10f\n", koncniZnesek);
        sc.close();
    }
}
