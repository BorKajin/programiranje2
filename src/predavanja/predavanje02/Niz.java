package predavanja.predavanje02;

import java.util.Scanner;

public class Niz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vpisi niz: ");
        String niz = sc.nextLine();
        StringBuilder sb = new StringBuilder(niz);
        System.out.printf("Prva črka v nizu:   %c\n", niz.charAt(0));
        System.out.printf("Zadnja črka v nizu: %c\n", niz.charAt(niz.length()-1));
        System.out.printf("Brez presledkov:    %s\n", niz.replaceAll(" ", ""));
        System.out.printf("Število besed:      %d\n", niz.split(" +").length);
        System.out.printf("Celotna dolžina:    %d\n", niz.length());
        System.out.printf("Obrnjen niz:        %s\n", sb.reverse().toString());

    }
}
