import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Case> cases = new ArrayList<>();
    List<Row> rows = new ArrayList<>();
    List<Column> columns = new ArrayList<>();
    List<Block> blocks = new ArrayList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    int[] entries = {
            0, 0, 0,     0, 6, 8,    0, 3, 0,
            1, 9, 0,     0, 0, 0,    0, 0, 0,
            8, 0, 3,     1, 0, 0,    2, 0, 0,

            4, 0, 0,     0, 5, 1,    0, 6, 0,
            7, 0, 0,     0, 2, 0,    0, 0, 4,
            0, 0, 0,     0, 7, 0,    8, 0, 0,

            0, 1, 0,     0, 0, 5,    0, 0, 7,
            0, 0, 4,     0, 0, 0,    0, 0, 0,
            0, 5, 0,     0, 3, 0,    1, 0, 0};

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        int number = 0;

        for (int i = 0; i < 9; i++) {
            rows.add(new Row(i));
            columns.add(new Column(i));
            blocks.add(new Block(i));
        }

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Case oneCase = new Case(row, column);
                cases.add(oneCase);
                rows.get(row).addCase(oneCase);
                columns.get(column).addCase(oneCase);
                blocks.get(Block.resolveIDBlock(row, column)).addCase(oneCase);

                oneCase.setValue(entries[number]);
                number++;
            }
        }

        new Resolver(cases, rows, columns, blocks).resolve();
    }
}
