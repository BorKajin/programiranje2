package DN09;

public class DN09 {
    public static void main(String[] args) {
        if (args.length < 1) System.exit(0);
        switch (args[0]) {
            case "izpisiTekmovanje" -> {
                Tekmovanje tekmovanje = Tekmovanje.izDatotek(args[1], args[2]);
                if (tekmovanje != null) {
                    tekmovanje.izpisiTekmovalce();
                    System.out.println();
                    tekmovanje.izpisiGlasove();
                }
            }
            case "izpisiTocke" -> {
                Tekmovanje tekmovanje = Tekmovanje.izDatotek(args[1], args[2]);
                if (tekmovanje != null) {
                    tekmovanje.izpisiTocke();
                }
            }
            case "najboljse" -> {
                Tekmovanje tekmovanje = Tekmovanje.izDatotek(args[1], args[2]);
                if (tekmovanje != null) {
                    tekmovanje.izpisiRezultateUrejeno(Integer.parseInt(args[3]));
                }
            }
            case "utezeno" -> {
                Tekmovanje tekmovanje = Tekmovanje.izDatotek(args[1], args[2]);
                if (tekmovanje != null) {
                    tekmovanje.setKriterij(new UtezeniKriterij(Float.parseFloat(args[5]), Float.parseFloat(args[4])));
                    tekmovanje.izpisiRezultateUrejeno(Integer.parseInt(args[3]));
                }
            }
        }
    }
}
