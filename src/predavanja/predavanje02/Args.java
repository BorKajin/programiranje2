package predavanja.predavanje02;

public class Args {
    public static void main(String[] args) {
        System.out.printf("Število argumentov: %d\n", args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.printf("Argument %2d: %s\n", i + 1, args[i]);
        }
    }
}
