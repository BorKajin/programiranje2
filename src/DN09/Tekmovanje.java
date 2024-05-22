package DN09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tekmovanje {
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
        Tekmovalec zmagovalec = seznamTekmovalcev.getFirst();
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
