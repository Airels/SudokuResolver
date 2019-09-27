import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Case> cases = new ArrayList<>();
    private List<Structure> rows, columns, blocks;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private int[] entries = {
            0, 0, 0,     5, 3, 0,    0, 8, 0,
            0, 0, 0,     0, 0, 9,    0, 5, 0,
            0, 4, 0,     8, 0, 7,    0, 1, 0,

            0, 5, 7,     0, 0, 0,    0, 0, 0,
            0, 9, 0,     0, 1, 8,    7, 0, 0,
            2, 0, 0,     0, 5, 0,    1, 0, 0,

            0, 0, 0,     0, 6, 2,    3, 0, 0,
            7, 6, 0,     0, 0, 0,    0, 0, 9,
            0, 0, 0,     0, 0, 4,    0, 0, 0};

    public static void main(String[] args) {
        new Main().start();
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
