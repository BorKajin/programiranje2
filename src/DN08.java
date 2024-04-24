interface Lik {
    double obseg();
}

public class DN08 {
    public static void main(String[] args) {
        Lik[] liki = new Lik[args.length];
        for (int i = 0; i < args.length; i++) {
            String[] lik = args[i].split(":");
            switch (lik[0]) {
                case "kvadrat" -> liki[i] = new Kvadrat(Double.parseDouble(lik[1]));
                case "pravokotnik" -> liki[i] = new Pravokotnik(Double.parseDouble(lik[1]), Double.parseDouble(lik[2]));
                case "nkotnik" -> liki[i] = new nKotnik(Integer.parseInt(lik[1]), Double.parseDouble(lik[2]));
            }
        }
        System.out.printf("%.0f", skupniObseg(liki));
    }

    private static double skupniObseg(Lik[] liki) {
        double obseg = 0;
        for (Lik lik : liki) {
            obseg += lik.obseg();
        }
        return obseg;
    }
}

class Pravokotnik implements Lik {
    private final double a;
    private final double b;

    public Pravokotnik(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double obseg() {
        return 2 * a + 2 * b;
    }
}

class Kvadrat extends Pravokotnik implements Lik {
    public Kvadrat(double a) {
        super(a, a);
    }
}

class nKotnik implements Lik {
    private final double a;
    private final int n;

    public nKotnik(int n, double a) {
        this.a = a;
        this.n = n;
    }

    @Override
    public double obseg() {
        return n * a;
    }
}

