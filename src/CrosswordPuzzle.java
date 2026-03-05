import java.util.Collections;
import java.util.List;

public class CrosswordPuzzle {
    private final String solution;
    private final List<CrosswordRow> rows;

    public CrosswordPuzzle(String solution, List<CrosswordRow> rows) {
        this.solution = solution;
        this.rows = Collections.unmodifiableList(rows);
    }

    public String getSolution() {
        return solution;
    }

    public List<CrosswordRow> getRows() {
        return rows;
    }
}
