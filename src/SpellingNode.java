public class SpellingNode {
    private char value; // the letter stored at this node (root stores a space character)
    private SpellingNode [] children; // an array of 26 children nodes
    private boolean correctWord; // true if this letter indicates the end of a word

    // constructor that initializes the value and the children array (all empty to start)
    public SpellingNode (char letter) {
        value = letter;
        children = new SpellingNode[26];
        correctWord = false;
    }

    // setter method for correctWord
    public void setCorrect() {
        correctWord = true;
    }

    // getter method for correctWord
    public boolean getCorrect() {
        return correctWord;
    }

    // adds a child to this node with the given letter
    public boolean addChild(char letter) {
        // converts the input to lowercase if it is uppercase
        if (Character.isUpperCase(letter)) {
            letter = Character.toLowerCase(letter);
        }

        // returns false if the child already exists
        if (children[(int)letter -97] != null) {
            return false;
        }
        // if the child doesn't exit already
        SpellingNode newNode = new SpellingNode(letter);
        // add to proper location in children array
        // use ascii values to calculate the right index
        children[(int)letter -97] = newNode;
        return true;
    }

    // return the SpellingNode object with the given character
    public SpellingNode getChildAt(char val) {
        // converts the input to lowercase if it is uppercase
        if (Character.isUpperCase(val)) {
            val = Character.toLowerCase(val);
        }
        // use ascii values to find the index that stores the node with the given char
        return children[(int)val -97];
    }

}
