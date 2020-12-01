package sr.model.resolver.methods;

import sr.model.resolver.Resolver;
import sr.model.structures.Case;
import sr.model.structures.Structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Blocks structure not allowed for this resolution
 */
class ExclusivePairOneNumber implements Method {

    @Override
    public boolean resolve(Resolver resolver) {
        List<Structure> structures = new ArrayList<>();
        structures.addAll(resolver.getRows());
        structures.addAll(resolver.getColumns());

        for (Structure oneStructure : structures) {
            Case firstCaseWithExclusivePair = null;
            Case secondCaseWithExclusivePair = null;

            for (Case selectedCase : oneStructure.getCases()) {
                secondCaseWithExclusivePair = oneStructure.existExclusivePairOneNumber(selectedCase);

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
                        switch (selectedCase.indexOfValue(valueToTest)) {
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
