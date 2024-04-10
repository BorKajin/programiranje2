package predavanja.predavanje07;

import java.util.Scanner;

public class Studenti {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vpiši število študentov: ");
        Student[] studenti = new Student[sc.nextInt()];
        for (int i = 0; i < studenti.length; i++) {
            System.out.print("Vpiši " + (i + 1) + ". ime: ");
            String ime = sc.nextLine();
            System.out.print("Vpiši " + (i + 1) + ". id: ");
            int id = sc.nextInt();
            studenti[i] = new Student(id, ime);
        }
    }
}
