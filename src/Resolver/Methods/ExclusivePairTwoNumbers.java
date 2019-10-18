package Resolver.Methods;

import Structures.Case;
import Structures.Structure;

import java.util.List;

class ExclusivePairTwoNumbers {
    private List<Structure> structures;

    ExclusivePairTwoNumbers(List<Structure> structures) {
        this.structures = structures;
    }

    boolean resolve() {
        for (Structure oneStructure : structures) { // oneStructure refers to one row or column (block is not allowed)
            Case firstCaseWithExclusivePair = null;
            Case secondCaseWithExclusivePair = null;

            for (Case selectedCase : oneStructure.getCases()) {
                secondCaseWithExclusivePair = oneStructure.existExclusivePairTwoNumbers(selectedCase);

                if (secondCaseWithExclusivePair != null) {
                    firstCaseWithExclusivePair = selectedCase;
                    break;
                }
            }

            if (secondCaseWithExclusivePair != null) {
                for (Case selectedCase : oneStructure.getCases()) {
                    if (selectedCase == firstCaseWithExclusivePair
                            || selectedCase == secondCaseWithExclusivePair
                            || selectedCase.getPossibleValues().size() != 3)
                        continue;

                    int firstPair = firstCaseWithExclusivePair.getPossibleValues().get(0);
                    int secondPair = firstCaseWithExclusivePair.getPossibleValues().get(1);

                    int indexOfFirstPair = selectedCase.indexOfValue(firstPair);
                    int indexOfSecondPair = selectedCase.indexOfValue(secondPair);

                    if (indexOfFirstPair == -1 || indexOfSecondPair == -1)
                        continue;

                    int index = indexOfFirstPair + indexOfSecondPair;

                    switch (index) {
                        case 1:
                            selectedCase.setValue(selectedCase.getPossibleValues().get(2));
                            selectedCase.resolvedMethod = 4;
                            return true;
                        case 2:
                            selectedCase.setValue(selectedCase.getPossibleValues().get(1));
                            selectedCase.resolvedMethod = 4;
                            return true;
                        case 3:
                            selectedCase.setValue(selectedCase.getPossibleValues().get(0));
                            selectedCase.resolvedMethod = 4;
                            return true;
                        default:
                            throw new RuntimeException("Unexpected value: index variable returned: " + index + ". She should return 1, 2 or 3");
                    }
                }
            }
        }

        return false;
    }
}
