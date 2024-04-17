package vaje.igra;

import java.util.Random;

public class Logika {

    private static final int LEVO = 0, DESNO = 1, GOR = 2, DOL = 3;
    private static int VELIKOST;

    private static int[][] polja;

    private static int tocke;

    private static boolean konec;

    /**
     * Ustvari novo igralno polje s stranico velikost, gor postavi dve naključni ploščici in nastavi točke na 0, ter označi potek igre.
     *
     * @param velikost dolžina stranice igralne površine
     */
    public static void zacniNovoIgro(int velikost) {
        polja = new int[velikost][velikost];
        tocke = 0;
        konec = false;
        VELIKOST = velikost;
        postaviNakljucnoPloscico();
        postaviNakljucnoPloscico();
    }

    /**
     * Gre skozi vsa polja in če pride na prosto polje, poskusi z verjetnostjo 1/število_prostih_polj postaviti na to polje ploščico. Z verjetnostjo 90% bo ploščica vredna 2, z verjetnostjo 10% pa 4.
     */
    private static void postaviNakljucnoPloscico() {
        Random rnd = new Random();
        int prostaPolja = steviloProstihPolj();
        while (true) {
            for (int i = 0; i < VELIKOST; i++) {
                for (int j = 0; j < VELIKOST; j++) {
                    if (polja[i][j] == 0 && rnd.nextInt(prostaPolja) == 0) {
                        polja[i][j] = rnd.nextInt(10) < 9 ? 2 : 4;
                        return;
                    }
                }
            }
        }
    }


    /**
     * Prešteje število prostih polj na igralni površini.
     *
     * @return število prostih polj
     */
    private static int steviloProstihPolj() {
        int steviloProstih = 0;
        for (int[] vrstica : polja) {
            for (int ploscica : vrstica) {
                if (ploscica == 0)
                    steviloProstih++;
            }
        }
        return steviloProstih;
    }

    /**
     * Če je igra v teku, zaključi trenutni potek igre. Če ne poteka nobena igra, ustavi program.
     */
    public static void koncajIgro() {
        if (konec) {
            System.exit(0);
        }
        konec = true;
    }

    /**
     * Vrne vrednost ploščice na podanem mestu na igralni površini.
     *
     * @param i številka vrstice z iskano ploščico
     * @param j številka iskane ploščice v vrstici
     * @return vrednost iskane ploščice
     */
    public static int vrniPloscico(int i, int j) {
        return polja[i][j];
    }

    /**
     * Vrne dosežene točke.
     *
     * @return dosežene točke
     */
    public static int vrniTocke() {
        return tocke;
    }

    /**
     * Preveri, če je igralec ustvaril ploščico, vredno 2048, in s tem zmagal. Sicer ni še zmagal.
     *
     * @return ali je igralec že zmagal
     */
    public static boolean jeZmagal() {
        for (int[] vrstica : polja) {
            for (int ploscica : vrstica) {
                if (ploscica >= 2048)
                    return true;
            }
        }
        return false;
    }

    /**
     * Vrne stanje igre – true, če je zaključena, false, če igra še poteka.
     *
     * @return stanje igre
     */
    public static boolean jeKonec() {
        return konec;
    }


    /**
     * Premakne in združi vrstice v dano smer z rotiranjem površine, tako da je dana smer v levo. Nato rotira igralno površino nazaj na začetno smer in postavi naključno ploščico. Če premik ni več možen, konča igro.
     *
     * @param smer smer za premik in združevanje
     */
    public static void naslednjaPoteza(int smer) {
        boolean premik = false;
        boolean mozenPremik = false;
        for (int i = 0; i < 4; i++) {
            if (i == 0 && smer == LEVO) {
                premik = premakniInZdruzi();
            }
            if (i == 1 && smer == DOL) {
                premik = premakniInZdruzi();
            }
            if (i == 2 && smer == DESNO) {
                premik = premakniInZdruzi();
            }
            if (i == 3 && smer == GOR) {
                premik = premakniInZdruzi();
            }
            if (preveriMozenPremik()) mozenPremik = true;
            rotiraj();
        }
        if (premik) postaviNakljucnoPloscico();
        konec = !mozenPremik;
    }

