package sr.resolver.methods;

import sr.resolver.Resolver;
import sr.structures.Case;
import sr.structures.Structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Blocks structure not allowed for this resolution
 */
class ExclusivePairTwoNumbers implements Method {

    @Override
    public boolean resolve(Resolver resolver) {
        List<Structure> structures = new ArrayList<>();
        structures.addAll(resolver.getRows());
        structures.addAll(resolver.getColumns());

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
