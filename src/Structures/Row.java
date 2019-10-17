package Structures;

import java.util.ArrayList;
import java.util.List;

public class Row implements Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    public Row(int id) {
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

    public Case existExclusivePair(Case caseToTest, int numberOfPairs) {

        if (numberOfPairs == 1) {
            if (caseToTest.getPossibleValues().size() != 2)
                return null;

            for (Case selectedCase : cases) {
                if (selectedCase.getPossibleValues().size() != 2 || caseToTest == selectedCase)
                    continue;

                for (int valueToTest : caseToTest.getPossibleValues()) {
                    if (!(selectedCase.containsValue(valueToTest)))
                        return null;
                }

                return  selectedCase;
            }
        }
        else if (numberOfPairs == 2) {
            if (caseToTest.getPossibleValues().size() != 3)
                return null;

            for (Case selectedCase : cases) {
                boolean differentValueCaptured = false;

                if (selectedCase.getPossibleValues().size() != 3 || caseToTest == selectedCase)
                    continue;

                for (int valueToTest : caseToTest.getPossibleValues()) {
                    if (!(selectedCase.containsValue(valueToTest)))
                        if (differentValueCaptured)
                            return null;
                        else
                            differentValueCaptured = true;
                }

                return selectedCase;
            }
        }
        else {
            System.out.println("ERROR: INVALID PARAMETER numberOfPairs. Expected: 1/2. Found: " + numberOfPairs);
        }

        return null;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : cases) {
            result.append(" ").append(selectedCase.getValue()).append(" ");

            if (selectedCase.getColumn() == 2 || selectedCase.getColumn() == 5)
                result.append("|");
        }

        return result.toString();
    }
}
