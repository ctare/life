package ctare.nodes.unit.states;

import processing.core.PApplet;

/**
 * Created by ctare on 2020/05/22.
 */
public class StatesValue {
    public int value;
    public int maxValue;

    public StatesValue(int maxValue) {
        this(maxValue, maxValue);
    }

    public StatesValue(int value, int maxValue) {
        this.value = value;
        this.maxValue = maxValue;
    }

    public void add(int n) {
        value = Math.max(Math.min(value + n, maxValue), 0);
    }

    public boolean isFull() {
        return this.value == this.maxValue;
    }
}
