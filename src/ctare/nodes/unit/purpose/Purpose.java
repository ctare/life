package ctare.nodes.unit.purpose;

import ctare.core.Node;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/20.
 */
public abstract class Purpose<T extends Node> {
    public abstract State getState(UnitNode unit, T where);
}
