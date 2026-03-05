import java.util.List;

public class WordData {
    private final List<WordEntry> entries;

    public WordData(List<WordEntry> entries) {
        this.entries = entries;
    }

    public List<WordEntry> getEntries() {
        return entries;
    }
}
