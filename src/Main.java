public class Main {
    public static void main(String[] args) {
        String wordsFile = args.length > 0 ? args[0] : "words.txt";
        String descriptionsFile = args.length > 1 ? args[1] : "descriptions.txt";

        try {
            WordRepository repository = new WordRepository();
            WordData wordData = repository.load(wordsFile, descriptionsFile);

            CrosswordGenerator generator = new CrosswordGenerator();
            CrosswordPuzzle puzzle = generator.generate(wordData);

            CrosswordRenderer renderer = new CrosswordRenderer();
            System.out.println("Solution word: " + puzzle.getSolution());
            System.out.println();
            System.out.println("Complete crossword:");
            System.out.println(renderer.renderComplete(puzzle));
            System.out.println();
            System.out.println("Blurred crossword with descriptions:");
            System.out.println(renderer.renderBlurredWithDescriptions(puzzle));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
