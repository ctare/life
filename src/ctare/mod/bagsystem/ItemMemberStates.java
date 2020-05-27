package ctare.mod.bagsystem;

import ctare.mod.deadsystem.purpose.Abandon;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.WorkplaceNodeStates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctare on 2020/05/27.
 */
public abstract class ItemMemberStates<T extends Item> extends WorkplaceNodeStates {
    protected List<T> items = new ArrayList<>();
    private List<T> dropItems = new ArrayList<>();
    private List<T> addItems = new ArrayList<>();
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

    public void register(T item) {
        this.addItems.add(item);
    }

    public void unregister(T item) {
        this.dropItems.add(item);
    }

    public final void pickUp(UnitNode unit, T item, Runnable then) {
        unit.purpose = new Abandon();
        unit.state = new PickUpState<>(unit, item, then, this);
        item.marking(5);
    }
}
