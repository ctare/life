package ctare.mod.worksystem;

import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.StatesValue;
import ctare.nodes.unit.states.UnitStates;

/**
 * Created by ctare on 2020/05/23.
 */
public class ShippingStates extends UnitStates {
    public final StatesValue capacity;

    public ShippingStates(UnitNode unit) {
        super(unit);
        this.capacity = new StatesValue(50);
    }
}
