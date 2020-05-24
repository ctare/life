package ctare.mod.deadsystem;

import ctare.Main;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/24.
 */
public class CorpseStates extends AgeStates {
    public CorpseStates(UnitNode unit) {
        super(unit);
    }

    @Override
    public void update(UnitNode holder) {
        this.age -= 1;
        if (this.age == 0) {
            Main.instance().dropUnit(holder);
        }
    }
}
