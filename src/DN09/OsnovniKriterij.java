package DN09;

public class OsnovniKriterij implements Kriterij {
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
