package Structures;

import java.util.ArrayList;
import java.util.List;

public class Column implements Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    public Column(int id) {
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

    public boolean existIn(int value) {
        for (Case caseToTest : cases) {
            if (caseToTest.getValue() == value)
                return true;
        }

        return false;
    }

    @Override
    public Case existExclusivePairOneNumber(Case caseToTest) {
        boolean isMatch;

        if (caseToTest.getPossibleValues().size() != 2)
            return null;

        for (Case selectedCase : cases) {
            isMatch = true;

            if (selectedCase.getPossibleValues().size() != 2 || caseToTest == selectedCase)
                continue;

            for (int valueToTest : caseToTest.getPossibleValues()) {
                if (!(selectedCase.containsValue(valueToTest))) {
                    isMatch = false;
                    break;
                }
            }

            if (!isMatch)
                continue;

            return selectedCase;
        }

        return null;
    }

    @Override
    public Case existExclusivePairTwoNumbers(Case caseToTest) {
        if (caseToTest.getPossibleValues().size() != 2)
            return null;

        for (Case selectedCase : cases) {
            boolean foundFirstPair = false;

            if (selectedCase.getPossibleValues().size() != 2 || caseToTest == selectedCase)
                continue;

            for (int valueToTest : caseToTest.getPossibleValues()) {
                if (selectedCase.containsValue(valueToTest)) {
                    if (foundFirstPair)
                        return selectedCase;

                    foundFirstPair = true;
                }
            }
        }

        return null;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : cases) {
            result.append(" ").append(selectedCase.getValue()).append("\n");

            if (selectedCase.getRow() == 2 || selectedCase.getRow() == 5)
                result.append("---\n");
        }

        return result.toString();
    }
}
