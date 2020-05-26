package ctare.nodes;

import ctare.Main;
import ctare.core.Node;
import ctare.nodes.unit.states.WorkplaceNodeStates;
import processing.core.PApplet;

/**
 * Created by ctare on 2020/05/21.
 */
public abstract class WorkplaceNode extends Node {
    public WorkplaceNodeStates.Manager states;

    public WorkplaceNode(int amount) {
        super(amount);
    }

    @Override
    public void activate() {
        super.activate();
        this.states = new WorkplaceNodeStates.Manager(this.getAmount(), this.getClass());
    }

    @Override // debug
    public void design(PApplet app) {
        super.design(app);
        Main.instance().informationLayer.paint(main -> {
            main.fill(255);
            main.text(String.format("%d", this.member.size()), getPosition().x + getRadius(), getPosition().y + getRadius());
        });
    }

    @Override
    public void draw() {
        super.draw();
        if (isActive()) {
            this.states.update(this);
        }
    }
}
