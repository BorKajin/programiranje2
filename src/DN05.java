import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DN05 {

    public static void main(String[] args) throws FileNotFoundException {
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
            case "vrstica" -> poisciMaxVrstico(izracunajMaxVrstico(preberiSliko(args[1])));
            case "barvna" -> izpisiBarvnoSliko(preberiBarvnoSliko(args[1]));
            case "sivinska" -> izpisiSliko(pretvoriVSivinsko(preberiBarvnoSliko(args[1])));
            case "uredi" -> preberiVseInIzpisi(Arrays.copyOfRange(args, 1, args.length));
            case "jedro" -> konvolucijaJedro(preberiSliko(args[1]));
            case "glajenje" -> konvolucijaGlajenje(preberiSliko(args[1]));
            case "robovi" -> konvolucijaRobovi(preberiSliko(args[1]));
        }
    }

    private static int[][] preberiSliko(String ime) {
        int[][] slika = null;
        try (Scanner sc = new Scanner(new File(ime))) {
            if (!sc.hasNextLine()) {
                System.out.println("Napaka: Datoteka " + ime + " je prazna.");
                sc.close();
                System.exit(2);
            }
            String[] glava = sc.nextLine().split(" ");
            if (!glava[0].equals("P2:") || !glava[2].equals("x")) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2.");
                sc.close();
                System.exit(2);
            }
            int sirina = 0, visina = 0;
            try {
                sirina = Integer.parseInt(glava[1]);
                visina = Integer.parseInt(glava[3]);
            } catch (NumberFormatException e) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2 (velikost slike ni pravilna).");
                sc.close();
                System.exit(2);
            }
            if (sirina <= 0 || visina <= 0) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2 (velikost slike je 0 ali negativna).");
                sc.close();
                System.exit(2);
            }
            slika = new int[sirina][visina];
            for (int i = 0; i < slika[0].length; i++) {
                for (int j = 0; j < slika.length; j++) {
                    int naslednji = sc.nextInt();
                    if (naslednji < 0 || naslednji > 255) {
                        System.out.println("Napaka: datoteka " + ime + " vsebuje podatke izven obsega 0 do 255.");
                        sc.close();
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

    private static double izracunajSvetlost(int[][] slika) {
        int[] sivine = steviloSivin(slika);
        double povprecje = 0;
        for (int i = 0; i < sivine.length; i++) {
            povprecje += i * sivine[i];
        }
        povprecje /= slika.length * slika[0].length;
        return povprecje;
    }

    public static void svetlostSlike(int[][] slika, String ime) {
        System.out.printf("Srednja vrednost sivine na sliki %s je: %.2f", ime, izracunajSvetlost(slika));
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

    private static int izracunajMaxVrstico(int[][] slika) {
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

    private static void poisciMaxVrstico(int vrstica) {
        System.out.printf("Max razlika svetlo - temno je v %d. vrstici.", vrstica);
    }

    private static int[][][] preberiBarvnoSliko(String ime) {
        int[][][] slika = null;
        try (Scanner sc = new Scanner(new File(ime))) {
            if (!sc.hasNextLine()) {
                System.out.println("Napaka: Datoteka " + ime + " je prazna.");
                sc.close();
                System.exit(2);
            }
            String[] glava = sc.nextLine().split(" ");
            if (!glava[0].equals("P2B:") || !glava[2].equals("x")) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2.");
                sc.close();
                System.exit(2);
            }
            int sirina = 0, visina = 0;
            try {
                sirina = Integer.parseInt(glava[1]);
                visina = Integer.parseInt(glava[3]);
            } catch (NumberFormatException e) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2 (velikost slike ni pravilna).");
                sc.close();
                System.exit(2);
            }
            if (sirina <= 0 || visina <= 0) {
                System.out.println("Napaka: datoteka " + ime + " ni v formatu P2 (velikost slike je 0 ali negativna).");
                sc.close();
                System.exit(2);
            }
            slika = new int[sirina][visina][3];
            for (int i = 0; i < slika[0].length; i++) {
                for (int j = 0; j < slika.length; j++) {
                    int naslednji = sc.nextInt();
                    slika[j][i][0] = (naslednji >> 20) & 1023;
                    slika[j][i][1] = (naslednji >> 10) & 1023;
                    slika[j][i][2] = naslednji & 1023;
                    if (slika[j][i][2] > 1023 || slika[j][i][2] < 0
                            || slika[j][i][1] > 1023 || slika[j][i][1] < 0
                            || slika[j][i][0] > 1023 || slika[j][i][0] < 0) {
                        System.out.println("Napaka: datoteka " + ime + " vsebuje podatke izven obsega 0 do 1023.");
                        sc.close();
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
                sivinska[i][j] = (int) Math.round((((slika[i][j][0] + slika[i][j][1] + slika[i][j][2]) / 3d) * (255 / 1023d)));
            }
        }
        return sivinska;
    }

    private static void preberiVseInIzpisi(String[] imenaSlik) {
        int[] svetlosti = new int[imenaSlik.length];
        for (int i = 0; i < svetlosti.length; i++) {
            svetlosti[i] = (int) Math.round(izracunajSvetlost(preberiSliko(imenaSlik[i])));
        }
        for (int i = 0; i < svetlosti.length; i++) {
            for (int j = 0; j < i; j++) {
                if (svetlosti[i] > svetlosti[j] || (svetlosti[i] == svetlosti[j] && imenaSlik[i].compareToIgnoreCase(imenaSlik[j]) < 0)) {
                    String tempIme = imenaSlik[i];
                    int tempSvetlost = svetlosti[i];
                    svetlosti[i] = svetlosti[j];
                    imenaSlik[i] = imenaSlik[j];
                    imenaSlik[j] = tempIme;
                    svetlosti[j] = tempSvetlost;
                }
            }
        }
        for (int i = 0; i < svetlosti.length; i++) {
            System.out.printf("%s (%d)\n", imenaSlik[i], svetlosti[i]);
        }
    }

    private static int[][] konvolucija(int[][] slika, double[][] jedro) {
        int[][] rez = new int[slika.length - 2][slika[0].length - 2];
        for (int i = 1; i < slika.length - 1; i++) {
            for (int j = 1; j < slika[0].length - 1; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        rez[i - 1][j - 1] += (int) Math.round(slika[i + k - 1][j + l - 1] * jedro[k][l]);
                    }
                }
            }
        }
        return rez;
    }

    private static void konvolucijaJedro(int[][] slika) {
        izpisiSliko(konvolucija(slika, new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    private static int[][] razsiriSliko(int[][] slika) {
        int[][] rez = new int[slika.length + 2][slika[0].length + 2];
        for (int i = 0; i < slika.length; i++) {
            System.arraycopy(slika[i], 0, rez[i + 1], 1, slika[0].length);
        }
        System.arraycopy(slika[0], 0, rez[0], 1, slika[0].length);
        System.arraycopy(slika[slika.length - 1], 0, rez[rez.length - 1], 1, slika[0].length);
        for (int i = 0; i < rez.length; i++) {
            rez[i][0] = rez[i][1];
            rez[i][rez[0].length - 1] = rez[i][rez[0].length - 2];
        }
        return rez;
    }

    private static void konvolucijaGlajenje(int[][] slika) {
        izpisiSliko(konvolucija(razsiriSliko(slika), new double[][]{{1 / 16d, 1 / 8d, 1 / 16d}, {1 / 8d, 1 / 4d, 1 / 8d}, {1 / 16d, 1 / 8d, 1 / 16d}}));
    }

    private static void konvolucijaRobovi(int[][] slika) {
        int[][] roboviNavpicno = konvolucija(razsiriSliko(slika), new double[][]{{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}});
        int[][] roboviVodoravno = konvolucija(razsiriSliko(slika), new double[][]{{1, 0, -1}, {2, 0, -2}, {1, 0, -1}});
        int[][] roboviSkupaj = new int[slika.length][slika[0].length];
        int[][] roboviKoncni = new int[slika.length][slika[0].length];
        int maxVrednost = 0;
        for (int i = 0; i < roboviSkupaj.length; i++) {
            for (int j = 0; j < roboviSkupaj[0].length; j++) {
                roboviSkupaj[i][j] = (int) Math.round(Math.sqrt(Math.pow(roboviNavpicno[i][j], 2) + Math.pow(roboviVodoravno[i][j], 2)));
                if (roboviSkupaj[i][j] > maxVrednost)
                    maxVrednost = roboviSkupaj[i][j];
            }
        }
        for (int i = 0; i < roboviSkupaj.length; i++) {
            for (int j = 0; j < roboviSkupaj[0].length; j++) {
                roboviKoncni[i][j] = (int) Math.round(roboviSkupaj[i][j] * 255d / maxVrednost);
            }
        }
        izpisiSliko(roboviKoncni);
    }

}
