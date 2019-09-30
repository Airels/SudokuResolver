package Resolver;

import Main.Main;
import Resolver.Methods.Exclusion;
import Resolver.Methods.ExclusivePair;
import Resolver.Methods.UniquePossibility;
import Structures.*;

import java.util.List;

public class Resolver {
    private List<Structure> rows, columns, blocks;

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

        // command();
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
        if (new UniquePossibility(blocks).resolve())
            return;

        // FILL VALUE WITH MULTIPLE POSSIBILITIES
        if (new Exclusion(blocks).resolve() || new Exclusion(rows).resolve() || new Exclusion(columns).resolve())
            return;


        // EXCLUSIVE PAIR WITH 1 NUMBER
        // EXCLUSIVE PAIR ON ROW
        if (new ExclusivePair(rows).resolve())
          return;

        // EXCLUSIVE PAIR ON COLUMN
        new ExclusivePair(columns).resolve();
    }

    private void printResult() {
        // PRINT FOR EACH ROW
        for (Structure row : rows) {
            for (Case selectedCase : row.getCases()) {
                switch (selectedCase.resolvedMethod) {
                    case 3:
                        System.out.print(" " + Main.ANSI_RED + selectedCase.getValue() + Main.ANSI_RESET + " ");
                        break;
                    case 2 :
                        System.out.print(" " + Main.ANSI_BLUE + selectedCase.getValue() + Main.ANSI_RESET + " ");
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