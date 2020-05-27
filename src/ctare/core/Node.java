package ctare.core;

import ctare.utils.FilterIterator;
import ctare.utils.MappedArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ctare on 2020/05/17.
 */
public class Node extends RoundObject {
    public final MappedArray<Node, Integer> distances = new MappedArray<>(Comparator.comparingInt(MappedArray.KeyValue::getValue));

    private int amount;
    private Edge parent;



    protected final int SIZE = 5;
    protected int getSize() {
        return SIZE;
    }

    private static final Color COLOR = new Color(255, 255, 255);

    @Override
    public Color getColor() {
        return Node.COLOR;
    }

    public Node(int amount) {
        super();
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int getRadius() {
        return this.amount * this.getSize();
    }

    public Edge getParent() {
        return parent;
    }

    public void setParent(Edge parent) {
        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    public <T extends Node> List<T> execNodes(Class<T> cls, Predicate<T> p) {
        List<T> visited = new ArrayList<>();
        for (MappedArray.KeyValue<Node, Integer> v : FilterIterator.filter(this.distances, e -> cls.isAssignableFrom(e.getKey().getClass()))) {
            T node = (T) v.getKey();
            if (p.test(node)) {
                return null;
            } else {
                visited.add(node);
            }
        }
        return visited.size() == 0 ? null : visited;
    }
}
