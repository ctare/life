package ctare.mod.worksystem;

import ctare.Main;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.utils.Calc;

/**
 * Created by ctare on 2020/05/20.
 */
public class Working extends Free<ResourceNode> {
    public Working(UnitNode unit, ResourceNode where) {
        super(unit, where);
    }

    @Override
    public void update() {
        super.randomWalk(this.unit.getSpeed() * 0.5f);

        if (this.where.isGrowthPhase()) {
            if (Calc.bernoulli(0.05f)) {
                this.where.growth(1);
            }

            State.Manager.call(Working.class, this);
        } else {
            this.unit.forceReadyFor(Main.instance().root, new Report(this.where.gather(this.unit.states.get(ShippingStates.class).capacity.value)));
        }
    }
}
