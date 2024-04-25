package vaje.banka;

public class VarcevalniRacun extends Racun {
    private final double obresti;

    public VarcevalniRacun(String stevilka, double obresti) {
        super(stevilka);
        this.obresti = obresti;
    }

    public void dodajObresti() {
        this.polog(this.obresti * this.getStanje());
    }

    @Override
    String opisRacuna() {
        return String.format("varčevalni, obrestna mera %.2f%%", this.obresti * 100);
    }
}
