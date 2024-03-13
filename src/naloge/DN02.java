package naloge;

public class DN02 {
    public static void main(String[] args) {
        if (args.length > 0) {
            kvadrat(args[0]);
        }
    }

    private static void kvadrat(String arg) {
        int stranica = (int) Math.ceil(Math.sqrt(arg.length()));
        for (int i = 0; i < stranica; i++) {
            for (int j = 0; j < stranica; j++) {
                int lokacija = i*stranica + j;
                if (lokacija < arg.length()) {
                    System.out.print(arg.charAt(lokacija));
                }
                else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
