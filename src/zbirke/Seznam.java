package zbirke;

public class Seznam {

    private static String[] seznam = null;
    private static int mestoVSeznamu = -1;

    /**
     * Ustvari prazen seznam s podano dolžino, če seznam še ne obstaja.
     *
     * @param n kapaciteta seznama
     * @return true, če je ustvarjen; false, če že obstaja
     */
    public static boolean narediSeznam(int n) {
        if (seznam != null)
            return false;
        seznam = new String[n];
        mestoVSeznamu = 0;
        return true;
    }

    /**
     * Doda podan element na konec seznama. Če je seznam že poln, ga podaljša.
     *
     * @param element Element, ko bo dodan
     * @return true, če je element dodan; false, če seznam ne obstaja;
     */
    public static boolean dodajNaKonecSeznama(String element) {
        if (seznam == null)
            return false;
        if (mestoVSeznamu >= seznam.length) {
            String[] rez = new String[Math.max(seznam.length, 1) * 2];
            System.arraycopy(seznam, 0, rez, 0, seznam.length);
            seznam = rez;
        }
        seznam[mestoVSeznamu++] = element;
        return true;
    }

    /**
     * Vrine podan element na podano mesto v seznamu.
     *
     * @param element Element, ki bo dodan
     * @param mesto   mesto, na katero naj bo dodan element
     * @return true, če je element vrinjen; false, če element ni vrinjen
     */
    public static boolean dodajVSeznam(String element, int mesto) {
        if (seznam == null || mestoVSeznamu == seznam.length || mesto < 1)
            return false;
        if (mesto >= mestoVSeznamu) {
            return dodajNaKonecSeznama(element);
        }
        for (int i = mestoVSeznamu - 1; i > mesto - 1; i--) {
            seznam[i] = seznam[i - 1];
        }
        mestoVSeznamu++;
        seznam[mesto - 1] = element;
        return true;
    }

    /**
     * Odstrani element na podanem mestu in ga vrne.
     *
     * @param mesto mesto elementa, ki bo odstranjen
     * @return odstranjen element; null, če element ni bil odstranjen
     */
    public static String odstraniIzSeznama(int mesto) {
        if (seznam == null || --mesto >= mestoVSeznamu)
            return null;
        String element = seznam[mesto];
        for (int i = mesto; i < mestoVSeznamu - 1; i++) {
            seznam[i] = seznam[i + 1];
        }
        mestoVSeznamu--;
        return element;
    }

    /**
     * Vrne število elementov v seznamu
     *
     * @return število elementov v seznamu; -1, če seznam ne obstaja
     */
    public static int dolzinaSeznama() {
        return mestoVSeznamu;
    }

    /**
     * Izpiše elemente seznama, če obstaja in vsebuje elemente.
     */
    public static void izpisiSeznam() {
        if (seznam == null) {
            System.out.println("NAPAKA: Seznam ne obstaja.");
            return;
        }
        if (mestoVSeznamu == 0) {
            System.out.println("Seznam je prazen (nima elementov).");
            return;
        }
        System.out.println("Na seznamu so naslednji elementi:");
        for (int i = 0; i < mestoVSeznamu; i++) {
            System.out.printf("%d: %s\n", i + 1, seznam[i]);
        }
    }

    /**
     * Izpiše elemente seznama z uporabo 64-bitnih grafičnih znakov, če obstaja in vsebuje elemente.
     */
    public static void izpisiSeznam64Bit() {
        if (seznam == null) {
            izpis.Znaki.izpisi64bit("NAPAKA: Seznam ne obstaja.");
            return;
        }
        if (mestoVSeznamu == 0) {
            izpis.Znaki.izpisi64bit("Seznam je prazen (nima elementov).");
            return;
        }
        izpis.Znaki.izpisi64bit("Na seznamu so naslednji elementi:");
        for (int i = 0; i < mestoVSeznamu; i++) {
            izpis.Znaki.izpisi64bit((i + 1) + ". " + seznam[i]);
        }
    }

    /**
     * Uniči seznam, če ta obstaja
     *
     * @return true, če je seznam uničen; sicer false
     */
    public static boolean uniciSeznam() {
        if (seznam == null)
            return false;
        seznam = null;
        mestoVSeznamu = -1;
        return true;
    }
}
