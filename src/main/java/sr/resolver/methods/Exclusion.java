package sr.resolver.methods;

import sr.resolver.Resolver;
import sr.structures.Case;
import sr.structures.Structure;

import java.util.ArrayList;
import java.util.List;

class Exclusion implements Method {

    @Override
    public boolean resolve(Resolver resolver) {
        List<Structure> structures = new ArrayList<>();
        structures.addAll(resolver.getRows());
        structures.addAll(resolver.getColumns());
        structures.addAll(resolver.getBlocks());

        for (Structure oneStructure : structures) {
            for (Case selectedCase: oneStructure.getCases()) {
                List<Integer> possibleValuesInRow = new ArrayList<>();

                if (selectedCase.haveValue())
                    continue;

                for (Case oneCase : oneStructure.getCases())
                    if (oneCase != selectedCase)
                        possibleValuesInRow.addAll(oneCase.getPossibleValues());

                for (int possibleValue : selectedCase.getPossibleValues()) {
                    if (!(possibleValuesInRow.contains(possibleValue))) {
                        selectedCase.setValue(possibleValue);
                        selectedCase.resolvedMethod = 2;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
