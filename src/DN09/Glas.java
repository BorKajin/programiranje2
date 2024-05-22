package DN09;

public class Glas {
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
