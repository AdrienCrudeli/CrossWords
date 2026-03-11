public class WordData {
    private final DynamicArray<WordEntry> entries;

    public WordData(DynamicArray<WordEntry> entries) {
        this.entries = entries;
    }

    public DynamicArray<WordEntry> getEntries() {
        return entries;
    }
}
