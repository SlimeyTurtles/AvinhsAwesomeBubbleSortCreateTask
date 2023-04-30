import java.util.ArrayList;
import java.util.HashMap;

public class SearchBoard {
        private ArrayList<Character> charList = new ArrayList<Character>();
        private ArrayList<int[]> pathList = new ArrayList<int[]>();
        private Node currentNode;
        private boolean[][] booleanBoard;

        public static HashMap<String, int[][]> wordList = new HashMap<String, int[][]>();
        
        public SearchBoard() {
            this.currentNode = MakeData.node;
            boolean[][] bb = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
            };
            this.booleanBoard = bb;
        }

        public SearchBoard(Node currentNode, boolean[][] bb, ArrayList<Character> charList, ArrayList<int[]> pathList) {
            this.currentNode = currentNode;
            this.booleanBoard = bb;
            this.charList = charList;
            this.pathList = pathList;
        }

        private void searchNearbyLetters(int row, int col) {

            // dont test edge cases (ie dont test points below if touching bottom of board)
            // dont look at this this is the worst written code of my life
            if (row != 0) { // bottom
                if (booleanBoard[row-1][col] == false) { 
                    testSpace(row-1, col);
                }
            } if (row != 0 && col != 3) { // top right
                if (booleanBoard[row-1][col+1] == false) { 
                    testSpace(row-1, col+1);
                } 
            } if (col != 3) { // right
                if (booleanBoard[row][col+1] == false) {
                    testSpace(row, col+1); 
                }
            } if (row != 3 && col != 3) { // bottom right
                if (booleanBoard[row+1][col+1] == false) {
                    testSpace(row+1, col+1); 
                }  
            } if (row != 3) { // bottom
                if (booleanBoard[row+1][col] == false) {
                    testSpace(row+1, col); 
                }
            } if (row != 3 && col != 0) { // bottom left
                if (booleanBoard[row+1][col-1] == false) {
                    testSpace(row+1, col-1); 
                }
            } if (col != 0) { // left
                if (booleanBoard[row][col-1] == false) {
                    testSpace(row, col-1); 
                }
            } if (row != 0 && col != 0) { // top left
                if (booleanBoard[row-1][col-1] == false) {
                   testSpace(row-1, col-1); 
                }
            }
            
            saveWord();
        }

        // Tests if the given values can form into a word and if it should continue this branch
        // currentNode and booleanBoard are passed so that they persist until all iterations are complete
        private void testSpace(int row, int col) {
            char value = Board.charBoard[row][col];

            String wordString = "Testing: ";
            for (char letter : charList) {
                wordString = wordString + letter;
            }
            System.out.println(wordString + value);

            if (this.currentNode.hasNextValue(value)) {
                this.charList.add(value);
                this.currentNode = this.currentNode.searchNextNodes(value);
                this.booleanBoard[row][col] = true;
                SearchBoard sb = new SearchBoard(this.currentNode.searchNextNodes(value), this.booleanBoard, this.charList, this.pathList);
                sb.searchNearbyLetters(row, col);
            }
            // saveWord();
            System.out.println("Saving word");
        }
        // reformats the data out of ArrayLists and into Word objects
        private void saveWord() {
            
            // format the charList into a string
            String wordString = "";
            for (char letter : charList) {
                wordString = wordString + letter;
            }

            // format the pathList into a 2d array
            int pathSize = pathList.size();
            int[][] path = new int[pathSize][2];
            for (int i = 0; i < pathSize; i++) {
                path[i] = pathList.get(i); 
            }

            // save them into a word and add it to wordList
            wordList.put(wordString, path);
            System.out.println(wordString);
        }

        public void getWordList() {
            for (int col = 0; col < 4; col++) {
                for (int row = 0; row < 4; row++) {
                    System.out.println("now testing: " + row + " " + col);
                    SearchBoard sb = new SearchBoard();
                    sb.testSpace(row, col);
                    System.out.println();
                }
            }
        }
}
