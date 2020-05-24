package ctare.mod.healthsystem;

import ctare.mod.healthsystem.RestNode;
import ctare.nodes.unit.UnitNode;
import ctare.mod.healthsystem.Heal;
import ctare.nodes.unit.purpose.Purpose;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/23.
 */
public class Rest extends Purpose<RestNode> {
    @Override
    public State getState(UnitNode unit, RestNode where) {
        return new Heal(unit, where);
    }
}
