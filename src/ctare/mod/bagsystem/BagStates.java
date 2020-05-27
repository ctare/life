package ctare.mod.bagsystem;

import ctare.Main;
import ctare.core.RoundObject;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.UnitStates;
import processing.core.PVector;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by ctare on 2020/05/25.
 */
public class BagStates extends UnitStates {
//    public static class Item {
//        private Object key;
//        private Color itemColor = new Color(255, 255, 255);
//        private int radius = 5;
//        private PVector position;
//
//        public Item(Object key) {
//            this.key = key;
//        }
//
//        public Item setItemColor(Color itemColor) {
//            this.itemColor = itemColor;
//            return this;
//        }
//
//        public Item setRadius(int radius) {
//            this.radius = radius;
//            return this;
//        }
//
//        private void setPosition(PVector p) {
//            this.position = p;
//        }
//    }

    private HashMap<Object, Item> items = new HashMap<>();
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
        for (Item item : items.values()) {
            calcItemPosition(driver, item);
            item.update();
            item.draw();
            driver = item;
        }
    }

    public void add(Item item) {
        items.put(item.key, item);
        item.setPosition(owner.getPosition());
    }

    public void remove(Object key) {
        items.remove(key);
    }

    public Collection<Object> items() {
        return this.items.values().stream().map(v -> v.key).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return this.items().toString();
    }

    public void transfer(BagStates to) {
        to.items.putAll(this.items);
        this.items.clear();
    }
}
