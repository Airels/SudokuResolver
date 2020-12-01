package resolver.methods;

import resolver.Resolver;
import structures.Case;
import structures.Structure;

import java.util.List;

class UniquePossibility implements Method {

    @Override
    public boolean resolve(Resolver resolver) {
        for (Structure block : resolver.getBlocks()) {
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
