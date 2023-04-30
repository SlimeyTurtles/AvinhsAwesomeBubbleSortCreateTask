import java.lang.Math;

class Board {
    public static char[][] charBoard = new char[4][4];

    public char[] letterList = {
        'A', 'B', 'C', 'D', 'E', 'F', 
        'G', 'H', 'I', 'J', 'K', 'L', 
        'M', 'N', 'O', 'P', 'Q', 'R', 
        'S', 'T', 'U', 'V', 'W', 'X', 
        'Y', 'Z'};

    public Board() {
        generateBoard();
    }

    public void generateBoard() {

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int randInt = (int) Math.floor(Math.random() * 26);
                charBoard[row][col] = letterList[randInt];
            }
        }
    }

    public void printBoard() {

        for (char[] charList : charBoard) {
            for (char c : charList) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        Board board = new Board();

        board.printBoard();
    }
} 