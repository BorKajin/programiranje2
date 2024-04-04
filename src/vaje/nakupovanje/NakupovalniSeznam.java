package vaje.nakupovanje;

import java.util.Scanner;

public class NakupovalniSeznam {

    /**
     * Izpis navodil za interaktivno uporabo seznama.
     */
    private static void izpisiNavodila() {
        System.out.println("Navodila:");
        System.out.println("1: ustvari seznam,\n2: dodaj element na konec,\n3: vrini element na mesto,\n4: odstrani element na mestu,\n5: vrni dolžino seznama,\n6: izpiši seznam,\n7: izpiši 64-bitno,\n8: uniči seznam,\nq: izhod,\nh: pomoč");
    }

    /**
     * Program za pripravo nakupovalnega seznama.
     * Omogoča ustvarjanje seznama, dodajanje in brisanje posameznih elementov ter izpis seznama.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Priprava nakupovalnega seznama ...");
        System.out.println("h: pomoč, q: izhod");
        char ukaz;
        String element;
        int mesto, dol;
        do {
            System.out.print(">> ");
            ukaz = sc.next().toLowerCase().charAt(0);
            switch (ukaz) {
                case 'q':
                    System.out.println("Izhod.");
                    break;
                case 'h':
                    izpisiNavodila();
                    break;
                case '1': // ustvari seznam
                    System.out.print("Ustvari seznam.\nVnesi dolžino seznama: ");
                    dol = sc.nextInt();
                    if (zbirke.Seznam.narediSeznam(dol))
                        System.out.printf("Seznam dolžine %d je ustvarjen.\n", dol);
                    else
                        System.out.print("NAPAKA: Seznam je že narejen ali podana dolžina ni prava.\n");
                    break;
                case '2': // dodaj element na konec
                    System.out.print("Dodaj element na konec.\nNov element: ");
                    element = sc.next();
                    if (zbirke.Seznam.dodajNaKonecSeznama(element))
                        System.out.printf("Element <%s> je dodan na konec seznama.\n", element);
                    else
                        System.out.println("NAPAKA: Seznam še ni narejen ali pa je že poln.");
                    break;
                case '3': // vrini element na mesto
                    System.out.print("Dodaj element na podano mesto.\nNov element: ");
                    element = sc.next();
                    System.out.print("Mesto vrinjenega elementa (od 1 naprej): ");
                    mesto = sc.nextInt();
                    if (zbirke.Seznam.dodajVSeznam(element, mesto))
                        System.out.printf("Element <%s> je dodan v seznam na mesto %d.\n", element, mesto);
                    else
                        System.out.println("NAPAKA: Seznam še ni narejen, je že poln ali pa je podano napačno mesto.");
                    break;
                case '4': // odstrani element na mestu
                    System.out.print("Odstrani element na podanem mestu.\nMesto brisanega elementa: ");
                    mesto = sc.nextInt();
                    element = zbirke.Seznam.odstraniIzSeznama(mesto);
                    if (element != null)
                        System.out.printf("Element <%s> je odstranjen iz seznama.\n", element);
                    else
                        System.out.printf("NAPAKA: Seznam še ni narejen ali element na mestu %d ne obstaja.\n", mesto);
                    break;
                case '5': // vrni dolžino seznama
                    dol = zbirke.Seznam.dolzinaSeznama();
                    if (dol >= 0)
                        System.out.printf("Dolžina seznama je %d.\n", dol);
                    else
                        System.out.println("NAPAKA: Seznam še ni narejen.");
                    break;
                case '6': // izpiši seznam
                    zbirke.Seznam.izpisiSeznam();
                    break;
                case '7': // izpiši 64-bitno
                    zbirke.Seznam.izpisiSeznam64Bit();
                    break;
                case '8': // uniči seznam
                    if (zbirke.Seznam.uniciSeznam())
                        System.out.println("Seznam je uničen.");
                    else
                        System.out.println("NAPAKA: Seznam ne obstaja.");
                    break;
                default:
                    System.out.println("Ukaz ni veljaven. Za navodila izberite h. Za izhod izberite q.");
            }
        } while (ukaz != 'q');
    }
}