    /**
     * Zarotira igralno polje (tabela polja) za 90 stopinj
     * v smeri urinega kazalca (v desno).
     * Rezultat metode je zarotirana tabela <code>polja</code>.
     */
    private static void rotiraj() {
        // najprej transponiramo tabelo – zamenjamo stolpce in vrstice
        for (int i = 0; i < VELIKOST; i++) {
            for (int j = i + 1; j < VELIKOST; j++) {
                int tmp = polja[i][j];
                polja[i][j] = polja[j][i];
                polja[j][i] = tmp;
            }
        }
        // Če rotiramo v desno (v smeri urinega kazalca), obrnemo še vrstni red stolpcev
        for (int i = 0; i < VELIKOST; i++) {
            for (int j = 0; j < VELIKOST / 2; j++) {
                int tmp = polja[i][j];
                polja[i][j] = polja[i][VELIKOST - 1 - j];
                polja[i][VELIKOST - 1 - j] = tmp;
            }
        }
    }

    /**
     * Gre skozi vse vrstice in združi in premakni vrstice v levo. Vrne true, če se je izvedel premik ali združevanje v katerikoli vrstici.
     *
     * @return ali se je zgodil premik
     */
    private static boolean premakniInZdruzi() {
        boolean premik = false;
        for (int i = 0; i < VELIKOST; i++) {
            if (premakniInZdruziVrstico(i)) {
                premik = true;
            }
        }
        return premik;
    }

    /**
     * Premakne in združi ploščice v dani vrstici v levo.
     *
     * @param vrstica številka vrstice, ki naj se premakne in združi v levo
     * @return ali se je zgodil premik ali združevanje
     */
    private static boolean premakniInZdruziVrstico(int vrstica) {
        boolean premik = false;
        for (int i = 0; i < VELIKOST; i++) {
            if (vrniPloscico(vrstica, i) == 0) {
                int mesto = i;
                for (int j = mesto; j < VELIKOST; j++) {
                    if (vrniPloscico(vrstica, j) != 0) {
                        premik = true;
                        polja[vrstica][mesto++] = vrniPloscico(vrstica, j);
                        polja[vrstica][j] = 0;
                    }
                }
            }
            for (int j = i + 1; j < VELIKOST; j++) {
                if (vrniPloscico(vrstica, j) == 0) {
                    continue;
                }
                if (vrniPloscico(vrstica, j) != vrniPloscico(vrstica, i)) {
                    break;
                }
                if (vrniPloscico(vrstica, j) == vrniPloscico(vrstica, i)) {
                    premik = true;
                    polja[vrstica][i] += vrniPloscico(vrstica, j);
                    polja[vrstica][j] = 0;
                    tocke += vrniPloscico(vrstica, i);
                }
            }
        }
        return premik;
    }

    /**
     * Preveri, če je možen premik vsaj ene vrstice v levo.
     *
     * @return ali je možen premik v levo.
     */
    private static boolean preveriMozenPremik() {
        for (int i = 0; i < VELIKOST; i++) {
            if (preveriMozenPremikVrstice(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Preveri, ali je možen premik ali združevanje dane vrstice v levo
     *
     * @param vrstica številka vrstice, pri kateri naj se preveri premik
     * @return ali je možen premik v levo
     */
    private static boolean preveriMozenPremikVrstice(int vrstica) {
        for (int i = 0; i < VELIKOST; i++) {
            if (vrniPloscico(vrstica, i) == 0) {
                return true;
            }
            for (int j = i + 1; j < VELIKOST; j++) {
                if (vrniPloscico(vrstica, j) == 0) {
                    continue;
                }
                if (vrniPloscico(vrstica, j) != vrniPloscico(vrstica, i)) {
                    break;
                }
                if (vrniPloscico(vrstica, j) == vrniPloscico(vrstica, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
