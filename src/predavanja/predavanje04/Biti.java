package predavanja.predavanje04;

public class Biti {
    public static void main(String[] args) {
        if (args.length<1) {
            System.exit(1);
        }
        int steviloBitov = 0;
        int stevilo = 0;
        try {
            stevilo = Integer.parseInt(args[0]);
        }catch (Exception e){
            System.exit(2);
        }
        while (stevilo > 0) {
            steviloBitov += stevilo & 1;
            stevilo = stevilo >> 1;
        }
        System.out.printf("Stevilo prizganih bitov v %s je %d",args[0], steviloBitov);
    }
}
