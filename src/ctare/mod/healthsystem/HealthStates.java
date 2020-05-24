package ctare.mod.healthsystem;

import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.StatesValue;
import ctare.nodes.unit.states.UnitStates;

/**
 * Created by ctare on 2020/05/23.
 */
public class HealthStates extends UnitStates {
    public final StatesValue power;

    public HealthStates(UnitNode unit) {
        super(unit);
        this.power = new StatesValue(6000);
    }


    public static void heal(UnitNode unit, int value) {
        unit.states.get(HealthStates.class).power.add(value);
    }

    public static void damage(UnitNode unit, int value) {
        unit.states.get(HealthStates.class).power.add(-value);
    }
}
