package vaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Besedle {

    // Konstante barv
    static final int BELA = 0;
    static final int CRNA = 1;
    static final int RUMENA = 2;
    static final int ZELENA = 3;

    // ANSI ukazi (za barvni izpis)
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN_BG = "\u001b[42m";
    static final String ANSI_YELLOW_BG = "\u001b[43m";
    static final String ANSI_WHITE_BG = "\u001b[47;1m";
    static final String ANSI_BLACK_BG = "\u001b[40m";
    static final String ANSI_WHITE = "\u001b[37m";
    static final String ANSI_BLACK = "\u001b[30m";

    static final String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ"; // Veljavne črke
    static final int MAX_POSKUSOV = 6; // Število poskusov

    static String[] seznamBesed; // Seznam vseh možnih besed
    static String[] slovar;
    static String iskanaBeseda; // Iskana beseda trenutne igre
    static int[] barveAbecede; // Barve črk pri izpisu abecede

    static Scanner sc = new Scanner(System.in);

    // Izpiše znak v izbrani barvi
    static void izpisiZBarvo(char znak, int barva) {
        String slog;
        if (barva == ZELENA) {
            slog = ANSI_BLACK + ANSI_GREEN_BG;
        } else if (barva == RUMENA) {
            slog = ANSI_BLACK + ANSI_YELLOW_BG;
        } else if (barva == BELA) {
            slog = ANSI_BLACK + ANSI_WHITE_BG;
        } else {
            slog = ANSI_WHITE + ANSI_BLACK_BG;
        }
        System.out.print(slog + " " + znak + " " + ANSI_RESET);
    }

    // Prebere seznam besed iz datoteke
    static void preberiBesede(String datoteka) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(datoteka))) {
            seznamBesed = new String[sc.nextInt()];
            for (int i = 0; i < seznamBesed.length; i++) {
                seznamBesed[i] = sc.nextLine().toUpperCase();
            }
        }
    }

    static void preberiSlovar(String datoteka) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(datoteka))) {
            slovar = new String[sc.nextInt()];
            for (int i = 0; i < slovar.length; i++) {
                slovar[i] = sc.nextLine().toUpperCase();
            }
        }
    }

    // Pripravi vse za novo igro
    static void novaIgra() {
        Random rnd = new Random();
        iskanaBeseda = seznamBesed[rnd.nextInt(seznamBesed.length)];
        barveAbecede = new int[abeceda.length()];

    }

    // Izpiše abecedo
    static void izpisiAbecedo() {
        for (int i = 0; i < abeceda.length(); i++) {
            izpisiZBarvo(abeceda.charAt(i), barveAbecede[i]);
        }
        System.out.println();
    }

    // Ali je beseda veljavna?
    static boolean veljavnaBeseda(String beseda) {
        if (beseda.length() != 5) {
            System.out.println("Nepravilna dolžina besede");
            return false;
        }
        for (int i = 0; i < 5; i++) {
            boolean najden = false;
            for (int j = 0; j < abeceda.length(); j++) {
                if (beseda.charAt(i) == abeceda.charAt(j)) {
                    najden = true;
                    break;
                }
            }
            if (!najden) {
                System.out.println("V besedi so neveljavni znaki!");
                return false;
            }
        }
        boolean najden = false;
        for (String s : slovar) {
            if (s.equals(beseda)) {
                najden = true;
                break;
            }
        }
        if (!najden) {
            System.out.println("Besede ni v slovarju!");
            return false;
        }
        return true;
    }

    // Določi barve črk v ugibani besedi
    static int[] pobarvajBesedo(String ugibanaBeseda) {
        int[] barve = new int[5];
        for (int i = 0; i < ugibanaBeseda.length(); i++) {
            int barva = CRNA;
            char znak = ugibanaBeseda.charAt(i);
            if (znak == iskanaBeseda.charAt(i))
                barva = ZELENA;
            for (int j = 0; j < iskanaBeseda.length() && barva == CRNA; j++) {
                if (znak == iskanaBeseda.charAt(j)) {
                    barva = RUMENA;
                    break;
                }
            }
            barve[i] = barva != CRNA ? barva : BELA;
            for (int j = 0; j < abeceda.length(); j++) {
                if (abeceda.charAt(j) == znak) {
                    barveAbecede[j] = barva;
                    break;
                }
            }
        }
        return barve;
    }

    static int[] pravilnoPobarvajBesedo(String ugibanaBeseda) {
        StringBuilder beseda = new StringBuilder(iskanaBeseda);
        int[] barve = new int[5];
        for (int i = 0; i < beseda.length(); i++) {
            int barva = CRNA;
            char znak = ugibanaBeseda.charAt(i);
            if (znak == beseda.charAt(i)) {
                barva = ZELENA;
                beseda.setCharAt(i, '_');
            }
            for (int j = 0; j < iskanaBeseda.length() && barva == CRNA; j++) {
                if (znak == beseda.charAt(j)) {
                    barva = RUMENA;
                    beseda.setCharAt(j, '_');
                    break;
                }
            }
            barve[i] = barva != CRNA ? barva : BELA;
            for (int j = 0; j < abeceda.length(); j++) {
                if (abeceda.charAt(j) == znak) {
                    barveAbecede[j] = barva;
                    break;
                }
            }
        }
        return barve;
    }

    // Izpiši besedo
    static void izpisiBesedo(String ugibanaBeseda, int[] barve) {
        for (int i = 0; i < ugibanaBeseda.length(); i++) {
            izpisiZBarvo(ugibanaBeseda.charAt(i), barve[i]);
        }
        System.out.println();
    }


    // Izvede eno igro
    static void igra() {
        novaIgra();

        int poskus = 1;
        boolean uganil = false;
        while (poskus <= MAX_POSKUSOV) {
            izpisiAbecedo();
            System.out.printf("Poskus %d/%d: ", poskus, MAX_POSKUSOV);
            String ugibanaBeseda = sc.nextLine().toUpperCase();

            // Preveri veljavnost
            if (!veljavnaBeseda(ugibanaBeseda))
                continue;

            // Pobarvaj crke v besedi (namigi)
            int[] barve = pravilnoPobarvajBesedo(ugibanaBeseda);

            // Izpiši pobarvano besedo
            izpisiBesedo(ugibanaBeseda, barve);

            if (ugibanaBeseda.equals(iskanaBeseda)) {
                uganil = true;
                break;
            }
            poskus++;
        }

        if (uganil) {
            System.out.printf("Bravo! Besedo si uganil/a v %d poskusih.\n", poskus);
        } else {
            System.out.printf("Žal ti ni uspelo. Pravilna beseda: %s\n", iskanaBeseda);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        preberiBesede("viri/besede.txt");
        preberiSlovar("viri/slovar.txt");

        while (true) {
            igra();
            System.out.print("Nova igra? (d/n): ");
            String odg = sc.nextLine();
            if (odg.toLowerCase().charAt(0) != 'd') {
                break;
            }
        }
        sc.close();
    }
}
