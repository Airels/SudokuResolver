import com.sun.jdi.event.ExceptionEvent;

import java.util.ArrayList;
import java.util.List;

public class Resolver {
    List<Case> cases;
    List<Structure> rows, columns, blocks;

    int casesFilled = 0, casesFilledBefore = 0;
    long startTime, endTime;
    boolean resolved = true;

    public Resolver(List<Case> cases, List<Structure> rows, List<Structure> columns, List<Structure> blocks) {
        this.cases = cases;
        this.rows = rows;
        this.columns = columns;
        this.blocks = blocks;
    }

    public void resolve() {
        System.out.println("Starting resolving...");
        startTime = System.currentTimeMillis();

        while (casesFilled < 81) {
            resolvePossibleValues();

            tryFillValues();

            System.out.println(casesFilled + "/81 resolved");

            if (casesFilled == casesFilledBefore) {
                resolved = false;
                break;
            }

            casesFilledBefore = casesFilled;
        }

        endTime = System.currentTimeMillis();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getCase(i, j).resolvedMethod == 2) {
                    System.out.print(" " + Main.ANSI_RED + getCase(i, j).getValue() + Main.ANSI_RESET + " ");
                }
                else if (getCase(i, j).resolvedMethod == 1) {
                    System.out.print(" " + Main.ANSI_GREEN + getCase(i, j).getValue() + Main.ANSI_RESET + " ");
                }
                else {
                    System.out.print(" " + getCase(i, j).getValue() + " ");
                }

                if (j == 2 || j == 5) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i == 2 || i == 5) {
                System.out.println("-----------------------------");
            }
        }

        if (resolved)
            System.out.println("Resolved in " + (endTime - startTime) + " milliseconds");
        else
            System.out.println(casesFilled + " out to 81 resolved but can't finish. Check inputs or give easier Sudoku");
    }


    // RESOLVE METHODS
    private void resolvePossibleValues() {
        casesFilled = 0;
        int casesWithNoPossibilities = 0;

        for (Structure row : rows) {
            for (Case selectedCase : row.getCases()) {
                selectedCase.clearPossiblesValues();

                Structure column = columns.get(selectedCase.getColumn());
                Structure block = blocks.get(Block.resolveIDBlock(row.getId(), column.getId()));

                if (!selectedCase.haveValue()) {
                    for (int valueToTest = 1; valueToTest < 10; valueToTest++) {
                        if (!(row.existIn(valueToTest) || column.existIn(valueToTest) || block.existIn(valueToTest)))
                            selectedCase.addPossibleValue(valueToTest);
                    }

                    if (selectedCase.getPossibleValues().size() == 0)
                        casesWithNoPossibilities++;
                }
                else
                    casesFilled++;
            }
        }

        // System.out.println(casesWithNoPossibilities + " cases with no possibilities!");
    }

    private void tryFillValues() {
        tryFillValuesLoop:
        // FILL VALUE WITH UNIQUE POSSIBILITY
        for (Structure row : rows) {
            for (int caseIndex = 0; caseIndex < 9; caseIndex++) {
                Case selectedCase = row.getCase(caseIndex);

                if (selectedCase.haveValue()) {
                    continue;
                }

                if (selectedCase.getPossibleValues().size() == 1) {
                    selectedCase.setValue(selectedCase.getPossibleValues().get(0));
                    selectedCase.resolvedMethod = 1;
                    return;
                }
            }
        }

        // FILL VALUE WITH MULTIPLE POSSIBILITIES
        for (Structure row : rows) {
            for (int caseIndex = 0; caseIndex < 9; caseIndex++) { // caseIndex is equivalent to column
                Case selectedCase = row.getCase(caseIndex);
                List<Integer> possibleValuesInBlock = new ArrayList<>();
                int blockID = Block.resolveIDBlock(row.getId(), caseIndex);


                // CHECK FOR ALL POSSIBLE VALUES IN THIS BLOCK FOR EVERY CASE
                for (Case caseToCheck : blocks.get(blockID).getCases()) {
                    if (selectedCase != caseToCheck && caseToCheck.getPossibleValues().size() != 0) {
                        possibleValuesInBlock.addAll(caseToCheck.getPossibleValues());
                    }
                }

                // CHECK FOR EVERY POSSIBLE VALUES IN THE SELECTED CASE, IF THERE ANY VALUES NOT ALREADY PRESENT FOR EACH CASES IN SAME BLOCK
                for (int possibleValue : possibleValuesInBlock) {
                    if (!(possibleValuesInBlock.contains(possibleValue)))
                        selectedCase.setValue(possibleValue);
                }
            }
        }
    }


    private Case getCase(int x, int y) {
        for (Case oneCase : cases)
            if (oneCase.isGoodCase(x, y))
                return oneCase;

        return null;
    }
}
