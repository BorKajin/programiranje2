public class DN04 {
    public static void main(String[] args) {
        if (args.length < 1){
            System.exit(1);
        }
        System.out.print(sestaviNiz(args[0]));
    }

    static String sestaviNiz(String dvojiskiZapis) {
        StringBuilder sb = new StringBuilder();
        while (!dvojiskiZapis.isEmpty()) {
            try {
                int dolzina= Math.min(dvojiskiZapis.length(), 8);
                char znak = (char)Integer.parseInt(dvojiskiZapis.substring(0, dolzina),2);
                sb.append(znak);
                dvojiskiZapis = dvojiskiZapis.substring(dolzina);
            } catch (Exception e) {
                System.exit(1);
            }
        }
        return sb.toString();
    }
}
