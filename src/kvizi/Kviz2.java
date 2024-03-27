package kvizi;

public class Kviz2 {
    public static void main(String[] args) {

    }

    public static int vsotaStevk(String str) {
        int vsota = 0;
        for (int i = 0; i < str.length(); i++) {
            try {
                vsota += Integer.parseInt("" + str.charAt(i));
            } catch (Exception ignored) {
            }
        }
        return vsota;
    }

    public static boolean preveriRep(String a, String b) {
        int krajsi = Math.min(a.length(), b.length());
        a = a.substring(a.length() - krajsi);
        b = b.substring(b.length() - krajsi);
        return a.equalsIgnoreCase(b);
    }

    public static int[] range(int a, int b, int c) {
        int[] rez = new int[(int) Math.ceil((double) (b - a) / c)];
        for (int i = 0; i < rez.length; i++) {
            rez[i] = a + c * i;
        }
        return rez;
    }

    public static void rotiraj(int[] tabela, int k) {
        int[] temp = new int[tabela.length];
        System.arraycopy(tabela, 0, temp, 0, tabela.length);
        for (int i = 0; i < tabela.length; i++) {
            tabela[(i - k % tabela.length + tabela.length) % (tabela.length)] = temp[i];
        }
    }

    public static int[] duplikati(int[] tabela) {
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

    public static String binarnoSestej(String s, String b) {
        boolean c = false;
        StringBuilder vsota = new StringBuilder();
        int dolzinaMax = Math.max(s.length(), b.length());
        for (int i = 0; i < dolzinaMax; i++) {
            char bit1 = (s.length() - 1 - i) >= 0 ? s.charAt(s.length() - (i + 1)) : '0';
            char bit2 = (b.length() - 1 - i) >= 0 ? b.charAt(b.length() - (i + 1)) : '0';
            char rez = c ^ bit1 == '0' ^ bit2 == '0' ? '1' : '0';
            c = (bit1 == '1' && bit2 == '1') || (c && (bit1 == '1' ^ bit2 == '1'));
            vsota.insert(0, rez);
        }
        vsota.insert(0, c?'1':"");
        return vsota.toString();
    }
    
    public static int vsotaSkupnihCifer(int a, int b) {
        int[] stevkeA = new int[(""+a).length()];
        for (int i = 0; i < (""+a).length(); i++) {
            stevkeA[i] = Integer.parseInt(""+(""+a).charAt(i));
        }
        int[] stevkeB = new int[(""+b).length()];
        for (int i = 0; i < (""+b).length(); i++) {
            stevkeB[i] = Integer.parseInt(""+(""+b).charAt(i));
        }
        int vsota = 0;
        for(int i:Kviz1.presek(stevkeA,stevkeB)){
            vsota += i;
        }
        return vsota;
    }

    public static String prevod(String niz) {
        boolean papajscina = true;
        for (String s:niz.split("[^aeioup]")) {
            if (!s.isEmpty() && !(s.matches("[aeiou]pap*")||s.equalsIgnoreCase("p"))){
                papajscina = false;
                break;
            }
        }
        StringBuilder rez = new StringBuilder();

        for (int i = 0; i < niz.length(); i++) {
            char znak = niz.charAt(i);
            rez.append(znak);
            if (znak == 'a' || znak == 'e' || znak == 'i' || znak == 'o' || znak == 'u') {
                if (!papajscina)
                    rez.append("pa");
                else
                    i+=2;
            }
        }
        return rez.toString();
    }

    public static String prepleti(String niz1, String niz2) {
        StringBuilder rez = new StringBuilder();
        for (int i = 0; i < Math.max(niz1.length(),niz2.length()); i++) {
            rez.append((i < niz1.length()) ? niz1.charAt(i) : " ");
            rez.append((i < niz2.length()) ? niz2.charAt(i) : " ");
        }
        return rez.toString();
    }

    public static void odpleti(String niz) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < niz.length(); i+=2) {
            s1.append(niz.charAt(i));
            s2.append((i+1)<niz.length()?niz.charAt(i + 1):"");
        }
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }

    public static String kodiraj(String niz, int odmik) {
        StringBuilder rez = new StringBuilder();
        for (int i = 0; i < niz.length(); i++) {
            rez.append((char)(niz.charAt(i) + odmik));
        }
        return rez.toString();
    }

    public static String dekodiraj(String niz, int odmik) {
        return kodiraj(niz, -odmik);
    }

    public static int[] nicleSpredaj(int[] tabela) {
        int[] temp = new int[tabela.length];
        int steviloElementov = 0;
        for (int j : tabela) {
            if (j != 0) {
                temp[steviloElementov++] = j;
            }
        }
        int[] rez = new int[tabela.length];
        System.arraycopy(temp,0,rez,rez.length-steviloElementov,steviloElementov);
        return rez;
    }

    public static int strStej(String niz, String podniz) {
        if(!niz.contains(podniz)){
            return 0;
        }
        return 1+ strStej(niz.substring(niz.indexOf(podniz)+podniz.length()),podniz);
    }

    public static double[][] zmnoziMatriki(double[][] a, double[][] b){
        if(a[0].length != b.length) {
            System.out.println("Tabeli nemoremo zmnožiti!");
            return null;
        }
        double[][] c = new double[a.length][b[0].length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                double vsota = 0;
                for (int n = 0; n < a[0].length; n++) {
                    vsota += a[i][n]*b[n][j];
                }
                c[i][j]=vsota;
            }
        }
        return c;
    }
}
