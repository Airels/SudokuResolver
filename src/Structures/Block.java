package Structures;

import java.util.ArrayList;
import java.util.List;

public class Block implements Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    public Block(int id) {
        this.id = id;
    }

    public void addCase(Case oneCase) {
        cases.add(oneCase);
    }

    public List<Case> getCases() {
        return cases;
    }

    public int getId() {
        return id;
    }

    public static int resolveIDBlock(int row, int column) {
        int idBlock = 0;

        if (row < 6)
            idBlock += 1;
        else
            idBlock += 2;

        if (column < 6)
            idBlock += 3;
        else
            idBlock += 6;

        return idBlock;
    }

    public boolean existIn(int value) {
        for (Case caseToTest : cases) {
            if (caseToTest.getValue() == value)
                return true;
        }

        return false;
    }

    @Override
    public Case existExclusivePairOneNumber(Case caseToTest) {
        throw new IllegalCallerException("Block Structure are note allowed to use Exclusive Pair Methods");
    }

    @Override
    public Case existExclusivePairTwoNumbers(Case caseToTest) {
        throw new IllegalCallerException("Block Structure are note allowed to use Exclusive Pair Methods");
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : cases) {
            result.append(" ").append(selectedCase.getValue()).append(" ");

            if (selectedCase.getColumn() == 2 || selectedCase.getColumn() == 5)
                result.append("\n");
        }

        return result.toString();
    }
}
