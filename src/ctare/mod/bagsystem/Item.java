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
    protected Object key;
    protected Color itemColor = new Color(255, 255, 255);
    private int radius = 5;
    public States.Value<ItemStates, Item, Item> states;

    public Item(Object key) {
        super(Main.instance().characterLayer);
        this.key = key;
        this.activate();
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

    public void update() {
        states.update(this);
    }
}
