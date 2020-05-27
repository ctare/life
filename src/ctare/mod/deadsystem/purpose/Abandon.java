package ctare.mod.deadsystem.purpose;

import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.state.AbandonState;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.purpose.Purpose;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/25.
 */
public class Abandon extends Purpose<GraveNode> {
    public static final int PRIORITY = 30;
    public Abandon() {
        super(PRIORITY);
    }

    @Override
    public State getState(UnitNode unit, GraveNode where) {
        return new AbandonState(unit, where);
    }
}
