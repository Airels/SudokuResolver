import java.util.ArrayList;
import java.util.List;

public class Case {
    int resolvedMethod = 0; // 0 = Not resolved, 1 = Resolved with unique possibility, 2 = Resolved with multiple possibilities
    private int row, column, value;
    private List<Integer> possibleValues = new ArrayList<>();

    public Case(int row, int column) {
        this.row = row;
        this.column = column;
        this.value = 0;
    }

    public void addPossibleValue(int value) {
        possibleValues.add(value);
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

    public void clearPossiblesValues() {
        possibleValues.clear();
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public boolean haveValue() {
        return (value != 0);
    }

    public boolean isGoodCase(int row, int column) {
        return (row == this.row && column == this.column);
    }
}
