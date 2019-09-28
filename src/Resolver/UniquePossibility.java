package Resolver;

import Structures.Case;
import Structures.Structure;

import java.util.List;

public class UniquePossibility {

    private List<Structure> blocks;

    public UniquePossibility(List<Structure> blocks) {
        this.blocks = blocks;
    }

    public void resolve() {
        for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
                if (selectedCase.haveValue())
                    continue;

                if (selectedCase.getPossibleValues().size() == 1) {
                    selectedCase.setValue(selectedCase.getPossibleValues().get(0));
                    selectedCase.resolvedMethod = 1;
                    return;
                }
            }
        }
    }
}
