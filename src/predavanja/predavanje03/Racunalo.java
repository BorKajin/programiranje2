package predavanja.predavanje03;

public class Racunalo {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.exit(2 - args.length);
        }
        racunalo(args);
    }

    private static void racunalo(String[] args) {
        int vsota = Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
        System.out.print(args[0] + " + " + args[1] + " = " + vsota);
    }
}
