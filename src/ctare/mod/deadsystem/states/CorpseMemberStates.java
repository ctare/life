package ctare.mod.deadsystem.states;

import ctare.Main;
import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.state.PickUpState;
import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.WorkplaceNodeStates;

import java.util.ArrayList;

/**
 * Created by ctare on 2020/05/24.
 */
public class CorpseMemberStates extends WorkplaceNodeStates {
    private ArrayList<Corpse> corpses = new ArrayList<>();
    private ArrayList<Corpse> dropCorpses = new ArrayList<>();
    private ArrayList<Corpse> addCorpses = new ArrayList<>();
    public CorpseMemberStates(int amount) {
        super(amount);
    }

    @Override
    public void update(WorkplaceNode holder) {
        corpses.addAll(addCorpses);
        addCorpses.forEach(Corpse::activate);
        addCorpses.clear();
        corpses.removeAll(dropCorpses);
        dropCorpses.clear();

        corpses.forEach(Corpse::draw);

        if (GraveNode.getCnt() > 0) {
            int unitIndex = 0;
            for (Corpse corpse : corpses) {
                while (unitIndex < holder.member.size()) {
                    UnitNode unit = holder.member.get(unitIndex++);
                    if (Main.instance().isHit(unit, holder)) {
                        unit.state = new PickUpState(unit, holder, corpse);
                        break;
                    }
                }
            }
        }
    }

    public void register(Corpse corpse) {
        this.addCorpses.add(corpse);
    }

    public void unregister(Corpse corpse) {
        this.dropCorpses.add(corpse);
        corpse.deactivate();
    }
}
