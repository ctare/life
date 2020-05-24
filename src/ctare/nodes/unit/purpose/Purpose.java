package ctare.nodes.unit.purpose;

import ctare.core.Node;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/20.
 */
public abstract class Purpose<T extends Node> {
    public int priority;
    public Purpose(int priority) {
        this.priority = priority;
    }
    public abstract State getState(UnitNode unit, T where);
}
