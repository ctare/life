package ctare.nodes.unit.states;

import ctare.nodes.WorkplaceNode;
import ctare.utils.Calc;

/**
 * Created by ctare on 2020/05/24.
 */
public class NodeInfoStates extends WorkplaceNodeStates {
    public final StatesValue capacity;

    public NodeInfoStates(int amount) {
        super(amount);
        this.capacity = new StatesValue(amount);
    }

    @Override
    public void update(WorkplaceNode holder) {
        if (holder.member.size() > holder.states.get(NodeInfoStates.class).capacity.value && Calc.bernoulli(1.01f)) {
//            UnitNode victim = Calc.choice(holder.member);
//            Main.instance().dropUnit(victim);
        }
    }
}
