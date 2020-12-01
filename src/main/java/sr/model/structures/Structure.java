package sr.model.structures;

import java.util.ArrayList;
import java.util.List;

public abstract class Structure {
    protected int id;
    private List<Case> cases = new ArrayList<>();

    public Structure(int id) {
        this.id = id;
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

    public void addCase(Case oneCase) {
        cases.add(oneCase);
    }

    public List<Case> getCases() {
        return cases;
    }

    public Case existExclusivePairOneNumber(Case caseToTest) {
        boolean isMatch;

        if (caseToTest.getPossibleValues().size() != 2)
            return null;

        for (Case selectedCase : getCases()) {
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

    public Case existExclusivePairTwoNumbers(Case caseToTest) {
        if (caseToTest.getPossibleValues().size() != 2)
            return null;

        for (Case selectedCase : getCases()) {
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
}
