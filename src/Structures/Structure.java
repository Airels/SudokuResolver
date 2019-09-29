package Structures;

import java.util.List;

public interface Structure {

    boolean existIn(int value);

    Case existExclusivePair(Case caseToTest);

    int getId();

    void addCase(Case oneCase);

    List<Case> getCases();
}
