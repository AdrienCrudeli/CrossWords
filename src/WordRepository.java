import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordRepository {
    public WordData load(String wordsFile, String descriptionsFile) throws IOException {
        DynamicArray<String> words = readLines(wordsFile);
        DynamicArray<String> descriptions = readLines(descriptionsFile);

        DynamicArray<WordEntry> entries = new DynamicArray<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i).trim();
            String description = descriptions.get(i).trim();
            if (!word.isEmpty()) {
                entries.add(new WordEntry(word, description));
            }
        }
        return new WordData(entries);
    }

    private DynamicArray<String> readLines(String filePath) throws IOException {
        DynamicArray<String> lines = new DynamicArray<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
