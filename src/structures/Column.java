package structures;

import java.util.ArrayList;
import java.util.List;

public class Column extends Structure {

    public Column(int id) {
        super(id);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : super.getCases()) {
            result.append(" ").append(selectedCase.getValue()).append("\n");

            if (selectedCase.getRowID() == 2 || selectedCase.getRowID() == 5)
                result.append("---\n");
        }

        return result.toString();
    }
}
