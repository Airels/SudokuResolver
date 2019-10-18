package Structures;

import java.util.List;

class ExistenceTests {
    static Case existExclusivePairOneNumber(List<Case> cases, Case caseToTest) {
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

    static Case existExclusivePairTwoNumbers(List<Case> cases, Case caseToTest) {
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
}
