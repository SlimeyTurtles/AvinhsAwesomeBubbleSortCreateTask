public class MakeData {
    public static Node node = new Node('a');

    public static void generateTree() {
        for (String word : WordList.wordList) {
            Node newNode = node;
            char[] wordToArray = word.toCharArray();
            for (char letter : wordToArray) {
                newNode = newNode.searchNextNodes(Character.toUpperCase(letter));                
            }
        }

        System.out.println("Data Initialized...");
    }

    public Node getData() {
        return node;
    }

    public static void main(String[] args) {

        generateTree();

        for (Node nextNode : node.getNodeList()) {
            System.out.println(nextNode.getValue());
            if (!nextNode.getNodeList().isEmpty()) {
                node = nextNode;
            }
        }

    }
}