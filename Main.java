public class Main {
    public static void main(String[] args) {

        MakeData.generateTree();
        Board board = new Board();
        board.printBoard();
        SearchBoard sb = new SearchBoard();
        sb.getWordList();
    }
}
