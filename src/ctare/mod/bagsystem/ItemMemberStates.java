package ctare.mod.bagsystem;

import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.states.WorkplaceNodeStates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctare on 2020/05/27.
 */
public abstract class ItemMemberStates extends WorkplaceNodeStates {
    private List<Item> items = new ArrayList<>();
    private List<Item> dropItems = new ArrayList<>();
    private List<Item> addItems = new ArrayList<>();
    public ItemMemberStates(int amount) {
        super(amount);
    }

    @Override
    public void update(WorkplaceNode holder) {
        items.addAll(addItems);
        addItems.forEach(Item::activate);
        addItems.clear();
        items.removeAll(dropItems);
        dropItems.clear();

        items.forEach(Item::draw);
    }
}
