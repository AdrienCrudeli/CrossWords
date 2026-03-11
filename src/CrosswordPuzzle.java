public class CrosswordPuzzle {
    private final String solution;
    private final DynamicArray<CrosswordRow> rows;

    public CrosswordPuzzle(String solution, DynamicArray<CrosswordRow> rows) {
        this.solution = solution;
        this.rows = rows;
    }

    public String getSolution() {
        return solution;
    }

    public DynamicArray<CrosswordRow> getRows() {
        return rows;
    }
}
