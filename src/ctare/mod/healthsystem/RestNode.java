package ctare.mod.healthsystem;

import ctare.core.Color;
import ctare.core.NodesManager;
import ctare.nodes.WorkplaceNode;

import java.util.List;

/**
 * Created by ctare on 2020/05/21.
 */
public class RestNode extends WorkplaceNode<RestNode> {
    private static final Color color = new Color(224, 38, 162);
    public RestNode(int amount) {
        super(amount);
    }

    @Override
    public List<RestNode> getNodes() {
        return NodesManager.get(RestNode.class);
    }

    @Override
    public Color getColor() {
        return RestNode.color;
    }
}
