package kvizi;

public class Kviz2 {
    public static void main(String[] args) {

    }

    static int vsotaStevk(String str) {
        int vsota = 0;
        for (int i = 0; i < str.length(); i++) {
            try {
                vsota += Integer.parseInt("" + str.charAt(i));
            } catch (Exception ignored) {
            }
        }
        return vsota;
    }

    static boolean preveriRep(String a, String b) {
        int krajsi = Math.min(a.length(), b.length());
        a = a.substring(a.length() - krajsi);
        b = b.substring(b.length() - krajsi);
        return a.equalsIgnoreCase(b);
    }

    static int[] range(int a, int b, int c) {
        int[] rez = new int[(int) Math.ceil((double) (b - a) / c)];
        for (int i = 0; i < rez.length; i++) {
            rez[i] = a + c * i;
        }
        return rez;
    }

    static void rotiraj(int[] tabela, int k) {
        int[] temp = new int[tabela.length];
        System.arraycopy(tabela, 0, temp, 0, tabela.length);
        for (int i = 0; i < tabela.length; i++) {
            tabela[(i - k % tabela.length + tabela.length) % (tabela.length)] = temp[i];
        }
    }

    static int[] duplikati(int[] tabela) {
        int[] temp = new int[tabela.length];
        int steviloOhranjenih = 0;
        for (int k : tabela) {
            boolean duplikat = false;
            for (int j = 0; j < steviloOhranjenih; j++) {
                if (temp[j] == k) {
                    duplikat = true;
                    break;
                }
            }
            if (!duplikat) {
                temp[steviloOhranjenih++] = k;
            }
        }
        int[] rez = new int[steviloOhranjenih];
        System.arraycopy(temp, 0, rez, 0, steviloOhranjenih);
        return rez;
    }

    static String prevod(String niz) {
        return null;
    }
}
