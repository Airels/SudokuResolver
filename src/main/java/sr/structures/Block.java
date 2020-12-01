package sr.structures;

public class Block extends Structure {

    public Block(int id) {
        super(id);
    }

    public static int resolveIDBlock(int row, int column) {
        int idBlock = 0;

        if (row >= 6)
            idBlock += 2;
        else if (row >= 3)
            idBlock += 1;

        if (column >= 6)
            idBlock += 6;
        else if (column >= 3)
            idBlock += 3;

        return idBlock;
    }

    public boolean existIn(int value) {
        for (Case caseToTest : super.getCases()) {
            if (caseToTest.getValue() == value)
                return true;
        }

        return false;
    }

    @Override
    public Case existExclusivePairOneNumber(Case caseToTest) {
        throw new RuntimeException("Block Structure are not allowed to use Exclusive Pair Methods");
    }

    @Override
    public Case existExclusivePairTwoNumbers(Case caseToTest) {
        throw new RuntimeException("Block Structure are not allowed to use Exclusive Pair Methods");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Case selectedCase : super.getCases()) {
            result.append(" ").append(selectedCase.getValue()).append(" ");

            if (selectedCase.getColumnID() == 2 || selectedCase.getColumnID() == 5)
                result.append("\n");
        }

        return result.toString();
    }
}
