package Resolver.Exclusion;

import Structures.Case;
import Structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class ExclusionOnColumn {

    private List<Structure> columns;

    public ExclusionOnColumn(List<Structure> columns) {
        this.columns = columns;
    }

    public void resolve() {
        for (Structure column : columns) {
            for (Case selectedCase : column.getCases()) {
                List<Integer> possibleValuesInColumn = new ArrayList<>();

                if (selectedCase.haveValue())
                    continue;

                for (Case oneCase : column.getCases())
                    if (oneCase != selectedCase)
                        possibleValuesInColumn.addAll(oneCase.getPossibleValues());


                for (int possibleValue : selectedCase.getPossibleValues()) {
                    if (!(possibleValuesInColumn.contains(possibleValue))) {
                        selectedCase.setValue(possibleValue);
                        selectedCase.resolvedMethod = 2;
                        return;
                    }
                }
            }
        }
    }
}
