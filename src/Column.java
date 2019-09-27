import java.util.ArrayList;
import java.util.List;

public class Column implements Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    Column(int id) {
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
        for (Case caseToTest : cases) {
            if (caseToTest.getValue() == value)
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String result = "";

        for (Case selectedCase : cases) {
            result += " " + selectedCase.getValue() + "\n";

            if (selectedCase.getRow() == 2 || selectedCase.getRow() == 5)
                result += "---\n";
        }

        return result;
    }
}
