import com.sun.jdi.event.ExceptionEvent;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        printResult();

        if (resolved)
            System.out.println("Resolved in " + (endTime - startTime) + " milliseconds");
        else
            System.out.println(casesFilled + " out to 81 resolved but can't finish. Check inputs or give easier Sudoku");

        command();
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

                if (selectedCase.haveValue())
                    continue;

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


                if (selectedCase.haveValue())
                    continue;

                // CHECK FOR ALL POSSIBLE VALUES IN THIS BLOCK FOR EVERY CASE
                for (Case caseToCheck : blocks.get(blockID).getCases()) {
                    if (selectedCase != caseToCheck) {
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


    // OTHERS METHODS
    private void command() {
        for (;;) {
            System.out.println();

            System.out.print("> ");
            Scanner commandScanner = new Scanner(System.in);
            String command = commandScanner.nextLine();
            int number;

            if (command.contains("get")) {
                if (command.contains("row")) {
                    number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                    System.out.println(rows.get(number).toString());
                }
                else if (command.contains("column")) {
                    number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                    System.out.println(columns.get(number).toString());
                }
                else if (command.contains("block")) {
                    number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                    System.out.println(blocks.get(number).toString());
                }
                else {
                    System.err.println("Unknown parameters");
                }
            }
        }
    }

    private void printResult() {
        // PRINT FOR EACH ROW
        for (Structure row : rows) {
            for (Case selectedCase : row.getCases()) {
                switch (selectedCase.resolvedMethod) {
                    case 2 :
                        System.out.print(" " + Main.ANSI_RED + selectedCase.getValue() + Main.ANSI_RESET + " ");
                        break;
                    case 1:
                        System.out.print(" " + Main.ANSI_GREEN + selectedCase.getValue() + Main.ANSI_RESET + " ");
                        break;
                    default:
                        System.out.print(" " + selectedCase.getValue() + " ");
                }

                if (selectedCase.getColumn() == 2 || selectedCase.getColumn() == 5)
                    System.out.print("|");
            }

            System.out.println();

            if (row.getId() == 2 || row.getId() == 5)
                System.out.println("-----------------------------");
        }

        // PRINT FOR EACH COLUMN (need improvements, rotating Sudoku)
        /* for (Structure column : columns) {
            for (Case selectedCase : column.getCases()) {
                switch (selectedCase.resolvedMethod) {
                    case 2 :
                        System.out.print(" " + Main.ANSI_RED + selectedCase.getValue() + Main.ANSI_RESET + " ");
                        break;
                    case 1:
                        System.out.print(" " + Main.ANSI_GREEN + selectedCase.getValue() + Main.ANSI_RESET + " ");
                        break;
                    default:
                        System.out.print(" " + selectedCase.getValue() + " ");
                }

                if (selectedCase.getRow() == 2 || selectedCase.getRow() == 5)
                    System.out.print("|");
            }

            System.out.println();

            if (column.getId() == 2 || column.getId() == 5)
                System.out.println("-----------------------------");
        } */

        String[] linesResult = new String[11];
        linesResult[3] = "-----------------------------";
        linesResult[6] = "-----------------------------";

        // PRINT FOR EACH BLOCK
        /* for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
                selectedCase
            }
        } */
    }
}
