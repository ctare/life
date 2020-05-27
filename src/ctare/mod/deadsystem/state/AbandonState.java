package ctare.mod.deadsystem.state;

import ctare.mod.bagsystem.BagStates;
import ctare.mod.bagsystem.Item;
import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.states.StatesValue;

import java.util.ArrayList;

/**
 * Created by ctare on 2020/05/25.
 */
public class AbandonState extends Free<GraveNode> {
    private StatesValue processingTime = new StatesValue(0, 200);
    private ArrayList<Corpse> corpses = new ArrayList<>();
    private BagStates bag;
    public AbandonState(UnitNode unit, GraveNode where) {
        super(unit, where);
        bag = unit.states.get(BagStates.class);
        for (Item o : bag.items()) {
            if (o instanceof Corpse) {
                corpses.add((Corpse) o);
            }
        }
    }

    @Override
    public void update() {
        randomWalk(unit.getSpeed() * 0.5f);
        if (corpses.size() > 0) {
            if (processingTime.isFull()) {
                bag.remove(corpses.remove(0));
                processingTime.value = 0;
            } else {
                processingTime.add(1);
            }
            State.Manager.call(AbandonState.class, this);
        } else {
            unit.state = new MourningState(unit, where);
        }
    }
}
