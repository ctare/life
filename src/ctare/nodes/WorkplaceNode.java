package ctare.nodes;

import ctare.Main;
import ctare.core.Node;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.states.NodeInfoStates;
import ctare.nodes.unit.states.States;
import ctare.nodes.unit.states.WorkplaceNodeStates;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by ctare on 2020/05/21.
 */
public abstract class WorkplaceNode extends Node {
    public class Member extends ArrayList<UnitNode> {
        public boolean isFull() {
            return this.size() >= states.get(NodeInfoStates.class).capacity.value;
        }
    }

    public final Member member = new Member();

    public void register(UnitNode unit) {
        this.member.add(unit);
    }

    public void unregister(UnitNode unit) {
        this.member.remove(unit);
    }

    public static States.Manager<WorkplaceNodeStates, WorkplaceNode, Integer> statesManager = new States.Manager<>(int.class);
    public States.Value<WorkplaceNodeStates, WorkplaceNode, Integer> states;

    public WorkplaceNode(int amount) {
        super(amount);
    }

    @Override
    public void activate() {
        super.activate();
        this.states = statesManager.getValue(this.getAmount(), this.getClass());
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
    protected void update() {
        super.update();
        this.states.update(this);
    }
}
