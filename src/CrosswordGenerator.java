import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CrosswordGenerator {
    private static final int MIN_WORD_LENGTH = 3;
    private static final int MAX_SOLUTION_LENGTH = 10;

    private final Random random = new Random();

    public CrosswordPuzzle generate(WordData wordData) {
        List<WordEntry> allEntries = wordData.getEntries();
        if (allEntries.isEmpty()) {
            throw new IllegalArgumentException("no words loaded");
        }

        List<WordEntry> candidates = new ArrayList<>();
        for (WordEntry entry : allEntries) {
            int len = entry.getWord().length();
            if (len >= MIN_WORD_LENGTH && len <= MAX_SOLUTION_LENGTH) {
                candidates.add(entry);
            }
        }

        if (candidates.isEmpty()) {
            throw new IllegalArgumentException("no solution candidates (length 3-10)");
        }

        for (int attempt = 0; attempt < candidates.size() * 2; attempt++) {
            WordEntry solutionEntry = candidates.get(random.nextInt(candidates.size()));
            CrosswordPuzzle puzzle = tryBuild(solutionEntry, allEntries);
            if (puzzle != null) {
                return puzzle;
            }
        }

        throw new IllegalStateException("could not generate crossword with available words");
    }

    private CrosswordPuzzle tryBuild(WordEntry solutionEntry, List<WordEntry> allEntries) {
        String solution = solutionEntry.getWord();
        Set<String> usedWords = new HashSet<>();
        usedWords.add(solution);
        List<CrosswordRow> rows = new ArrayList<>();

        for (int i = 0; i < solution.length(); i++) {
            char targetChar = solution.charAt(i);
            List<WordEntry> possibleRows = new ArrayList<>();

            for (WordEntry entry : allEntries) {
                String word = entry.getWord();
                if (!usedWords.contains(word) && word.indexOf(targetChar) >= 0) {
                    possibleRows.add(entry);
                }
            }

            if (possibleRows.isEmpty()) {
                return null;
            }

            WordEntry chosen = possibleRows.get(random.nextInt(possibleRows.size()));
            int crossingIndex = chosen.getWord().indexOf(targetChar);
            rows.add(new CrosswordRow(chosen, crossingIndex, targetChar, chosen.getDescription()));
            usedWords.add(chosen.getWord());
        }

        return new CrosswordPuzzle(solution, rows);
    }
}
