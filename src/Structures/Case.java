package Structures;

import java.util.ArrayList;
import java.util.List;

public class Case {
    public int resolvedMethod = 0; // 0 = Not resolved, 1 = Unique, 2 = Exclusion, 3 = ExclusivePairOneNumber, 4 = ExclusivePairTwoNumbers
    private int row, column, value;
    private List<Integer> possibleValues;

    public Case(int row, int column) {
        possibleValues = new ArrayList<>();

        this.row = row;
        this.column = column;
        this.value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean containsValue(int value) {
        return possibleValues.contains(value);
    }

    public int indexOfValue(int value) {
        if (possibleValues.contains(value))
            return possibleValues.indexOf(value);

        return -1;
    }

    public void clearPossiblesValues() {
        possibleValues.clear();
    }

    public void addPossibleValue(int value) {
        possibleValues.add(value);
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public boolean haveValue() {
        return (value != 0);
    }
}
