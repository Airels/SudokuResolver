package fr.airels.pt;

import java.util.ArrayList;
import java.util.List;

public class Case {
    private int x, y, value;
    private List<Integer> possibleValues = new ArrayList<>();

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public boolean isGoodCase(int x, int y) {
        return (x == this.x && y == this.y);
    }
}
