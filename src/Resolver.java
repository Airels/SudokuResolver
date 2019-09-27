import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Resolver {
    private List<Structure> rows, columns, blocks;

    private int casesFilled = 0, casesFilledBefore = 0;
    private long startTime, endTime;
    private boolean resolved = true;

    public Resolver(List<Structure> rows, List<Structure> columns, List<Structure> blocks) {
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

        for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
                selectedCase.clearPossiblesValues();

                Structure row = rows.get(selectedCase.getRow());
                Structure column = columns.get(selectedCase.getColumn());

                if (!selectedCase.haveValue()) {
                    for (int valueToTest = 1; valueToTest < 10; valueToTest++) {
                        if (!(row.existIn(valueToTest) || column.existIn(valueToTest) || block.existIn(valueToTest)))
                            selectedCase.addPossibleValue(valueToTest);
                    }

                    /* if (casesFilledBefore == 32 && row.getId() == 1 && column.getId() == 0) {
                        System.out.println(row.existIn(9));
                        System.out.println(column.existIn(9));
                        System.out.println(block.existIn(9));

                        List<Integer> integers = new ArrayList<>();

                        for (Case oneCase : block.getCases()) {
                            if (oneCase != selectedCase)
                                integers.addAll(oneCase.getPossibleValues());
                        }

                        System.out.println(integers.contains(9));

                        printResult();

                        System.exit(405);
                    }

                    if (selectedCase.getPossibleValues().size() == 0) {
                        String result = "ERR :\n";
                        result += "CELL COORDINATES : (" + row.getId() + ";" + column.getId() + ")";

                        printResult();

                        System.err.println(result);
                        System.exit(404);
                    } */
                }
                else
                    casesFilled++;
            }
        }
    }

    private void tryFillValues() {
        // FILL VALUE WITH UNIQUE POSSIBILITY
        for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
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
        for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
                List<Integer> possibleValuesInBlock = new ArrayList<>();

                if (selectedCase.haveValue())
                    continue;

                for (Case oneCase : block.getCases()) {
                    if (oneCase != selectedCase)
                        possibleValuesInBlock.addAll(oneCase.getPossibleValues());
                }

                for (int possibleValue : selectedCase.getPossibleValues()) {
                    if (!(possibleValuesInBlock.contains(possibleValue))) {
                        selectedCase.setValue(possibleValue);
                        selectedCase.resolvedMethod = 2;
                        return;
                    }
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
            else if (command.contains("end")) {
                System.exit(0);            }
            else {
                System.out.println("Unknown command");
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
    }
}
