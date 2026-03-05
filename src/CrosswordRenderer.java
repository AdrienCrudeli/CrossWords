import java.util.ArrayList;
import java.util.List;

public class CrosswordRenderer {
    public String renderComplete(CrosswordPuzzle puzzle) {
        int leftWidth = maxLeftWidth(puzzle.getRows());
        List<String> lines = new ArrayList<>();
        for (CrosswordRow row : puzzle.getRows()) {
            String word = row.getEntry().getWord();
            int crossingIndex = row.getCrossingIndex();

            String prefix = word.substring(0, crossingIndex);
            String suffix = word.substring(crossingIndex + 1);
            String line = repeat(" ", leftWidth - prefix.length())
                    + prefix + "|" + row.getSolutionChar() + "|" + suffix;
            lines.add(line);
        }
        return String.join(System.lineSeparator(), lines);
    }

    public String renderBlurredWithDescriptions(CrosswordPuzzle puzzle) {
        int leftWidth = maxLeftWidth(puzzle.getRows());

        List<String> boardLines = new ArrayList<>();
        int maxBoardWidth = 0;
        for (CrosswordRow row : puzzle.getRows()) {
            String word = row.getEntry().getWord();
            int crossingIndex = row.getCrossingIndex();

            int prefixLength = crossingIndex;
            int suffixLength = word.length() - crossingIndex - 1;
            String boardLine = repeat(" ", leftWidth - prefixLength)
                    + repeat(".", prefixLength) + "|.|" + repeat(".", suffixLength);
            boardLines.add(boardLine);
            maxBoardWidth = Math.max(maxBoardWidth, boardLine.length());
        }

        List<String> output = new ArrayList<>();
        for (int i = 0; i < puzzle.getRows().size(); i++) {
            CrosswordRow row = puzzle.getRows().get(i);
            String boardLine = boardLines.get(i);
            String clue = row.getClue();
            output.add(padRight(boardLine, maxBoardWidth + 2) + clue);
        }

        return String.join(System.lineSeparator(), output);
    }

    private int maxLeftWidth(List<CrosswordRow> rows) {
        int max = 0;
        for (CrosswordRow row : rows) {
            max = Math.max(max, row.getCrossingIndex());
        }
        return max;
    }

    private String padRight(String text, int minWidth) {
        if (text.length() >= minWidth) {
            return text;
        }
        return text + repeat(" ", minWidth - text.length());
    }

    private String repeat(String value, int count) {
        if (count <= 0) {
            return "";
        }
        return value.repeat(count);
    }
}
