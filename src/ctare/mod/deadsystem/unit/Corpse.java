package ctare.mod.deadsystem.unit;

import ctare.Main;
import ctare.core.Color;
import ctare.mod.bagsystem.BagStates;
import ctare.mod.deadsystem.state.DeadState;
import ctare.mod.deadsystem.states.CorpseMemberStates;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/23.
 */
public class Corpse extends UnitNode {
    private static final Color color = new Color(91, 91, 91);
    public Corpse(UnitNode unit) {
        super(unit.place);
        super.setPosition(unit.getPosition().x, unit.getPosition().y);
    }

    @Override
    protected void firstAction() {
        this.state = new DeadState(this, this.place);
    }

    @Override
    public Color getColor() {
        return Corpse.color;
    }

    public static void kill(UnitNode unit) {
        Main.instance().dropUnit(unit);
        Corpse corpse = new Corpse(unit);
        unit.states.get(BagStates.class).transfer(corpse.states.get(BagStates.class));
        unit.place.states.get(CorpseMemberStates.class).register(corpse);
    }
}
