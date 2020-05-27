package ctare.mod.bagsystem;

import ctare.Main;
import ctare.core.RoundObject;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.UnitStates;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctare on 2020/05/25.
 */
public class BagStates extends UnitStates {
    private List<Item> items = new ArrayList<>();
    private UnitNode owner;
    private static final int CLOSENESS = 0;
    public BagStates(UnitNode unit) {
        super(unit);
        owner = unit;
    }

    private static void calcItemPosition(RoundObject driver, Item item) {
        int length = driver.getRadius() + item.getRadius() - CLOSENESS;
        PVector driverPosition = driver.getPosition();
        if (!Main.instance().isHit(driverPosition.x, driverPosition.y, 0, item.getPosition().x, item.getPosition().y, length)) {
            double angle = Math.atan2(item.getPosition().y - driver.getPosition().y, item.getPosition().x - driverPosition.x);
            item.getPosition().x = driverPosition.x + (float)Math.cos(angle) * length;
            item.getPosition().y = driverPosition.y + (float)Math.sin(angle) * length;
        }
    }

    @Override
    public void update(UnitNode holder) {
        RoundObject driver = holder;
        for (Item item : items) {
            calcItemPosition(driver, item);
            item.draw();
            driver = item;
        }
    }

    public void add(Item item) {
        items.add(item);
        item.setPosition(owner.getPosition());
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public List<Item> items() {
        return items;
    }

    @Override
    public String toString() {
        return this.items().toString();
    }

    public void transfer(BagStates to) {
        to.items.addAll(this.items);
        this.items.clear();
    }
}
