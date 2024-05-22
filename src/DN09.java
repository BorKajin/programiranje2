import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

interface Kriterij {
    int steviloTock(Tekmovanje tekmovanje, String drzava);
}

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
                    tekmovanje.setKriterij(new UtezeniKriterij(Float.parseFloat(args[4]), Float.parseFloat(args[5])));
                    tekmovanje.izpisiRezultateUrejeno(Integer.parseInt(args[3]));
                }
            }
        }
    }
}

class Glas {
    private String odDrzave;
    private String zaDrzavo;
    private int stTock;

    public Glas(String odDrzave, String zaDrzavo, int stTock) {
        this.odDrzave = odDrzave;
        this.zaDrzavo = zaDrzavo;
        this.stTock = stTock;
    }

    public String getOdDrzave() {
        return odDrzave;
    }

    public String getZaDrzavo() {
        return zaDrzavo;
    }

    public int getStTock() {
        return stTock;
    }

    @Override
    public String toString() {
        return String.format("%s --%dt-> %s", odDrzave, stTock, zaDrzavo);
    }
}

class LocenGlas extends Glas {
    private int stTockGlasovi;
    private int stTockZirije;

    public LocenGlas(String odDrzave, String zaDrzavo, int stTock, int stTockGlasovi, int stTockZirija) {
        super(odDrzave, zaDrzavo, stTock);
        this.stTockGlasovi = stTockGlasovi;
        this.stTockZirije = stTockZirija;
    }

    public int getStTockZirije() {
        return stTockZirije;
    }

    public int getStTockGlasovi() {
        return stTockGlasovi;
    }

}

class OsnovniKriterij implements Kriterij {
    @Override
    public int steviloTock(Tekmovanje tekmovanje, String drzava) {
        int tocke = 0;
        for (Glas g : tekmovanje.getSeznamGlasov()) {
            if (g.getZaDrzavo().equals(drzava)) {
                tocke += g.getStTock();
            }
        }
        return tocke;
    }
}

class Tekmovalec {
    private String drzava;
    private String izvajalec;
    private String naslovPesmi;

    public Tekmovalec(String drzava, String izvajalec, String naslovPesmi) {
        this.drzava = drzava;
        this.izvajalec = izvajalec;
        this.naslovPesmi = naslovPesmi;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getIzvajalec() {
        return izvajalec;
    }

    public String getNaslovPesmi() {
        return naslovPesmi;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s - %s", drzava, izvajalec, naslovPesmi);
    }
}

class Tekmovanje {
    private ArrayList<Tekmovalec> seznamTekmovalcev;
    private ArrayList<Glas> seznamGlasov;
    private Kriterij kriterij;
    private boolean urejeno;

    public Tekmovanje(ArrayList<Tekmovalec> seznamTekmovalcev, ArrayList<Glas> seznamGlasov) {
        this.seznamTekmovalcev = seznamTekmovalcev;
        this.seznamGlasov = seznamGlasov;
        this.kriterij = new OsnovniKriterij();
        this.urejeno = false;
    }

    public static Tekmovanje izDatotek(String datotekaTekmovalci, String datotekaGlasovi) {
        ArrayList<Tekmovalec> tekmovalci = new ArrayList<Tekmovalec>();
        try (Scanner sc = new Scanner(new File(datotekaTekmovalci))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] vrstica = sc.nextLine().split(";");
                tekmovalci.add(new Tekmovalec(vrstica[1], vrstica[2], vrstica[3]));
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        ArrayList<Glas> glasovi = new ArrayList<Glas>();
        try (Scanner sc = new Scanner(new File(datotekaGlasovi))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] vrstica = sc.nextLine().split(";", -1);
                if (vrstica[5].isEmpty()) {
                    glasovi.add(new Glas(vrstica[2], vrstica[3], Integer.parseInt(vrstica[4])));
                } else {
                    glasovi.add(new LocenGlas(vrstica[2], vrstica[3], Integer.parseInt(vrstica[4]), Integer.parseInt(vrstica[5]), vrstica[6].isEmpty() ? 0 : Integer.parseInt(vrstica[6])));
                }
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return new Tekmovanje(tekmovalci, glasovi);
    }

