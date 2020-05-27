package ctare.mod.bagsystem;

import ctare.Main;
import ctare.core.Color;
import ctare.core.RoundObject;
import ctare.nodes.unit.states.States;

/**
 * Created by ctare on 2020/05/27.
 */
public class Item extends RoundObject {
    public static States.Manager<ItemStates, Item, Item> statesManager = new States.Manager<>(Item.class);
    protected Color itemColor = new Color(255, 255, 255);
    private int radius = 5;
    public States.Value<ItemStates, Item, Item> states;
    public Marking marking = new Marking(0);

    public Item() {
        super(Main.instance().characterLayer);
        states = statesManager.getValue(this, this.getClass());
    }

    public Item setItemColor(Color itemColor) {
        this.itemColor = itemColor;
        return this;
    }

    public Item setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    @Override
    public int getRadius() {
        return this.radius;
    }

    @Override
    public Color getColor() {
        return this.itemColor;
    }

    @Override
    protected void update() {
        states.update(this);
        this.marking.update();
    }

    public final boolean hasOwner() {
//        return this.owner != null && this.owner.isActive();
        return this.marking.time > 0;
    }

    public final void marking(int time) {
        this.marking.time = time;
    }

    private static class Marking {
        private int time;

        public Marking(int time) {
            this.time = time;
        }

        public void update() {
            this.time = Math.max(0, this.time - 1);
        }
    }
}
