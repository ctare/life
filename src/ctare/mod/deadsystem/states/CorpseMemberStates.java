package ctare.mod.deadsystem;

import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.states.WorkplaceNodeStates;
import ctare.utils.WeakArrayList;

/**
 * Created by ctare on 2020/05/24.
 */
public class CorpseMemberStates extends WorkplaceNodeStates {
    private WeakArrayList<Corpse> corpses = new WeakArrayList<>();
    public CorpseMemberStates(int amount) {
        super(amount);
    }

    @Override
    public void update(WorkplaceNode holder) {
        corpses.forEach(Corpse::draw);
    }

    public void register(Corpse corpse) {
        this.corpses.add(corpse);
    }
}
