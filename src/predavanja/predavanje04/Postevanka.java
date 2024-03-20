package predavanja.predavanje04;

import java.io.PrintWriter;
import java.util.Scanner;

public class Postevanka {


    public static void main(String[] args) {
        int n;
        int a;
        int b;
        try (Scanner sc = new Scanner(System.in)){
            System.out.print("Vnesi stevilo:");
            n = sc.nextInt();
            System.out.print("Vnesi spodnjo mejo:");
            a = sc.nextInt();
            System.out.print("Vnesi zgornjo mejo:");
            b = sc.nextInt();
        }
        try (PrintWriter pw = new PrintWriter("viri/postevanka.txt")){
            for (int i = a; i <= b; i++) {
                pw.printf("%d * %d = %d\n",i,n,i*n);
            }
        } catch (Exception ignored) {}
    }
}
