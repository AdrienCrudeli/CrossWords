# Crossword generator

Generates a crossword from a word list: picks a solution word, then builds one row per letter,
each row holding another word that crosses the solution at that letter. Prints the finished grid
and a blanked version with the clues.

## How it works

`CrosswordGenerator` picks a candidate solution at random among the words of 3 to 10 letters.
For each letter of that solution it looks for an unused word containing that letter and places it
so the two cross there. If a letter cannot be matched, the attempt is abandoned and another
solution word is tried, up to twice the number of candidates before giving up.

That retry loop matters: whether a crossword can be built at all depends on the overlap between
the solution's letters and the rest of the list. With a short list, most candidate solutions fail
and only a few work.

`CrosswordRenderer` produces two views of the same puzzle, the complete grid, and the blanked
grid with the clue for each row underneath.

## Running it

Requires a JDK.

```bash
javac -d out src/*.java
java -cp out Main
```

By default it reads `words.txt` and `descriptions.txt` from the working directory. Both can be
overridden:

```bash
java -cp out Main my-words.txt my-descriptions.txt
```

## Input format

Two parallel files, one entry per line, matched by line number: `words.txt` holds the words and
`descriptions.txt` the matching clue. The repository ships a 15-word English sample.

```
# words.txt          # descriptions.txt
dishes               glass or ceramic  a more or less concave vessel from which food is served
table                a piece of furniture consisting of a smooth flat slab fixed on legs
soup                 a liquid food especially with a meat, fish, or vegetable stock as a base
```

Fifteen words is close to the lower bound — generation fails fairly often on a list that small.
A few dozen words makes it reliable.

## Structure

`WordRepository` reads the files, `WordEntry` and `WordData` hold them, `CrosswordGenerator`
builds the puzzle, `CrosswordPuzzle` and `CrosswordRow` represent the result, and
`CrosswordRenderer` turns it into text. Reading, generating and rendering stay separate, so the
output format can change without touching the generation logic.
