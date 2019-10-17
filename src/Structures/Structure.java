package Structures;

import java.util.List;

public interface Structure {

    boolean existIn(int value);

    Case existExclusivePairOneNumber(Case caseToTest);

    Case existExclusivePairTwoNumbers(Case caseToTest);

    int getId();

    void addCase(Case oneCase);

    List<Case> getCases();
}
