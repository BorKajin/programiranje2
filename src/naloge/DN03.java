package naloge;

import java.io.File;
import java.util.Scanner;

public class DN03 {
    public static void main(String[] args) {

    }
    private String[] preberiBesede(File f) {
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        }catch (Exception e){
            System.out.print("Datoteka ne obstaja");
            System.exit(2);
        }

    }
}
