package Structures;

import java.util.List;

public abstract class Structure {

    abstract boolean existIn(int value);

    abstract Case existExclusivePair(Case caseToTest, int numberOfPairs);

    abstract int getId();

    abstract void addCase(Case oneCase);

    abstract List<Case> getCases();
}
