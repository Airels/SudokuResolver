package Resolver;

import Main.Main;
import Resolver.Exclusion.*;
import Structures.*;

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
        new UniquePossibility(blocks).resolve();

        // FILL VALUE WITH MULTIPLE POSSIBILITIES
        // EXCLUSION METHOD ON BLOCK
        new ExclusionOnBlock(blocks).resolve();

        // EXCLUSION METHOD ON ROW
        new ExclusionOnRow(rows).resolve();

        // EXCLUSION METHOD ON COLUMN
        new ExclusionOnColumn(columns).resolve();


        // EXCLUSIVE PAIR WITH 1 NUMBER FOR EACH ROW (I'll add that later)
        /* for (Structure row : rows) {
            for (Case selectedCase : row.getCases()) {

            }
        } */
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
