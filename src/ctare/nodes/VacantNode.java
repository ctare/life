package ctare.nodes;

import ctare.core.NodesManager;

import java.util.List;

/**
 * Created by ctare on 2020/05/21.
 */
public abstract class VacantNode extends WorkplaceNode<VacantNode> {
    public VacantNode(int amount) {
        super(amount);
    }

    @Override
    public List<VacantNode> getNodes() {
        return NodesManager.get(VacantNode.class);
    }
}
