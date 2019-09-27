import java.util.ArrayList;
import java.util.List;

public class Block implements Structure {
    private List<Case> cases = new ArrayList<>();
    private int id;

    public Block(int id) {
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

    public static int resolveIDBlock(int row, int column) {
        int idBlock = 0;

        if (row < 3)
            idBlock += 0;
        else if (row < 6)
            idBlock += 1;
        else
            idBlock += 2;

        if (column < 3)
            idBlock += 0;
        else if (column < 6)
            idBlock = 3;
        else
            idBlock = 6;

        return idBlock;
    }
}
