package ctare.mod.healthsystem;

import ctare.core.NodesManager;
import ctare.nodes.VacantNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.purpose.Nothing;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.utils.Calc;

import java.util.List;

/**
 * Created by ctare on 2020/05/23.
 */
public class Heal extends Free<RestNode> {
    public Heal(UnitNode unit, RestNode where) {
        super(unit, where);
    }

    @Override
    public void update() {
        super.randomWalk(this.unit.getSpeed() * 0.3f);
        HealthStates.heal(this.unit, 30);

        if (this.unit.states.get(HealthStates.class).power.isFull()) {
            List<VacantNode> nodes = NodesManager.getVacancy(VacantNode.class);
            if (nodes.size() == 0) {
                nodes = NodesManager.get(VacantNode.class);
            }
            this.unit.forceReadyFor(Calc.getNode(nodes), new Nothing());
        }

        State.Manager.call(Heal.class, this);
    }
}
