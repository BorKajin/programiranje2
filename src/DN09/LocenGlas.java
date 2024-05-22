package DN09;

public class LocenGlas extends Glas {
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
