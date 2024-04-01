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
        switch (args[0]) {
            case "izpisi" -> izpisiSliko(preberiSliko(args[1]));
            case "histogram" -> histogram(preberiSliko(args[1]));
            case "svetlost" -> svetlostSlike(preberiSliko(args[1]), args[1]);
            case "zmanjsaj" -> izpisiSliko(zmanjsajSliko(preberiSliko(args[1])));
            case "rotiraj" -> izpisiSliko(rotirajSliko(preberiSliko(args[1])));
            case "zrcali" -> izpisiSliko(zrcaliSliko(preberiSliko(args[1])));
            case "vrstica" -> izpisiMaxVrstico(poisciMaxVrstico(preberiSliko(args[1])));
            case "barvna" -> izpisiBarvnoSliko(preberiBarvnoSliko(args[1]));
            case "sivinska" -> izpisiSliko(pretvoriVSivinsko(preberiBarvnoSliko(args[1])));
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

    private static int[][] zmanjsajSliko(int[][] slika) {
        if (slika.length < 3 || slika[0].length < 3) return slika;
        int[][] rez = new int[slika.length / 2][slika[0].length / 2];
        for (int i = 0; i < rez.length; i++) {
            for (int j = 0; j < rez[0].length; j++) {
                rez[i][j] = (slika[i * 2][j * 2] + slika[i * 2 + 1][j * 2] + slika[i * 2][j * 2 + 1] + slika[i * 2 + 1][j * 2 + 1]) / 4;
            }
        }
        return rez;
    }

    private static int[][] rotirajSliko(int[][] slika) {
        int[][] rez = new int[slika[0].length][slika.length];
        for (int i = 0; i < rez.length; i++) {
            for (int j = 0; j < rez[0].length; j++) {
                rez[rez.length - (i + 1)][j] = slika[j][i];
            }
        }
        return rez;
    }

    private static int[][] zrcaliSliko(int[][] slika) {
        int[][] rez = new int[slika.length][slika[0].length];
        for (int i = 0; i < rez.length; i++) {
            System.arraycopy(slika[i], 0, rez[rez.length - (i + 1)], 0, rez[0].length);
        }
        return rez;
    }

    private static int poisciMaxVrstico(int[][] slika) {
        int maxRazlika = 0;
        int maxVrstica = 0;
        for (int i = 0; i < slika[0].length; i++) {
            for (int[] stolpec1 : slika) {
                for (int[] stolpec2 : slika) {
                    if (Math.abs(stolpec1[i] - stolpec2[i]) > maxRazlika) {
                        maxVrstica = i;
                        maxRazlika = Math.abs(stolpec1[i] - stolpec2[i]);
                    }
                }
            }
        }
        return maxVrstica + 1;
    }

    private static void izpisiMaxVrstico(int vrstica) {
        System.out.printf("Max razlika svetlo - temno je v %d. vrstici.", vrstica);
    }

    private static int[][][] preberiBarvnoSliko(String ime) {
        int[][][] slika = null;
        try (Scanner sc = new Scanner(new File(ime))) {
            if (!sc.hasNextLine()) {
                System.out.println("Napaka: Datoteka " + ime + " je prazna.");
                System.exit(2);
            }
            String[] glava = sc.nextLine().split(" ");
            if (!glava[0].equals("P2B:") || !glava[2].equals("x")) {
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
            slika = new int[sirina][visina][3];
            for (int i = 0; i < slika[0].length; i++) {
                for (int j = 0; j < slika.length; j++) {
                    int naslednji = sc.nextInt();
                    int maska = 0b1111111111;
                    slika[j][i][0] = (naslednji >> 20) & maska;
                    slika[j][i][1] = (naslednji >> 10) & maska;
                    slika[j][i][2] = naslednji & maska;
                    if (slika[j][i][2] > 1023 || slika[j][i][2] < 0 ||
                            slika[j][i][1] > 1023 || slika[j][i][1] < 0 ||
                            slika[j][i][0] > 1023 || slika[j][i][0] < 0) {
                        System.out.println("Napaka: datoteka " + ime + " vsebuje podatke izven obsega 0 do 1023.");
                        System.exit(2);
                    }
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

    private static void izpisiBarvnoSliko(int[][][] slika) {
        System.out.printf("velikost slike: %d x %d\n", slika.length, slika[0].length);
        for (int i = 0; i < slika[0].length; i++) {
            for (int j = 0; j < slika.length; j++) {
                System.out.printf("(%4d,%4d,%4d)%s", slika[j][i][0], slika[j][i][1], slika[j][i][2], (j < slika.length - 1) ? " " : "");
            }
            System.out.println();
        }
    }

    private static int[][] pretvoriVSivinsko(int[][][] slika) {
        int[][] sivinska = new int[slika.length][slika[0].length];
        for (int i = 0; i < sivinska.length; i++) {
            for (int j = 0; j < sivinska[0].length; j++) {
                sivinska[i][j] = ((slika[i][j][0] + slika[i][j][1] + slika[i][j][2]) / 3) * 255 / 1023;
            }
        }
        return sivinska;
    }

}
