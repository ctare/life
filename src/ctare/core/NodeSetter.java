package ctare.core;

/**
 * Created by ctare on 2020/05/17.
 */
public class NodeSetter {
    private Node node;

    public boolean setPosition(float x, float y) {
        if (this.node != null) {
            this.node.setPosition(x, y);
            return true;
        }
        return false;
    }

    public boolean addAmount(int x) {
        if (this.node != null) {
            this.node.setAmount(this.node.getAmount() + x);
            return true;
        }
        return false;
    }

    public void register(Graph graph, Edge edge) {
        graph.register(this.node, edge);
        this.node = null;
    }

    public void register(Graph graph) {
        this.register(graph, null);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void clear() {
        this.node = null;
    }
}
