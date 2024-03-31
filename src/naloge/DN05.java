package naloge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DN05 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.exit(1);
        }
        int[][] slika = preberiSliko(args[1]);
        switch (args[0]) {
            case "izpisi" -> izpisiSliko(slika);
            case "histogram" -> histogram(slika);
            case "svetlost" -> svetlostSlike(slika, args[1]);
        }

    }


    private static int[][] preberiSliko(String ime) {
        int[][] slika = null;
        try (Scanner sc = new Scanner(new File(ime))) {
            if (!sc.hasNextLine()) {
                System.out.println("Napaka: Datoteka " + ime + " je prazna.");
                System.exit(2);
            }
            String[] glava = sc.nextLine().split(" ");
            if (!glava[0].equals("P2:") || !glava[2].equals("x")) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2.");
                System.exit(2);
            }
            int sirina = 0, visina = 0;
            try {
                sirina = Integer.parseInt(glava[1]);
                visina = Integer.parseInt(glava[3]);
            } catch (NumberFormatException e) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2 (velikost slike ni pravilna).");
                System.exit(2);
            }
            if (sirina <= 0 || visina <= 0) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2 (velikost slike je 0 ali negativna).");
                System.exit(2);
            }
            slika = new int[sirina][visina];
            for (int i = 0; i < slika[0].length; i++) {
                for (int j = 0; j < slika.length; j++) {
                    int naslednji = sc.nextInt();
                    if (naslednji < 0 || naslednji > 255) {
                        System.out.println("Napaka: datoteka " + ime + " vsebuje podatke izven obsega 0 do 255.");
                        System.exit(2);
                    }
                    slika[j][i] = naslednji;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Napaka: datoteka " + ime + " ne obstaja.");
            System.exit(2);
        } catch (NoSuchElementException e) {
            System.out.println("Napaka: datoteka " + ime + " vsebuje premalo podatkov.");
            System.exit(2);
        }
        return slika;
    }

    private static void izpisiSliko(int[][] slika) {
        System.out.printf("velikost slike: %d x %d\n", slika.length, slika[0].length);
        for (int i = 0; i < slika[0].length; i++) {
            for (int j = 0; j < slika.length; j++) {
                System.out.printf("%3d%s", slika[j][i], (j < slika.length - 1) ? " " : "");
            }
            System.out.println();
        }
    }

    private static int[] steviloSivin(int[][] slika) {
        int[] sivine = new int[256];
        for (int[] vrstica : slika) {
            for (int piksel : vrstica) {
                sivine[piksel]++;
            }
        }
        return sivine;
    }

    private static void histogram(int[][] slika) {
        int[] sivine = steviloSivin(slika);
        System.out.println("sivina : # pojavitev");
        for (int i = 0; i < sivine.length; i++) {
            if (sivine[i] > 0) {
                System.out.printf("%5d  :   %d\n", i, sivine[i]);
            }
        }
    }

    private static void svetlostSlike(int[][] slika, String ime) {
        int[] sivine = steviloSivin(slika);
        double povprecje = 0;
        for (int i = 0; i < sivine.length; i++) {
            povprecje += i * sivine[i];
        }
        povprecje /= slika.length * slika[0].length;
        System.out.printf("Srednja vrednost sivine na sliki %s je: %.2f", ime, povprecje);
    }

}
