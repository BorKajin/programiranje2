package predavanja.predavanje04;

public class MnozicaZnakov {
    public static void main(String[] args) {
        int mnozica1 = getPraznaMnozica();
        mnozica1 = dodajElement(mnozica1, 'a','i', 'e', 'z');
        System.out.printf("Mnozica1=%s\n", toString(mnozica1));
        int mnozica2 = getPraznaMnozica();
        mnozica2 = dodajElement(mnozica2, 'a', 'f','z', 'b');
        System.out.printf("Mnozica2=%s\n", toString(mnozica2));
        int presek = presek(mnozica1, mnozica2);
        int unija = unija (mnozica1, mnozica2);
        System.out.printf("presek(%s, %s)=%s\n",
                toString(mnozica1), toString(mnozica2), toString(presek));
        System.out.printf("unija (%s, %s)=%s\n",
                toString(mnozica1), toString(mnozica2), toString (unija));
        System.out.printf("Število elementov v množici 1: %d\n",steviloElementov(mnozica1));
    }

    static int getPraznaMnozica() {
        return 0;
    }

    static int dodajElement(int mnozica, char... znaki) {
        for(char znak:znaki){
            mnozica|= getBit(znak);
        }
        return mnozica;
    }

    static int getBit(char znak) {
        return 1 << (znak - 'a');
    }

    static int presek(int... mnozice) {
        int rez = mnozice.length!=0 ? Integer.MAX_VALUE : 0;
        for(int mnozica:mnozice){
            rez &= mnozica;
        }
        return rez;
    }

    static int unija(int... mnozice) {
        int rez = getPraznaMnozica();
        for(int mnozica:mnozice){
            rez |= mnozica;
        }
        return rez;
    }

    static String toString(int mnozica) {
        StringBuilder sb = new StringBuilder("[");
        for (char i = 'a'; i <= 'z'; i++) {
            if (vsebuje(mnozica, i)){
                sb.append(sb.length()!=1 ? "," : "").append(i);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    static int steviloElementov(int mnozica) {
        int stElementov = 0;
        int mnozica1 = mnozica;
        while (mnozica1 > 0){
            stElementov += mnozica1&1;
            mnozica1 >>= 1;
        }
        return stElementov;
    }

    static boolean vsebuje(int mnozica, char c){
        int bit = getBit(c);
        return (mnozica& bit) == bit;
    }

}
