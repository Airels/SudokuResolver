package sr.resolver;

import sr.resolver.methods.Methods;
import sr.structures.*;

import java.util.ArrayList;
import java.util.List;

public class Resolver {
    private int resolveTime = 0;

    private final List<Structure> rows, columns, blocks;

    private int casesFilled = 0, casesFilledBefore = 0;

    public Resolver(int[] entries) {
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

                rows.get(row).addCase(newCase);
                columns.get(column).addCase(newCase);
                blocks.get(Block.resolveIDBlock(row, column)).addCase(newCase);

                newCase.setValue(entries[number]);
                number++;
            }
        }
    }

    public int[] resolve() throws Exception {
        // System.out.println("Starting resolving...");

        long startTime = System.currentTimeMillis();

        while (casesFilled < 81) {
            resolvePossibleValues();

            tryFillValues();

            // System.out.println(casesFilled + "/81 resolved");

            if (casesFilled == casesFilledBefore) {
                throw new Exception("Unable to resolve this sudoku");
            }

            casesFilledBefore = casesFilled;
        }

        long endTime = System.currentTimeMillis();

        resolveTime = (int) (endTime - startTime);

        int[] result = new int[81];
        int i = 0;

        for (Structure row : rows) {
            for (Case selectedCase : row.getCases()) {
                result[i] = selectedCase.getValue();
                i++;
            }
        }

        return result;
    }


    // RESOLVE METHODS
    private void resolvePossibleValues() {
        casesFilled = 0;

        for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
                selectedCase.clearPossiblesValues();

                Structure row = rows.get(selectedCase.getRowID());
                Structure column = columns.get(selectedCase.getColumnID());

                if (!selectedCase.haveValue()) {
                    for (int valueToTest = 1; valueToTest < 10; valueToTest++) {
                        if (!(row.existIn(valueToTest) || column.existIn(valueToTest) || block.existIn(valueToTest)))
                            selectedCase.addPossibleValue(valueToTest);
                    }
                }
                else
                    casesFilled++;
            }
        }
    }

    private void tryFillValues() {
        for (Methods method : Methods.values()) {
            if (method.getMethodInstance().resolve(this))
                return; // To re-execute calculation of possible values
        }
    }

    public int getResolveTime() {
        return resolveTime;
    }

    public List<Structure> getRows() {
        return rows;
    }

    public List<Structure> getColumns() {
        return columns;
    }

    public List<Structure> getBlocks() {
        return blocks;
    }
}
