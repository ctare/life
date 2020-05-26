package ctare.nodes;

import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.purpose.Nothing;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by ctare on 2020/05/21.
 */
public abstract class VacantNode extends WorkplaceNode {
    public VacantNode(int amount) {
        super(amount);
    }

    public static void free(UnitNode unit) {
        Consumer<VacantNode> proc = node -> unit.forceReadyFor(node, new Nothing());
        List<VacantNode> nodes = unit.place.execNodes(VacantNode.class, node -> {
            if (!node.member.isFull()) {
                proc.accept(node);
                return true;
            }
            return false;
        });

        if (nodes != null) {
            proc.accept(nodes.get(0));
        }
    }
}
