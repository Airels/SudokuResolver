package Resolver.Methods;

import Structures.Case;
import Structures.Structure;

import java.util.List;

class UniquePossibility implements ResolveMethods{

    private List<Structure> blocks;

    UniquePossibility(List<Structure> blocks) {
        this.blocks = blocks;
    }

    @Override
    public boolean resolve() {
        for (Structure block : blocks) {
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
