package ctare.nodes.unit.state;

import ctare.Main;
import ctare.core.Node;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/20.
 */
public class Moving extends State<Node> {
    public Moving(UnitNode unit, Node where) {
        super(unit, where);
    }

    @Override
    public void update() {
        if (!Main.instance().isHit(this.unit, this.where)) {
            this.unit.move(this.where.getPosition(), this.unit.getSpeed());

            State.Manager.call(Moving.class, this);
        } else {
            this.unit.state = new Ready(this.unit, this.where);
        }
    }
}
