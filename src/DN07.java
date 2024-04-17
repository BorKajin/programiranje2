import java.io.File;
import java.util.Scanner;

public class DN07 {

    public static void main(String[] args) {
        if (args.length < 2) System.exit(1);
        izpisiPovrsino(args[1], preberiPlanete(args[0]));
    }

    private static Planet[] preberiPlanete(String imeDatoteke) {
        Planet[] planeti = new Planet[8];
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            for (int i = 0; i < 8; i++) {
                String[] vrstica = sc.nextLine().split(":");
                planeti[i] = new Planet(vrstica[0], Integer.parseInt(vrstica[1]));
            }
        } catch (Exception e) {
            System.exit(2);
        }
        return planeti;
    }

    private static void izpisiPovrsino(String imenaPlanetov, Planet[] planeti) {
        double povrsina = 0;
        for (String ime : imenaPlanetov.split("[+]")) {
            for (Planet planet : planeti) {
                if (ime.equalsIgnoreCase(planet.getIme())) {
                    povrsina += planet.povrsina();
                    break;
                }
            }
        }
        System.out.printf("Povrsina planetov \"%s\" je %d milijonov km2", imenaPlanetov, Math.round(povrsina / 1000000));
    }

}

class Planet {
    private final String ime;
    private final int radij;

    public Planet(String ime, int radij) {
        this.ime = ime;
        this.radij = radij;
    }

    public double povrsina() {
        return 4 * Math.PI * Math.pow(radij, 2);
    }

    public String getIme() {
        return ime;
    }
}
