package ctare.mod.deadsystem.state;

import ctare.mod.deadsystem.states.AgeStates;
import ctare.mod.deadsystem.states.CorpseMemberStates;
import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/23.
 */
public class DeadState extends State<WorkplaceNode> {
    private AgeStates unitStates;
    public DeadState(UnitNode unit, WorkplaceNode where) {
        super(unit, where);

        assert unit instanceof Corpse;
        this.unitStates = unit.states.get(AgeStates.class);
    }

    @Override
    public void update() {
        if (this.unitStates.getAge() == 0) {
            unit.place.states.get(CorpseMemberStates.class).unregister((Corpse) unit);
        } else {
            State.Manager.call(DeadState.class, this);
        }
    }
}
