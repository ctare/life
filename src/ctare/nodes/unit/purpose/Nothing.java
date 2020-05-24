package ctare.nodes.unit.purpose;

import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/20.
 */
public class Nothing extends Purpose<WorkplaceNode> {
    public Nothing() {
        super(0);
    }

    @Override
    public State getState(UnitNode unit, WorkplaceNode where) {
        return new Free<WorkplaceNode>(unit, where);
    }
}
