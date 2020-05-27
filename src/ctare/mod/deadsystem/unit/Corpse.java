package ctare.mod.deadsystem.unit;

import ctare.Main;
import ctare.core.Color;
import ctare.mod.bagsystem.BagStates;
import ctare.mod.bagsystem.Item;
import ctare.mod.deadsystem.states.CorpseMemberStates;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/23.
 */
public class Corpse extends Item {
    private static final Color color = new Color(91, 91, 91);
    private UnitNode origin;
    public Corpse(UnitNode unit) {
        super();
        super.setPosition(unit.getPosition());
        this.setRadius(unit.getRadius());
        this.origin = unit;
    }

    @Override
    public Color getColor() {
        return Corpse.color;
    }

    @Override
    public void update() {
        super.update();
        origin.states.get(BagStates.class).update(origin);
    }

    public final UnitNode getOrigin() {
        return origin;
    }

    public static void kill(UnitNode unit) {
        Main.instance().dropUnit(unit);
        Corpse corpse = new Corpse(unit);
        unit.place.states.get(CorpseMemberStates.class).register(corpse);
    }
}
