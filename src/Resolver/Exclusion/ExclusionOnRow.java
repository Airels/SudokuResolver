package Resolver.Exclusion;

import Structures.Case;
import Structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class ExclusionOnRow {

    private List<Structure> rows;

    public ExclusionOnRow(List<Structure> rows) {
        this.rows = rows;
    }

    public void resolve() {
        for (Structure row : rows) {
            for (Case selectedCase: row.getCases()) {
                List<Integer> possibleValuesInRow = new ArrayList<>();

                if (selectedCase.haveValue())
                    continue;

                for (Case oneCase : row.getCases())
                    if (oneCase != selectedCase)
                        possibleValuesInRow.addAll(oneCase.getPossibleValues());

                for (int possibleValue : selectedCase.getPossibleValues()) {
                    if (!(possibleValuesInRow.contains(possibleValue))) {
                        selectedCase.setValue(possibleValue);
                        selectedCase.resolvedMethod = 2;
                        return;
                    }
                }
            }
        }
    }
}
