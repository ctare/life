package ctare.mod.deadsystem.nodes;

import ctare.core.Color;
import ctare.core.NodesManager;
import ctare.nodes.WorkplaceNode;

import java.util.List;

/**
 * Created by ctare on 2020/05/23.
 */
public class GraveNode extends WorkplaceNode<GraveNode> {
    private static final Color color = new Color(30, 30, 30);

    @Override
    public Color getColor() {
        return GraveNode.color;
    }

    public GraveNode(int amount) {
        super(amount);
    }

    @Override
    public List<GraveNode> getNodes() {
        return NodesManager.get(GraveNode.class);
    }
}
