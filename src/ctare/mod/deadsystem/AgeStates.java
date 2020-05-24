package ctare.mod.deadsystem;

import ctare.Main;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.UnitStates;
import ctare.utils.Calc;

/**
 * Created by ctare on 2020/05/24.
 */
public class AgeStates extends UnitStates {
    int age;
    public AgeStates(UnitNode unit) {
        super(unit);

        this.age = Calc.random.nextInt(200) + 400;
    }

    @Override
    public void update(UnitNode holder) {
        this.age -= 1;
        if (this.age == 0) {
            Main.instance().dropUnit(holder);
            Main.instance().addUnit(new Corpse(holder));
        }
    }
}
