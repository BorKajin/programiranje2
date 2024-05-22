package DN09;

public class UtezeniKriterij implements Kriterij {
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
