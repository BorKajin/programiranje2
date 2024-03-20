package predavanja.predavanje04;

public class Biti {
    public static void main(String[] args) {
        if (args.length<1) {
            System.exit(1);
        }
        int steviloBitov = prestejBite(Integer.parseInt(args[0]));
        System.out.printf("Stevilo prizganih bitov v %s je %d",args[0], steviloBitov);
    }

    private static int prestejBite(int stevilo) {
        int steviloBitov = 0;
        while (stevilo > 0) {
            steviloBitov += stevilo & 1;
            stevilo = stevilo >> 1;
        }
        return steviloBitov;
    }
}
