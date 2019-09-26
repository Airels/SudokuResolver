package fr.airels.pt;

import java.util.ArrayList;
import java.util.List;

public class Block implements Structure {
    List<Case> cases = new ArrayList<>();
    int id;

    public Block(int id) {
        this.id = id;
    }

    public void addCase(Case oneCase) {
        cases.add(oneCase);
    }

    public Case getCase(int index) {
        return cases.get(index);
    }
}
