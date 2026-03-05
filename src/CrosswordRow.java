public class CrosswordRow {
    private final WordEntry entry;
    private final int crossingIndex;
    private final char solutionChar;
    private final String clue;

    public CrosswordRow(WordEntry entry, int crossingIndex, char solutionChar, String clue) {
        this.entry = entry;
        this.crossingIndex = crossingIndex;
        this.solutionChar = solutionChar;
        this.clue = clue;
    }

    public WordEntry getEntry() {
        return entry;
    }

    public int getCrossingIndex() {
        return crossingIndex;
    }

    public char getSolutionChar() {
        return solutionChar;
    }

    public String getClue() {
        return clue;
    }
}
