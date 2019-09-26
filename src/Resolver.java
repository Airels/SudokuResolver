import java.util.ArrayList;
import java.util.List;

public class Resolver {
    List<Case> cases;
    List<Row> rows;
    List<Column> columns;
    List<Block> blocks;

    int casesFilled = 0, casesFilledBefore = 0;
    long startTime, endTime;
    boolean resolved = true;

    public Resolver(List<Case> cases) {
        this.cases = cases;
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
                System.out.print(" " + getCase(i, j).getValue() + " ");

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

    private void resolvePossibleValues() {
        casesFilled = 0;

        for (int x = 0; x < 9; x++){
            for (int y = 0; y < 9; y++) {
                Case oneCase = getCase(x, y);
                oneCase.clearPossiblesValues();

                if (!oneCase.haveValue()) {
                    for (int i = 1; i < 10; i++) {
                        if (!(existInRow(oneCase, i) || existInColumn(oneCase, i) || existInBlock(oneCase, i))) {
                            oneCase.addPossibleValue(i);
                        }
                    }
                }
                else {
                    casesFilled++;
                }
            }
        }
    }

    private void tryFillValues() {
        tryFillValuesLoop:
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Case oneCase = getCase(x, y);

                if (oneCase.haveValue()) {
                    continue;
                }

                if (oneCase.getPossibleValues().size() == 1) {
                    oneCase.setValue(oneCase.getPossibleValues().get(0));
                    return;
                }
            }
        }

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Case oneCase = getCase(x, y);

                int xBlock, yBlock;
                List<Integer> possibleValuesInBlock = new ArrayList<>();

                if (x < 3)
                    xBlock = 0;
                else if (x < 6)
                    xBlock = 3;
                else
                    xBlock = 6;

                if (y < 3)
                    yBlock = 0;
                else if (y < 6)
                    yBlock = 3;
                else
                    yBlock = 6;


                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Case thisCase = getCase(i+xBlock, j+yBlock);
                        List<Integer> possibleValues = thisCase.getPossibleValues();

                        if (oneCase != thisCase && possibleValues.size() != 0)
                            possibleValuesInBlock.addAll(getCase(i+xBlock, i+yBlock).getPossibleValues());
                    }
                }

                for (int possibleValue : oneCase.getPossibleValues()) {
                    if (!(possibleValuesInBlock.contains(possibleValue))) {
                        oneCase.setValue(possibleValue);
                        return;
                    }
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

    private boolean existInRow(Case oneCase, int value) { // Row == x
        int row = oneCase.getX();

        for (int y = 0; y < 9; y++) {
            if (getCase(row, y).getValue() == value)
                return true;
        }

        return false;
    }

    private boolean existInColumn(Case oneCase, int value) { // Column == y
        int column = oneCase.getY();

        for (int x = 0; x < 9; x++) {
            if (getCase(x, column).getValue() == value)
                return true;
        }

        return false;
    }

    private boolean existInBlock(Case oneCase, int value) {
        int x = oneCase.getX();
        int y = oneCase.getY();

        if (x < 3)
            x = 0;
        else if (x < 6)
            x = 3;
        else
            x = 6;

        if (y < 3)
            y = 0;
        else if (y < 6)
            y = 3;
        else
            y = 6;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Case testCase = getCase(i+x, j+y);
                if (testCase.getValue() == value)
                    return true;
            }
        }

        return false;
    }
}
