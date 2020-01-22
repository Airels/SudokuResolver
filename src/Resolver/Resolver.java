package Resolver;

import Resolver.Methods.ResolvingMethod;
import Resolver.Methods.ValuesFiller;
import Structures.Case;
import Structures.Structure;

import java.util.List;

public class Resolver {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m"; // EXCLUSIVE TWO NUMBERS
    public static final String ANSI_RED = "\u001B[31m"; // EXCLUSIVE ONE NUMBER
    public static final String ANSI_BLUE = "\u001B[36m"; // EXCLUSION
    public static final String ANSI_GREEN = "\u001B[32m"; // UNIQUE VALUE

    private final List<Structure> rows, columns, blocks;

    private int casesFilled = 0, casesFilledBefore = 0;
    private boolean resolved = true;

    public Resolver(List<Structure> rows, List<Structure> columns, List<Structure> blocks) {
        this.rows = rows;
        this.columns = columns;
        this.blocks = blocks;
    }

    public void resolve() {
        System.out.println("Starting resolving...");
        long startTime = System.currentTimeMillis();

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

        long endTime = System.currentTimeMillis();

        printResult();

        if (resolved)
            System.out.println("Resolved in " + (endTime - startTime) + " milliseconds");
        else
            System.out.println(casesFilled + " out to 81 resolved but can't finish. Check inputs or give easier Sudoku.");
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
                }
                else
                    casesFilled++;
            }
        }
    }

    private void tryFillValues() {
        // FILL VALUE WITH UNIQUE POSSIBILITY
        if (ValuesFiller.Resolve(ResolvingMethod.UNIQUE_POSSIBILITY, blocks))
            return;

        // FILL VALUE WITH MULTIPLE POSSIBILITIES
        if (ValuesFiller.Resolve(ResolvingMethod.EXCLUSION, blocks)
                || ValuesFiller.Resolve(ResolvingMethod.EXCLUSION, rows)
                || ValuesFiller.Resolve(ResolvingMethod.EXCLUSION, columns))
            return;


        // EXCLUSIVE PAIR WITH 1 NUMBER
        if (ValuesFiller.Resolve(ResolvingMethod.EXCLUSIVE_PAIR_ONE_NUMBER, rows))
          return;

        if (ValuesFiller.Resolve(ResolvingMethod.EXCLUSIVE_PAIR_ONE_NUMBER, columns))
            return;


        // EXCLUSIVE PAIR WITH 2 NUMBERS
        if (ValuesFiller.Resolve(ResolvingMethod.EXCLUSIVE_PAIR_TWO_NUMBERS, rows))
            return;

        if (ValuesFiller.Resolve(ResolvingMethod.EXCLUSIVE_PAIR_TWO_NUMBERS, columns))
            return;
    }

    private void printResult() {
        // PRINT FOR EACH ROW
        for (Structure row : rows) {
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
            }

            System.out.println();

            if (row.getId() == 2 || row.getId() == 5)
                System.out.println("-----------------------------");
        }
    }
}
