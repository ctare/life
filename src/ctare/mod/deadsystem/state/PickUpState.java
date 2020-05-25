package ctare.mod.deadsystem.state;

import ctare.Main;
import ctare.core.Node;
import ctare.core.NodesManager;
import ctare.mod.bagsystem.BagStates;
import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.purpose.Abandon;
import ctare.mod.deadsystem.states.CorpseMemberStates;
import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.State;
import ctare.utils.Calc;

import java.util.List;

/**
 * Created by ctare on 2020/05/25.
 */
public class PickUpState extends State<WorkplaceNode> {
    private Corpse target;
    public PickUpState(UnitNode unit, WorkplaceNode where, Corpse target) {
        super(unit, where);
        this.target = target;
        where.states.get(CorpseMemberStates.class).unregister(target);
        target.activate();
        target.state = new State<Node>(target, unit) {
            @Override
            public void update() {
            }
        };
    }

    @Override
    public void update() {
        target.draw();
        if (Main.instance().isHit(unit, target)) {
            target.deactivate();

            BagStates bag = unit.states.get(BagStates.class);

            BagStates.Item item = new BagStates.Item(target)
                    .setItemColor(target.getColor())
                    .setRadius(target.getRadius());
            bag.add(item);
            target.states.get(BagStates.class).transfer(bag);

            List<GraveNode> nodes = NodesManager.get(GraveNode.class);
            assert nodes.size() > 0;
            unit.forceReadyFor(Calc.getNode(nodes), new Abandon());

        } else {
            unit.move(target.getPosition(), unit.getSpeed());

            State.Manager.call(PickUpState.class, this);
        }
    }
}
