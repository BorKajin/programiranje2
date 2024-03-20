package kvizi;

public class Kviz2 {
    public static void main(String[] args) {

    }

    static int vsotaStevk(String str) {
        int vsota = 0;
        for (int i = 0; i < str.length(); i++) {
            try {
                vsota += Integer.parseInt(""+str.charAt(i));
            }catch (Exception ignored){}
        }
        return vsota;
    }

    static boolean preveriRep(String a, String b) {
        int krajsi = Math.min(a.length(),b.length());
        a = a.substring(a.length()-krajsi);
        b = b.substring(b.length()-krajsi);
        return a.equalsIgnoreCase(b);
    }

    static int[] range(int a, int b, int c) {
        return null;
    }
}
