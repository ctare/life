package ctare.mod.deadsystem.state;

import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.nodes.VacantNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/25.
 */
public class MourningState extends Free<GraveNode> {
    private int time = 100;
    public MourningState(UnitNode unit, GraveNode where) {
        super(unit, where);
    }

    @Override
    public void update() {
        super.update();

        this.time = Math.max(0, this.time - 1);
        if (time == 0) {
            VacantNode.free(unit);
        } else {
            State.Manager.call(MourningState.class, this);
        }
    }
}
