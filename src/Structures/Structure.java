package Structures;

import java.util.List;

public abstract class Structure {

    public abstract boolean existIn(int value);

    public abstract Case existExclusivePairOneNumber(Case caseToTest);

    public abstract Case existExclusivePairTwoNumbers(Case caseToTest);

    public abstract int getId();

    public abstract void addCase(Case oneCase);

    public abstract List<Case> getCases();
}
