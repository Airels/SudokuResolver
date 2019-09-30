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
            for (Case selectedCase : oneStructure.getCases()) {
                int firstPairValue = 0, secondPairValue = 0;
                Case caseWithExclusivePair = oneStructure.existExclusivePair(selectedCase);

                if (caseWithExclusivePair == null)
                    continue;

                // RETRIEVE PAIR VALUE
                for (int valueToTest : caseWithExclusivePair.getPossibleValues()) {
                    if (selectedCase.containsValue(valueToTest))
                        if (firstPairValue == 0)
                            firstPairValue = valueToTest;
                        else
                            secondPairValue = valueToTest;
                }

                // AFFECT NON-PAIR VALUE
                if (firstPairValue != 0 && secondPairValue != 0) {
                    for (int valueToAssign : selectedCase.getPossibleValues()) {
                        if (valueToAssign != firstPairValue && valueToAssign != secondPairValue) {
                            selectedCase.setValue(valueToAssign);
                            selectedCase.resolvedMethod = 3;
                        }
                    }

                    for (int valueToAssign : caseWithExclusivePair.getPossibleValues()) {
                        if (valueToAssign != firstPairValue && valueToAssign != secondPairValue) {
                            caseWithExclusivePair.setValue(valueToAssign);
                            caseWithExclusivePair.resolvedMethod = 3;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
