package resolver.methods;

import structures.Case;
import structures.Structure;

import java.util.List;

class UniquePossibility {

    private List<Structure> structure;

    UniquePossibility(List<Structure> structure) {
        this.structure = structure;
    }

    boolean resolve() {
        for (Structure block : structure) {
            for (Case selectedCase : block.getCases()) {
                if (selectedCase.haveValue())
                    continue;

                if (selectedCase.getPossibleValues().size() == 1) {
                    selectedCase.setValue(selectedCase.getPossibleValues().get(0));
                    selectedCase.resolvedMethod = 1;
                    return true;
                }
            }
        }

        return false;
    }
}
