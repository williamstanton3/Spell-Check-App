import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SpellingTrie {
    // root must be public so it can be accessed for the printWords function call in SpellChecker
    public SpellingNode root;
    // creates a default tree; root stores a single space
    public SpellingTrie() {
        root = new SpellingNode(' ');
    }
    // adds the given word to the tree
    public boolean addWord(String word) {
        char [] wordArray = word.toCharArray();
        SpellingNode curr = root;
        // iterate through the wordArray and add each letter as a child of the previous letter
        for (int i = 0; i < wordArray.length; i++) {
            curr.addChild(wordArray[i]);
            curr = curr.getChildAt(wordArray[i]);
        }
        // set correct to true for the last letter in the word
        curr.setCorrect();
        return true;
    }
    // check if the given word exits in the tree
    public boolean checkWord(String word) {
        char [] wordArray = word.toCharArray();
        SpellingNode curr = root;
        // iterate through the wordArray
        for (int i = 0; i < wordArray.length; i++) {
            // return false if at any point a letter in the array is not a child of the previous letter
            if (curr.getChildAt(wordArray[i]) == null) {
                return false;
            }
            // update curr to its child based on the next letter in the array
            curr = curr.getChildAt(wordArray[i]);
        }
        // only return true if the final letter in the given word is the final letter of the word in the tree
        if (curr.getCorrect()) {
            return true;
        }
        return false;
    }

    // print all the words in the tree in alphabetical order
    public void printWords(String subWord, SpellingNode c) {
        // if c is the end of a word, print the current subword
        if (c.getCorrect()) {
            System.out.println(subWord);
        }
        // traverse children of c
        for (char ch = 'a'; ch <= 'z'; ch++) {
            // gets the current child node
            SpellingNode currNode = c.getChildAt(ch);
            // if currNode is not null, it means there is a child with that char value
            if (currNode != null) {
                // recursively call printWords but add the current char to the end of the subWord
                printWords(subWord + ch, currNode);
            }
        }
    }
}
