package predavanja.predavanje03;

import java.util.Random;

public class Loto {
    public static void main(String[] args) {
        izpisiStevilke();
    }

    private static void izpisiStevilke() {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            System.out.print(random.nextInt(1,50) + " ");
        }
        System.out.print(random.nextInt(1,50));
    }
}
