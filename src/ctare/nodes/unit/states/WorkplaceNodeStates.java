package ctare.nodes.unit.states;

import ctare.nodes.WorkplaceNode;

/**
 * Created by ctare on 2020/05/23.
 */
public abstract class WorkplaceNodeStates extends States<WorkplaceNode> {
    public WorkplaceNodeStates(int amount) {
    }

    static {
        WorkplaceNode.statesManager.register(NodeInfoStates.class);
    }
}
