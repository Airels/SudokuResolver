package Main;

import Resolver.Resolver;
import Structures.*;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private List<Case> cases = new ArrayList<>();
    private List<Structure> rows, columns, blocks;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    private int[] entries = {
            0, 6, 0,     0, 4, 0,    0, 0, 3,
            0, 8, 0,     6, 7, 0,    9, 0, 4,
            0, 0, 0,     2, 9, 0,    1, 0, 0,

            0, 1, 0,     0, 0, 0,    0, 0, 7,
            0, 0, 0,     5, 0, 0,    2, 3, 0,
            0, 0, 7,     8, 2, 0,    0, 9, 0,

            1, 0, 0,     7, 0, 2,    0, 8, 0,
            0, 4, 6,     0, 0, 0,    0, 0, 0,
            0, 0, 8,     0, 0, 0,    3, 0, 0};

    public static void main(String[] args) {
        new Main().start();

        /* List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(3);
        list1.add(4);
        list1.add(9);

        list2.add(9);
        list2.add(3);
        list2.add(4);


        for (int number : list1) {
            if (!list2.contains(number)) {
                System.out.println("false");
                System.exit(0);
            }
        }

        System.out.println("true"); */
    }

    private void start() {
        rows = new ArrayList<>();
        columns = new ArrayList<>();
        blocks = new ArrayList<>();

        int number = 0;

        for (int i = 0; i < 9; i++) {
            rows.add(new Row(i));
            columns.add(new Column(i));
            blocks.add(new Block(i));
        }

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Case newCase = new Case(row, column);
                cases.add(newCase);

                rows.get(row).addCase(newCase);
                columns.get(column).addCase(newCase);
                blocks.get(Block.resolveIDBlock(row, column)).addCase(newCase);

                newCase.setValue(entries[number]);
                number++;
            }
        }

        new Resolver(rows, columns, blocks).resolve();
    }
}
