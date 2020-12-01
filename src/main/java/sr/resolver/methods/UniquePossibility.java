package sr.resolver.methods;

import sr.resolver.Resolver;
import sr.structures.Case;
import sr.structures.Structure;

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
