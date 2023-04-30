import java.util.ArrayList;

public class Node {
    private ArrayList<Node> nextNodes = new ArrayList<Node>();
    private char value;

    public Node(char value) {
        this.value = value;
    }

    public Node searchNextNodes(char value) {
        if (!this.nextNodes.isEmpty()) {
            for (Node node : this.nextNodes) {
                if (node.value == value) {
                    return node;
                }
            }
        }
        Node newNode = new Node(value);
        this.nextNodes.add(newNode);
        return newNode;
    }

    public boolean hasNextValue(char value) {
        if (!this.nextNodes.isEmpty()) {
            for (Node node : this.nextNodes) {
                if (node.value == value) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Node> getNodeList() {
        return this.nextNodes;
    }

    public char getValue() {
        return this.value;
    }

    public static void main(String[] args) {
        Node node = new Node('a');
        node.searchNextNodes('b');
    }
}
