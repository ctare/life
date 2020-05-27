package ctare.mod.bagsystem;

import ctare.Main;
import ctare.core.Color;
import ctare.core.RoundObject;

/**
 * Created by ctare on 2020/05/27.
 */
public class Item extends RoundObject {
    protected Object key;
    protected Color itemColor = new Color(255, 255, 255);
    private int radius = 5;

    public Item(Object key) {
        super(Main.instance().characterLayer);
        this.key = key;
        this.activate();
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
}
