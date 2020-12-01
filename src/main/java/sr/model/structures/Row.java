package sr.model.structures;

public class Row extends Structure {

    public Row(int id) {
        super(id);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : super.getCases()) {
            result.append(" ").append(selectedCase.getValue()).append(" ");

            if (selectedCase.getColumnID() == 2 || selectedCase.getColumnID() == 5)
                result.append("|");
        }

        return result.toString();
    }
}
