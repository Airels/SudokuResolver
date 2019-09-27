import java.util.List;

public interface Structure {

    boolean existIn(int value);

    int getId();

    void addCase(Case oneCase);

    Case getCase(int index);

    List<Case> getCases();
}
