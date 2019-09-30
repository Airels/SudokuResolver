package Resolver.Methods;

import Structures.Case;
import Structures.Structure;

import java.util.List;

public class ExclusivePair {
    public List<Structure> structures;

    public ExclusivePair(List<Structure> structures) {
        this.structures = structures;
    }

    public boolean resolve() {
        for (Structure oneStructure : structures) { // oneStructure refers to one row or column (block is not allowed)
            Case firstCaseWithExclusivePair = null;
            Case secondCaseWithExclusivePair = null;

            for (Case selectedCase : oneStructure.getCases()) {
                secondCaseWithExclusivePair = oneStructure.existExclusivePair(selectedCase);

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
                        if (!(selectedCase.containsValue(valueToTest))) {
                            selectedCase.setValue(valueToTest);
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
