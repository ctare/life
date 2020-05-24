package ctare.mod.deadsystem;

import ctare.core.Color;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/23.
 */
public class Corpse extends UnitNode {
    private static final Color color = new Color(91, 91, 91);
    public Corpse(UnitNode unit) {
        super(unit.place);
        super.setPosition(unit.getPosition().x, unit.getPosition().y);
        this.activate();
    }

    @Override
    protected void firstAction() {
        this.state = new DeadState(this, this.place);
    }

    @Override
    public Color getColor() {
        return Corpse.color;
    }
}
