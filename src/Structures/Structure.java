package Structures;

import java.util.List;

public interface Structure {

    boolean existIn(int value);

    int getId();

    void addCase(Case oneCase);

    List<Case> getCases();
}
