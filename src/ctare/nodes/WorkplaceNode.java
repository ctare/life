package ctare.nodes;

import ctare.Main;
import ctare.core.Graph;
import ctare.core.Node;
import ctare.nodes.unit.states.WorkplaceNodeStates;
import processing.core.PApplet;

import java.util.List;

/**
 * Created by ctare on 2020/05/21.
 */
public abstract class WorkplaceNode<E extends WorkplaceNode> extends Node {
    public WorkplaceNodeStates.Manager states;
    protected int distance = 0;

    public WorkplaceNode(int amount) {
        super(amount);
    }

    public void calcDistance() {
        this.distance = Graph.getRoute(this).size();
    }

    @Override
    public void activate() {
        super.activate();
        this.states = new WorkplaceNodeStates.Manager(this.getAmount(), this.getClass());
        calcDistance();
        this.getNodes().add((E) this);
    }

    public abstract List<E> getNodes();

    public int getDistance() {
        return distance;
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
        this.states.update(this);
    }
}
