package resolver.methods;

import structures.Case;
import structures.Structure;

import java.util.ArrayList;
import java.util.List;

class Exclusion {
    private List<Structure> structures;

    Exclusion(List<Structure> structures) {
        this.structures = structures;
    }

    boolean resolve() {
        for (Structure oneStructure : structures) { // oneStructure refers to one row, column or block
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
