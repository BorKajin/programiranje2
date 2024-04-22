package vaje.vinjete;

public class Vinjeta {
    private final String registrskaOznaka;
    private final String razredVinjete;
    private final String zacetekVeljavnosti;
    private final String vrstaVinjete;

    public Vinjeta(String registrskaOznaka, String razredVinjete, String zacetekVeljavnosti, String vrstaVinjete) {
        this.registrskaOznaka = registrskaOznaka;
        this.razredVinjete = razredVinjete;
        this.zacetekVeljavnosti = zacetekVeljavnosti;
        this.vrstaVinjete = vrstaVinjete;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]: %s (%s)", registrskaOznaka, razredVinjete, vrstaVinjete, zacetekVeljavnosti);
    }

    public String getRegistrskaOznaka() {
        return registrskaOznaka;
    }

    public String getVrstaVinjete() {
        return vrstaVinjete;
    }

    public String getRazredVinjete() {
        return razredVinjete;
    }

    public String veljavnostLetneVinjete() {
        if (!this.vrstaVinjete.equals("letna")) return null;
        String[] datum = this.zacetekVeljavnosti.split("[.]");
        if (datum[0].equals("29") && datum[1].equals("2")) {
            datum[0] = "1";
            datum[1] = "3";
        }
        return String.format("%s.%s.%s", datum[0], datum[1], Integer.parseInt(datum[2]) + 1);


    }
}
