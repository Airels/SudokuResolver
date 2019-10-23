package Structures;

import java.util.ArrayList;
import java.util.List;

public class Column extends Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    public Column(int id) {
        this.id = id;
    }

    public void addCase(Case oneCase) {
        cases.add(oneCase);
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
    public Case existExclusivePairOneNumber(Case caseToTest) {
        return ExistenceTests.existExclusivePairOneNumber(cases, caseToTest);
    }

    @Override
    public Case existExclusivePairTwoNumbers(Case caseToTest) {
        return ExistenceTests.existExclusivePairTwoNumbers(cases, caseToTest);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : cases) {
            result.append(" ").append(selectedCase.getValue()).append("\n");

            if (selectedCase.getRow() == 2 || selectedCase.getRow() == 5)
                result.append("---\n");
        }

        return result.toString();
    }
}
