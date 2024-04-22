package vaje.aplikacija;

import vaje.vinjete.SeznamVinjet;

public class Kontrola {
    public static void main(String[] args) {
        SeznamVinjet vinjete = new SeznamVinjet();
        vinjete.preberiPodatke("viri/vinjete.txt");
        vinjete.izpisiVinjete();
        //vinjete.izpisiLetneVeljavnosti();
        //System.out.println(vinjete.preveriVinjeto("KR321MA"));
        vinjete.statistika();
    }
}
