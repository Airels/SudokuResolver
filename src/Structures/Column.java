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

    public Case existExclusivePair(Case caseToTest) {
        for (Case selectedCase : cases) {
            if (selectedCase.getPossibleValues().size() != 2 || caseToTest == selectedCase)
                continue;

            for (int possibleValue : caseToTest.getPossibleValues()) {
                if (selectedCase.containsValue(possibleValue))
                    return selectedCase;
            }
        }

        return null;
    }


    @Override
    public String toString() {
        String result = "";

        for (Case selectedCase : cases) {
            result += " " + selectedCase.getValue() + "\n";

            if (selectedCase.getRow() == 2 || selectedCase.getRow() == 5)
                result += "---\n";
        }

        return result;
    }
}
