package ctare.mod.deadsystem.states;

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
        this.age = Math.max(0, this.age - 1);
    }
}
