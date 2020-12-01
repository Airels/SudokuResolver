package sr;

import sr.resolver.Resolver;

public class Main {
    /* public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m"; // EXCLUSIVE TWO NUMBERS
    public static final String ANSI_RED = "\u001B[31m"; // EXCLUSIVE ONE NUMBER
    public static final String ANSI_BLUE = "\u001B[36m"; // EXCLUSION
    public static final String ANSI_GREEN = "\u001B[32m"; // UNIQUE VALUE */

    private static boolean onlyResult = false;

    private static int[] entries = {
            2, 0, 5,     0, 0, 0,    0, 0, 0,
            3, 0, 8,     6, 0, 0,    9, 0, 0,
            0, 0, 0,     1, 0, 0,    4, 0, 0,

            0, 0, 0,     0, 5, 0,    0, 1, 0,
            0, 0, 0,     0, 9, 0,    0, 2, 0,
            8, 7, 0,     0, 2, 0,    0, 0, 0,

            0, 0, 0,     0, 8, 9,    0, 0, 3,
            0, 0, 6,     0, 0, 3,    0, 0, 5,
            5, 0, 4,     0, 0, 0,    0, 0, 1};

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-resultOnly":
                    onlyResult = true;
                    break;
                case "-grid":
                    String initialGrid = args[i+1];
                    for (int j = 0; j < 81; j++) {
                        entries[j] = Character.getNumericValue(initialGrid.charAt(j));
                    }
                    break;
            }
        }

        Resolver resolver = new Resolver(entries);
        int[] results = resolver.resolve();

        if (onlyResult) {
            for (int value : results) {
                System.out.print(value);
            }
        } else {
            printResult(results);
            System.out.println("Resolved in " + resolver.getResolveTime() + " milliseconds");
        }
    }

    public static void printResult(int[] result) {
        int row = -1, column = -1;

        for (int value : result) {
            System.out.print(" " + value + " ");

            column++;

            if (column == 2 || column == 5)
                System.out.print("|");

            if (column == 8) {
                column = -1;
                row++;

                System.out.println();

                if (row == 2 || row == 5)
                    System.out.println("-----------------------------");
            }
        }

        // PRINT FOR EACH ROW
        /* for (Structure row : rows) {
            for (Case selectedCase : row.getCases()) {
                switch (selectedCase.resolvedMethod) {
                    case 4:
                        System.out.print(" " + ANSI_PURPLE + selectedCase.getValue() + ANSI_RESET + " ");
                        break;
                    case 3:
                        System.out.print(" " +  ANSI_RED + selectedCase.getValue() + ANSI_RESET + " ");
                        break;
                    case 2 :
                        System.out.print(" " + ANSI_BLUE + selectedCase.getValue() + ANSI_RESET + " ");
                        break;
                    case 1:
                        System.out.print(" " + ANSI_GREEN + selectedCase.getValue() + ANSI_RESET + " ");
                        break;
                    default:
                        System.out.print(" " + selectedCase.getValue() + " ");
                }

                if (selectedCase.getColumn() == 2 || selectedCase.getColumn() == 5)
                    System.out.print("|");

                     System.out.println();

                if (row.getId() == 2 || row.getId() == 5)
                    System.out.println("-----------------------------");
            } */
    }
}
