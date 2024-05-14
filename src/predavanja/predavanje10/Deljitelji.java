package predavanja.predavanje10;

import java.util.Set;
import java.util.TreeSet;

public class Deljitelji {


    static Set<Integer> deljitelji(int x) {
        TreeSet<Integer> rez = new TreeSet<>();
        for (int i = 1; i <= x / 2; i++) {
            if (x % i == 0) rez.add(i);
        }
        rez.add(x);
        return rez;
    }

    public static void main(String[] args) {
        Set<Integer> d1 = deljitelji(16);
        Set<Integer> d2 = deljitelji(256);

        Set<Integer> unija = new TreeSet<>(d1);
        unija.addAll(d2);
        System.out.println(unija);

        Set<Integer> presek = new TreeSet<>(d1);
        presek.retainAll(d2);
        System.out.println(presek);
    }
}
