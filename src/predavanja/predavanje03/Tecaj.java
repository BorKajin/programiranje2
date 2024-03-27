package predavanja.predavanje03;

import java.io.File;
import java.util.Scanner;

public class Tecaj {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.print("Napaka: Datoteka ni navedena");
            System.exit(1);
        }
        tecaj(new File(args[0]));

    }

    private static void tecaj(File f) {
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (Exception e) {
            System.out.print("Napaka: Datoteka ni najdena.");
            System.exit(2);
        }
        String maxDatum = null, minDatum = null;
        Double min = null, max = null;
        try {
            while (sc.hasNext()) {
                String tempDatum = sc.next();
                double tempTecaj = Double.parseDouble(sc.next());
                if (max == null || tempTecaj > max) {
                    max = tempTecaj;
                    maxDatum = tempDatum;
                }
                if (min == null || tempTecaj < min) {
                    min = tempTecaj;
                    minDatum = tempDatum;
                }
            }
        } catch (Exception e) {
            System.out.print("Napaka pri delu z datoteko.");
            System.exit(3);
        }
        System.out.printf("MIN tečaj: %12.4f (datum: %s)\n", min, minDatum);
        System.out.printf("MAX tečaj: %12.4f (datum: %s)", max, maxDatum);
    }
}
