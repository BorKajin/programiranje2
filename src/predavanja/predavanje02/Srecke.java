package predavanja.predavanje02;

public class Srecke {
    public static void main(String[] args) {
        srecke();
    }

    private static void srecke() {
        int steviloVrstic = 10;
        System.out.println("Število srečk | Cena (EUR)");
        System.out.println("--------------------------");
        for (int i = 1; i <= steviloVrstic; i++) {
            System.out.printf("%5d%9s|%6.2f\n",i,"",i*1.25);
        }
    }
}
