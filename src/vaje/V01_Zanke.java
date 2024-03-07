package vaje;

public class V01_Zanke {
    public static void main(String[] args) {
    }

    static void pravokotnikStevil(int sirina, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= sirina; j++) {
                System.out.print(j%10);
            }
            System.out.println();
        }
    }

    static void pravokotnik(int odmik, int sirina, int visina) {
        for (int i = 0; i < visina; i++) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < sirina; j++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }

    static void pravokotniTrikotnikStevil(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j%10);
            }
            System.out.println();
        }
    }

    static void pravokotniTrikotnikStevilObrnjen(int visina) {
        for (int i = visina; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    static void trikotnikStevil(int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina-i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print(j%10);
            }
            System.out.println();
        }
    }

    static void trikotnik(int odmik, int visina) {
        for (int i = 1; i <= visina; i++) {
            for (int j = 0; j < visina-i+odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void trikotnikObrnjen(int odmik, int visina) {
        for (int i = visina; i >= 1; i--) {
            for (int j = 0; j < visina-i+odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void romb(int odmik, int visina) {
        trikotnik(odmik, visina);
        trikotnikObrnjen(odmik+1,visina-1);
    }

    static void smreka(int velikost) {
        for (int i = 1; i <= velikost; i++) {
            trikotnik(2*(velikost-i),2*i);
        }
        pravokotnik(velikost+1,velikost+(velikost+1)%2,2*velikost);
    }

    static void rombA(int odmik, int velikost){
        for (int i = 1; i <= velikost; i++) {
            for (int j = 0; j < 2*(velikost-i) + odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i-1; j++) {
                System.out.print("# ");
            }
            System.out.println();
        }
        for (int i = velikost-1; i >= 1; i--) {
            System.out.print(" ".repeat(2*(velikost-i) + odmik));
            System.out.print("# ".repeat(2*i-1));
            System.out.println();
        }
    }

    static void rombPrazen(int odmik, int velikost) {
        for (int i = 0; i < velikost; i++) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*velikost-1; j++) {
                if(j<velikost-i){
                    System.out.print("# ");
                } else if (j<velikost-1+i) {
                    System.out.print("  ");
                }else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
        for (int i = velikost-2; i >= 0; i--) {
            for (int j = 0; j < odmik; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*velikost-1; j++) {
                if(j<velikost-i){
                    System.out.print("# ");
                } else if (j<velikost-1+i) {
                    System.out.print("  ");
                }else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    static void iks(int velikost) {
        for (int i = 0; i < velikost; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < velikost*2-1; j++) {
                    if (j==i || j==2*velikost-i-2) {
                        System.out.print("XXXXX");
                    }
                    else {
                        System.out.print("     ");
                    }
                }
                System.out.println();
            }
        }
        for (int i = velikost-2; i >= 0; i--) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < velikost*2-1; j++) {
                    if (j==i || j==2*velikost-i-2) {
                        System.out.print("XXXXX");
                    }
                    else {
                        System.out.print("     ");
                    }
                }
                System.out.println();
            }
        }
    }
}
