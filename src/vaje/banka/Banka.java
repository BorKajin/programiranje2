package vaje.banka;

public class Banka {
    private final Racun[] racuni = new Racun[500];
    private int steviloRacunov = 0;

    /**
     * Doda nov tekoči račun z dano številko, če račun s to številko še ne obstaja.
     *
     * @param stevilka – številka novega računa
     * @param limit    – limit računa
     * @return uspešnost operacije
     */
    public boolean dodajTekociRacun(String stevilka, double limit) {
        if (obstajaRacun(stevilka) != null) return false;
        this.racuni[this.steviloRacunov++] = new TekociRacun(stevilka, limit);
        return true;
    }


    /**
     * Doda nov varčevalni račun z dano številko, če račun s to številko še ne obstaja.
     *
     * @param stevilka – številka novega računa
     * @param obresti  – obresti računa
     * @return uspešnost operacije
     */
    public boolean dodajVarcevalniRacun(String stevilka, double obresti) {
        if (obstajaRacun(stevilka) != null) return false;
        this.racuni[this.steviloRacunov++] = new VarcevalniRacun(stevilka, obresti);
        return true;
    }

    private Racun obstajaRacun(String stevilka) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i].getStevilka().equals(stevilka)) return racuni[i];
        }
        return null;
    }

    /**
     * Na račun z dano številko položi podan znesek, če ta račun obstaja
     *
     * @param stevilka – številka računa za polog
     * @param znesek   – znesek za polog
     * @return uspešnost operacije
     */
    public boolean polog(String stevilka, double znesek) {
        Racun tmp = obstajaRacun(stevilka);
        return tmp != null && tmp.polog(znesek);
    }

    /**
     * Iz računa z dano številko dvigne podan znesek, če ta račun obstaja
     *
     * @param stevilka – številka računa za dvig
     * @param znesek   – znesek za dvig
     * @return uspešnost operacije
     */
    public boolean dvig(String stevilka, double znesek) {
        Racun tmp = obstajaRacun(stevilka);
        return tmp != null && tmp.dvig(znesek);
    }

    /**
     * Vsem varčevalnim računom doda obresti
     */
    public void dodajObresti() {
        for (int i = 0; i < steviloRacunov; i++) {
            if (this.racuni[i] instanceof VarcevalniRacun) {
                ((VarcevalniRacun) this.racuni[i]).dodajObresti();
            }
        }
    }

    /**
     * Izpiše vse račune
     */
    public void izpisiRacune() {
        for (int i = 0; i < steviloRacunov; i++) {
            System.out.println(racuni[i]);
        }
        System.out.printf("Skupaj %d računov.\n", this.steviloRacunov);
    }

    /**
     * Izpiše tekoče ali pa varčevalne račune
     *
     * @param varcevalni – tip računa za izpis: true – varčevalni, false – tekoči
     */
    public void izpisiRacune(boolean varcevalni) {
        int steviloIzpisanih = 0;
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i] instanceof VarcevalniRacun == varcevalni) {
                System.out.println(racuni[i]);
                steviloIzpisanih++;
            }
        }
        System.out.printf("Skupaj %d računov.\n", steviloIzpisanih);
    }

    /**
     * Sortira račune glede na podano polje
     *
     * @param polje    – polje, glede na katero bodo računi sortirani. Lahko je "stevilka" ali pa "stanje"
     * @param padajoce – vrstni red za sortiranje: true – padajoče, false – naraščajoče
     */
    public void sortirajRacune(String polje, boolean padajoce) {
        if (!polje.equals("stevilka") && !polje.equals("stanje")) return;
        for (int i = 0; i < steviloRacunov; i++) {
            for (int j = 0; j < steviloRacunov; j++) {
                if (racuni[i].primerjaj(racuni[j], polje) < 0 ^ padajoce) {
                    var tmp = racuni[i];
                    racuni[i] = racuni[j];
                    racuni[j] = tmp;
                }
            }
        }
    }
}
