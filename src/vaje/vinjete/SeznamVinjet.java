package vaje.vinjete;

import java.io.File;
import java.util.Scanner;

public class SeznamVinjet {
    private Vinjeta[] prodaneVinjete;

    public boolean preberiPodatke(String vir) {
        try (Scanner sc = new Scanner(new File(vir))) {
            this.prodaneVinjete = new Vinjeta[sc.nextInt()];
            sc.nextLine();
            for (int i = 0; i < this.prodaneVinjete.length; i++) {
                String tmp = sc.nextLine();
                String[] vrstica = tmp.split(";");
                this.prodaneVinjete[i] = new Vinjeta(vrstica[0], vrstica[1], vrstica[2], vrstica[3]);
            }
        } catch (Exception e) {
            System.out.println("Problem pri branju podatkov!");
            return false;
        }
        return true;
    }

    public void izpisiVinjete() {
        System.out.printf("V sistemu so zabeležene prodane vinjete (%d):\n", this.prodaneVinjete.length);
        for (Vinjeta vinjeta : this.prodaneVinjete) {
            System.out.println(vinjeta);
        }
    }

    public void izpisiVinjete(String vrsta) {
        System.out.printf("V sistemu je %s vinjeta za naslednja vozila:\n", vrsta);
        for (Vinjeta vinjeta : this.prodaneVinjete) {
            if (vinjeta.getVrstaVinjete().equals(vrsta)) {
                System.out.println(vinjeta);
            }
        }
    }

    public boolean preveriVinjeto(String registrska) {
        for (Vinjeta vinjeta : this.prodaneVinjete) {
            if (vinjeta.getRegistrskaOznaka().equals(registrska)) {
                return true;
            }
        }
        return false;
    }

    public void izpisiLetneVeljavnosti() {
        System.out.println("Letne vinjete z datumi veljavnosti:");
        for (Vinjeta vinjeta : this.prodaneVinjete) {
            if (vinjeta.getVrstaVinjete().equals("letna")) {
                System.out.printf("%s veljavna do %s\n", vinjeta.getRegistrskaOznaka(), vinjeta.veljavnostLetneVinjete());
            }
        }
    }

    public void statistika() {
        String[] razredi = new String[]{"1", "2A", "2B"};
        for (String razred : razredi) {
            int letna = 0, polletna = 0, mesecna = 0, tedenska = 0, max = 0;
            for (Vinjeta vinjeta : this.prodaneVinjete) {
                if (!vinjeta.getRazredVinjete().equals(razred)) continue;
                if (vinjeta.getVrstaVinjete().equals("letna")) {
                    letna++;
                    max = Math.max(letna, max);
                }
                if (vinjeta.getVrstaVinjete().equals("polletna")) {
                    polletna++;
                    max = Math.max(polletna, max);
                }
                if (vinjeta.getVrstaVinjete().equals("mesecna")) {
                    mesecna++;
                    max = Math.max(mesecna, max);
                }
                if (vinjeta.getVrstaVinjete().equals("tedenska")) {
                    tedenska++;
                    max = Math.max(tedenska, max);
                }
            }
            String vrste = (letna == max ? "letna, " : "") + (polletna == max ? "polletna, " : "") + (mesecna == max ? "mesečna, " : "") + (tedenska == max ? "tedenska, " : "");
            System.out.printf("Razred %s: %d (%s)\n", razred, max, vrste.substring(0, vrste.length() - 2));
        }
    }

}
