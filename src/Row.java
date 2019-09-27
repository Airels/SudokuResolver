import java.util.ArrayList;
import java.util.List;

public class Row implements Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    public Row(int id) {
        this.id = id;
    }

    public void addCase(Case oneCase) {
        cases.add(oneCase);
    }

    public Case getCase(int index) {
        return cases.get(index);
    }

    public List<Case> getCases() {
        return cases;
    }

    public int getId() {
        return id;
    }

    public boolean existIn(int value) {

        for (int testCase = 0; testCase < 9; testCase++) {
            if (getCase(testCase).getValue() == value)
                return true;
        }

        return false;
    }

    public String toString() {
        String result = "";

        for (Case selectedCase : cases) {
            result += " " + selectedCase.getValue() + " ";

            if (selectedCase.getColumn() == 2 || selectedCase.getColumn() == 5)
                result += "|";
        }

        return result;
    }
}
