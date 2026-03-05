import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    public WordData load(String wordsFile, String descriptionsFile) throws IOException {
        List<String> words = readLines(wordsFile);
        List<String> descriptions = readLines(descriptionsFile);

        List<WordEntry> entries = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).trim();
            String description = descriptions.get(i).trim();
            if (!word.isEmpty()) {
                entries.add(new WordEntry(word, description));
            }
        }
        return new WordData(entries);
    }

    private List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
