package structures;

import java.util.List;

public abstract class Structure {

    public abstract boolean existIn(int value);

    public abstract int getId();

    public abstract void addCase(Case oneCase);

    public abstract List<Case> getCases();

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