    public ArrayList<Tekmovalec> getSeznamTekmovalcev() {
        return seznamTekmovalcev;
    }

    public ArrayList<Glas> getSeznamGlasov() {
        return seznamGlasov;
    }

    public void setKriterij(Kriterij kriterij) {
        this.kriterij = kriterij;
        this.urejeno = false;
    }

    public void izpisiTekmovalce() {
        if (seznamTekmovalcev.isEmpty()) {
            System.out.println("Seznam tekmovalcev je prazen.");
        } else {
            System.out.println("Seznam tekmovalcev:");
        }
        for (Tekmovalec t : seznamTekmovalcev) {
            System.out.println(t);
        }
    }

    public void izpisiGlasove() {
        if (seznamGlasov.isEmpty()) {
            System.out.println("Seznam glasov je prazen.");
        } else {
            System.out.println("Seznam glasov:");
        }
        for (Glas g : seznamGlasov) {
            System.out.println(g);
        }
    }

    public int steviloTock(String drzava) {
        return kriterij.steviloTock(this, drzava);
    }

    public void izpisiTocke() {
        if (seznamTekmovalcev.isEmpty())
            System.out.println("Seznam tekmovalcev je prazen.");
        else
            System.out.println("Seznam tekmovalcev in njihovih tock:");
        for (Tekmovalec t : seznamTekmovalcev) {
            System.out.printf("%s: %dt\n", t.toString(), steviloTock(t.getDrzava()));
        }
    }

    public Tekmovalec getZmagovalec() {
        Tekmovalec zmagovalec = seznamTekmovalcev.get(0);
        for (Tekmovalec t : seznamTekmovalcev) {
            if (steviloTock(zmagovalec.getDrzava()) < steviloTock(t.getDrzava()))
                zmagovalec = t;
        }
        return zmagovalec;
    }

    public void urediPoTockah() {
        if (!urejeno)
            seznamTekmovalcev.sort((tekm2, tekm1) -> Integer.compare(steviloTock(tekm1.getDrzava()), steviloTock(tekm2.getDrzava())));
        urejeno = true;
    }

    public int getMesto(String drzava) {
        urediPoTockah();
        int mesto = 1;
        for (Tekmovalec t : seznamTekmovalcev) {
            if (t.getDrzava().equals(drzava)) {
                return mesto;
            }
            mesto++;
        }
        return -1;
    }

    public void izpisiRezultateUrejeno(int topK) {
        if (seznamTekmovalcev.isEmpty())
            System.out.println("Seznam tekmovalcev je prazen.");
        else
            System.out.println("Najboljse uvrsceni tekmovalci:");
        urediPoTockah();
        int mesto = 1;
        for (Tekmovalec t : seznamTekmovalcev) {
            if (mesto > topK)
                break;
            System.out.printf("%d. %s: %dt\n", mesto++, t.toString(), steviloTock(t.getDrzava()));
        }
    }

}

class UtezeniKriterij implements Kriterij {
    private float utezGlasovanja;
    private float utezZirija;

    public UtezeniKriterij(float utezGlasovanja, float utezZirija) {
        this.utezGlasovanja = utezGlasovanja;
        this.utezZirija = utezZirija;
    }

    @Override
    public int steviloTock(Tekmovanje tekmovanje, String drzava) {
        float tocke = 0;
        for (Glas g : tekmovanje.getSeznamGlasov()) {
            if (g.getZaDrzavo().equals(drzava)) {
                if (g instanceof LocenGlas) {
                    tocke += ((LocenGlas) g).getStTockGlasovi() * utezGlasovanja + ((LocenGlas) g).getStTockZirije() * utezZirija;
                } else {
                    tocke += g.getStTock() * utezZirija;
                }

            }
        }
        return Math.round(tocke);
    }
}


