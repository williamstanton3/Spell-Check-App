import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellChecker {
    private static SpellingTrie myTree; // the tree of correct words

    // read the given file and spell check its contents
    public static int checkWords(String filename) throws FileNotFoundException {
        int numMisspelled = 0;
        // use a scanner to read the contents of the given file
        File f1 = new File(filename);
        Scanner sc = new Scanner(f1);
        // loop through each string of the file and find misspelled words
        while (sc.hasNextLine()) {
            String currWord = sc.next();
            // remove all punctuation from the current word
            currWord = currWord.replaceAll("[\\p{P}-]","");
            // use checkWord to see if the word is misspelled
            if (!myTree.checkWord(currWord)) {
                System.out.println(currWord);
                numMisspelled++;
            }
        }
        return numMisspelled;
    }

    // reads the words in the given file and adds them to myTree
    public static void readWords(String filename) throws FileNotFoundException {
        File f2 = new File(filename);
        Scanner sc = new Scanner(f2);
        // iterate through file, adding each word to myTree
        while (sc.hasNextLine()) {
            String currWord = sc.nextLine();
            // remove all punctuation from the current word
            currWord = currWord.replaceAll("[\\p{P}-]","");
            myTree.addWord(currWord);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        myTree = new SpellingTrie();
        // reads in all the words form dictionary.txt, a text file of all correctly spelled english words
        readWords("dictionary.txt");
        // prints all the words alphabetically in dictionary.txt
        myTree.printWords(" ",myTree.root);
        // prints all misspelled words in testing.txt and the number of misspelled words
        System.out.println(checkWords("testing.txt"));
    }
}
