package predavanja.predavanje04;

@SuppressWarnings("unused")
public class MnozicaZnakov {

    public static int getPraznaMnozica() {
        return 0;
    }

    public static int dodajElement(int mnozica, char... znaki) {
        for (char znak : znaki) {
            mnozica |= getBit(znak);
        }
        return mnozica;
    }

    public static int getBit(char znak) {
        return 1 << (znak - 'a');
    }

    public static int presek(int... mnozice) {
        int rez = mnozice.length != 0 ? Integer.MAX_VALUE : 0;
        for (int mnozica : mnozice) {
            rez &= mnozica;
        }
        return rez;
    }

    public static int unija(int... mnozice) {
        int rez = getPraznaMnozica();
        for (int mnozica : mnozice) {
            rez |= mnozica;
        }
        return rez;
    }

    public static String toString(int mnozica) {
        StringBuilder sb = new StringBuilder("[");
        for (char i = 'a'; i <= 'z'; i++) {
            if (vsebuje(mnozica, i)) {
                sb.append(sb.length() != 1 ? "," : "").append(i);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static int steviloElementov(int mnozica) {
        int stElementov = 0;
        int mnozica1 = mnozica;
        while (mnozica1 > 0) {
            stElementov += mnozica1 & 1;
            mnozica1 >>= 1;
        }
        return stElementov;
    }

    public static boolean vsebuje(int mnozica, char c) {
        int bit = getBit(c);
        return (mnozica & bit) == bit;
    }

}
