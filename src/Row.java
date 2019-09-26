import java.util.ArrayList;
import java.util.List;

public class Row implements Structure {
    List<Case> cases = new ArrayList<>();
    int id;

    public Row(int id) {
        this.id = id;
    }

    public void addCase(Case oneCase) {
        cases.add(oneCase);
    }

    public Case getCase(int index) {
        return cases.get(index);
    }
}
