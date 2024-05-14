package predavanja.predavanje10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Dnevi {
    public static void main(String[] args) {
        Set<String> dnevi = new HashSet<>();
        dnevi.add("PON");
        dnevi.add("TOR");
        dnevi.add("SRE");
        dnevi.add("ČET");
        dnevi.add("PET");
        dnevi.add("SOB");
        dnevi.add("NED");


        // Sprehod po množici
        Iterator<String> it = dnevi.iterator();
        //noinspection WhileLoopReplaceableByForEach
        while (it.hasNext()) {
            String dan = it.next();
            System.out.println(dan);
        }

        // Boljši način – foreach zanka
        for (String dan : dnevi) {
            System.out.println(dan);
        }

    }
}
