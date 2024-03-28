package predavanja.predavanje05;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) {
        char[][] sudoku = preberiNakljucenSudoku("viri/sudoku.txt");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("-".repeat(35));
            }
            for (int j = 0; j < 9; j++) {
                System.out.printf("%c %c ", j % 3 == 0 ? '|' : ' ', sudoku[i][j] == '0' ? ' ' : sudoku[i][j]);
            }
            System.out.println();
        }
    }

    public static char[][] preberiNakljucenSudoku(String datoteka) {
        char[][] sudoku = new char[9][9];
        try (Scanner sc = new Scanner(new File(datoteka))) {
            Random rnd = new Random();
            int v = rnd.nextInt(50);
            while (v-- > 0)
                sc.nextLine();
            String vrstica = sc.nextLine();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = vrstica.charAt(i * 9 + j);
                }
            }
        } catch (Exception e) {
            System.out.println("Napaka datoteke" + e);
        }
        return sudoku;
    }


}
