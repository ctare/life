package ctare.nodes.unit;

import ctare.Main;
import ctare.core.*;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.purpose.Nothing;
import ctare.nodes.unit.purpose.Purpose;
import ctare.nodes.unit.state.Ready;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.states.States;
import ctare.nodes.unit.states.UnitStates;
import ctare.utils.Calc;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by ctare on 2020/05/17.
 */
public class UnitNode extends RoundObject {
    // private static final Color COLOR = new Color(155, 60, 60);
    public static final States.Manager<UnitStates, UnitNode, UnitNode> statesManager = new States.Manager<>(UnitNode.class);

    private static final Color COLOR = new Color(227, 207, 105);
    private static final float speed = 3f;
    private WorkplaceNode belong;
    public WorkplaceNode place;
    public final UnitStates.Value<UnitStates, UnitNode, UnitNode> states;
    public Purpose purpose;
    public State state;
    public ArrayList<Node> plan = new ArrayList<>();

    public UnitNode(WorkplaceNode place) {
        super(Main.instance().characterLayer);
        this.place = place;
        this.setPosition(place.getPosition().x, place.getPosition().y);

        states = statesManager.getValue(this, this.getClass());
    }

    @Override
    public void activate() {
        super.activate();
        this.firstAction();
    }

    protected void firstAction() {
        this.readyFor(place, new Nothing());
    }

    @Override
    public int getRadius() {
        return 5;
    }

    @Override
    public Color getColor() {
        return UnitNode.COLOR;
    }

    public void move(PVector target, float speed) {
        float d = Calc.dist(this.getPosition(), target) + 1e-8f;
        float dRatio = PApplet.max(0, d - speed) / d;

        this.getPosition().x = target.x + (this.getPosition().x - target.x) * dRatio;
        this.getPosition().y = target.y + (this.getPosition().y - target.y) * dRatio;
    }

    @Override
    protected void update() {
        state.update();
        states.update(this);
    }

    private void addPlan(Node place) {
        plan.addAll(Graph.getRoute(plan.size() > 0 ? plan.get(plan.size() - 1) : this.place, place));
    }

    public float getSpeed() {
        return speed;
    }

    public void forceReadyFor(WorkplaceNode workplaceNode, Purpose purpose) {
        this.purpose = null;
        readyFor(workplaceNode, purpose);
    }

    public void readyFor(WorkplaceNode workplace, Purpose purpose) {
        if (this.state instanceof Ready) {
            System.out.println("warning!!! readyFor");
            return;
        }

        if (this.purpose != null && this.purpose.priority >= purpose.priority) {
            return;
        }

        this.place.unregister(this);

        this.addPlan(workplace);
        workplace.register(this);
        belong = workplace;

        this.state = new Ready(this, this.place);
        this.purpose = purpose;
    }

    public WorkplaceNode getBelong() {
        return belong;
    }
}
