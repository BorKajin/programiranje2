public class DN06 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.exit(1);
        }
        izrisiSudoku(args[0]);
    }


    private static void izrisiSudoku(String arg) {
        StdDraw.setScale(0, 100);
        StdDraw.enableDoubleBuffering();
        for (int i = 8; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                char znak = arg.charAt((8 - i) * 9 + j);
                StdDraw.rectangle((j + 1) * 10, (i + 1) * 10, 5, 5);
                StdDraw.text((j + 1) * 10, (i + 1) * 10, "" + (znak != '0' ? znak : ' '));
                if ((j - 1) % 3 == 0 && (i - 1) % 3 == 0) {
                    StdDraw.setPenRadius(0.01);
                    StdDraw.rectangle((j + 1) * 10, (i + 1) * 10, 15, 15);
                    StdDraw.setPenRadius();
                }
            }
        }
        StdDraw.show();
    }
}
