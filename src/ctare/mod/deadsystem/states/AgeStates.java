package ctare.mod.deadsystem.states;

import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.Moving;
import ctare.nodes.unit.states.UnitStates;
import ctare.utils.Calc;

/**
 * Created by ctare on 2020/05/24.
 */
public class AgeStates extends UnitStates {
    int age;
    public AgeStates(UnitNode unit) {
        this(unit, 10000);
    }

    public AgeStates(UnitNode unit, int averageLife) {
        super(unit);
        this.age = Calc.random.nextInt(1000) + averageLife;
    }

    @Override
    public void update(UnitNode holder) {
        this.age = Math.max(0, this.age - 1);
        if (this.age == 0 && !(holder.state instanceof Moving)) {
            Corpse.kill(holder);
        }
    }

    public int getAge() {
        return age;
    }
}
