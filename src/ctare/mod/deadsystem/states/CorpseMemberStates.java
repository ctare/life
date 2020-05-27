package ctare.mod.deadsystem.states;

import ctare.Main;
import ctare.mod.bagsystem.BagStates;
import ctare.mod.bagsystem.Item;
import ctare.mod.bagsystem.ItemMemberStates;
import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.purpose.Abandon;
import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;

import java.util.stream.Collectors;

/**
 * Created by ctare on 2020/05/24.
 */
public class CorpseMemberStates extends ItemMemberStates<Corpse> {
    public CorpseMemberStates(int amount) {
        super(amount);
    }

    @Override
    public void update(WorkplaceNode holder) {
        super.update(holder);
        if (GraveNode.getCnt() > 0) {
            int unitIndex = 0;
            for (Corpse corpse : items) {
                if (corpse.hasOwner()) {
                    continue;
                }

                while (unitIndex < holder.member.size()) {
                    UnitNode unit = holder.member.get(unitIndex++);
                    if (Main.instance().isHit(unit, holder)) {
                        if (unit.purpose.priority < Abandon.PRIORITY) {
                            unit.purpose = new Abandon();
                            pickUp(unit, corpse, () -> {
                                corpse.getOrigin().states.get(BagStates.class).transfer(unit.states.get(BagStates.class));

                                holder.execNodes(GraveNode.class, node -> {
                                    unit.forceReadyFor(node, new Abandon());
                                    return true;
                                });
                            });
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.items.stream().map(Item::hasOwner).map(Object::toString).collect(Collectors.joining(", "));
    }
}
