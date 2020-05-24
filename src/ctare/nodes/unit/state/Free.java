package ctare.nodes.unit.state;

import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.utils.Calc;
import processing.core.PVector;

/**
 * Created by ctare on 2020/05/20.
 */
public class Free<E extends WorkplaceNode> extends State<E> {
    public PVector freeWalk;
    public Free(UnitNode unit, E where) {
        super(unit, where);
        this.freeWalk = Calc.getRandomPosition(this.where, this.unit.getRadius());
    }

    public void randomWalk(float speed, float frequency) {
        if (this.freeWalk != null) {
            this.unit.move(this.freeWalk, speed);

            if (Calc.dist(this.freeWalk, this.unit.getPosition()) < 1) {
                this.freeWalk = null;
            }
        } else if (Calc.bernoulli(frequency)) {
            this.freeWalk = Calc.getRandomPosition(this.where, this.unit.getRadius());
        }
    }

    public void randomWalk(float speed) {
        randomWalk(speed, 0.005f);
    }

    @Override
    public void update() {
        this.randomWalk(this.unit.getSpeed() * 0.5f);

        State.Manager.call(Free.class, this);
    }
}
