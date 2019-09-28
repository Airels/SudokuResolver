package Resolver.Exclusion;

import Structures.Case;
import Structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class ExclusionOnBlock {

    private List<Structure> blocks;

    public ExclusionOnBlock(List<Structure> blocks) {
        this.blocks = blocks;
    }

    public void resolve() {
        for (Structure block : blocks) {
            for (Case selectedCase : block.getCases()) {
                List<Integer> possibleValuesInBlock = new ArrayList<>();

                if (selectedCase.haveValue())
                    continue;

                for (Case oneCase : block.getCases()) {
                    if (oneCase != selectedCase)
                        possibleValuesInBlock.addAll(oneCase.getPossibleValues());
                }

                for (int possibleValue : selectedCase.getPossibleValues()) {
                    if (!(possibleValuesInBlock.contains(possibleValue))) {
                        selectedCase.setValue(possibleValue);
                        selectedCase.resolvedMethod = 2;
                        return;
                    }
                }
            }
        }
    }
}
