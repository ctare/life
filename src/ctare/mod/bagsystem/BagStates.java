package ctare.mod.bagsystem;

import ctare.Main;
import ctare.core.Color;
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
    public static class Item {
        private Object key;
        private Color itemColor = new Color(255, 255, 255);
        private int radius = 5;
        private PVector position;

        public Item(Object key) {
            this.key = key;
        }

        public Item setItemColor(Color itemColor) {
            this.itemColor = itemColor;
            return this;
        }

        public Item setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        private void setPosition(PVector p) {
            this.position = p;
        }
    }

    private HashMap<Object, Item> items = new HashMap<>();
    private UnitNode owner;
    private static final int CLOSENESS = 0;
    public BagStates(UnitNode unit) {
        super(unit);
        owner = unit;
    }

    public static void calcItemPosition(PVector driver, int driverRad, Item item) {
        int length = driverRad + item.radius - CLOSENESS;
        if (!Main.instance().isHit(driver.x, driver.y, 0, item.position.x, item.position.y, length)) {
            double angle = Math.atan2(item.position.y - driver.y, item.position.x - driver.x);
            item.position.x = driver.x + (float)Math.cos(angle) * length;
            item.position.y = driver.y + (float)Math.sin(angle) * length;
        }
    }

    @Override
    public void update(UnitNode holder) {
        Main.instance().characterLayer.paint(app -> {
            PVector driver = holder.getPosition();
            int driverRad = holder.getRadius();
            for (Item item : items.values()) {
                app.fill(item.itemColor.r, item.itemColor.g, item.itemColor.b);
                calcItemPosition(driver, driverRad, item);
                app.ellipse(item.position.x, item.position.y, item.radius * 2, item.radius * 2);
                driver = item.position;
                driverRad = item.radius;
            }
        });
    }

    public void add(Item item) {
        items.put(item.key, item);
        item.setPosition(owner.getPosition().copy());
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
