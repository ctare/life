package ctare.mod.deadsystem;

import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/23.
 */
public class DeadState extends State<WorkplaceNode> {
    public DeadState(UnitNode unit, WorkplaceNode where) {
        super(unit, where);
    }

    @Override
    public void update() {

    }
}
