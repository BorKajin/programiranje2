package naloge;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DN03 {
    public static void main(String[] args) {
        izpisiGeslo(args);
    }
    private static String[] preberiBesede(File f) {
        String[] besede = new String[0];
        try (Scanner sc = new Scanner(f)){
            besede = new String[sc.nextInt()];
            for (int i = 0; i < besede.length; i++) {
                besede[i]=sc.next();
            }
        }catch (Exception e) {
            System.out.print("Napaka pri delu z datoteko");
            System.exit(3);
        }
        return besede;
    }

    private static String sestaviGeslo(String[] besede, int dolzina, int seme) {
        Random random = new Random(seme);
        StringBuilder geslo = new StringBuilder();
        for (int i = 0; i < dolzina; i++) {
            String beseda = besede[random.nextInt(besede.length)];
             geslo.append(beseda.charAt(random.nextInt(beseda.length())));
        }
        return geslo.toString();
    }

    private static void izpisiGeslo(String[] args) {
        if (args.length < 3) {
            System.out.print("Napaka: premalo argumentov");
            System.exit(1);
        }
        String[] besede = preberiBesede(new File(args[0]));
        String geslo = sestaviGeslo(besede,Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        System.out.print(geslo);
    }
}
