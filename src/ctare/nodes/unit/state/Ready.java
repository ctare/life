package ctare.nodes.unit.state;

import ctare.Main;
import ctare.core.Node;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;

/**
 * Created by ctare on 2020/05/20.
 */
public class Ready extends State<Node> {
    public Ready(UnitNode unit, Node where) {
        super(unit, where);
    }

    @Override
    public void update() {
        this.unit.move(this.where.getPosition(), this.unit.getSpeed());
        if (Main.instance().isHit(this.where.getPosition().x, this.where.getPosition().y, 1,
                this.unit.getPosition().x, this.unit.getPosition().y, 0)) {

            if (this.unit.plan.size() > 0) {
                this.unit.place = (WorkplaceNode)this.unit.plan.remove(0);
                this.unit.state = new Moving(this.unit, this.unit.place);
            } else {
                this.unit.state = this.unit.purpose.getState(this.unit, this.where);
            }
        } else {
            State.Manager.call(Ready.class, this);
        }
    }
}
