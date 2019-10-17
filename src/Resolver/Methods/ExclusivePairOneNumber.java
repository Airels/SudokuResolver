package Resolver.Methods;

import Structures.Case;
import Structures.Structure;

import java.util.List;

class ExclusivePairOneNumber implements ResolveMethods {
    private List<Structure> structures;

    ExclusivePairOneNumber(List<Structure> structures) {
        this.structures = structures;
    }

    @Override
    public boolean resolve() {
        for (Structure oneStructure : structures) { // oneStructure refers to one row or column (block is not allowed)
            Case firstCaseWithExclusivePair = null;
            Case secondCaseWithExclusivePair = null;

            for (Case selectedCase : oneStructure.getCases()) {
                secondCaseWithExclusivePair = oneStructure.existExclusivePair(selectedCase, 1);

                if (secondCaseWithExclusivePair != null) {
                    firstCaseWithExclusivePair = selectedCase;
                    break;
                }
            }

            if (secondCaseWithExclusivePair != null) {
                for (Case selectedCase : oneStructure.getCases()) {
                    if (selectedCase == firstCaseWithExclusivePair
                            || selectedCase == secondCaseWithExclusivePair
                            || selectedCase.getPossibleValues().size() != 2)
                        continue;

                    for (int valueToTest : firstCaseWithExclusivePair.getPossibleValues()) {
                        switch (selectedCase.indexOfContainsValue(valueToTest)) {
                            case -1:
                                continue;
                            case 0:
                                selectedCase.setValue(selectedCase.getPossibleValues().get(1));
                                selectedCase.resolvedMethod = 3;
                                return true;
                            case 1:
                                selectedCase.setValue(selectedCase.getPossibleValues().get(0));
                                selectedCase.resolvedMethod = 3;
                                return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
