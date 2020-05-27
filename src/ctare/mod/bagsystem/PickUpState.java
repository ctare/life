package ctare.mod.bagsystem;

import ctare.Main;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.State;

/**
 * Created by ctare on 2020/05/27.
 */
public class PickUpState<T extends Item> extends State<WorkplaceNode> {
    private T target;
    private Runnable then;
    private ItemMemberStates<T> itemPlace;
    public PickUpState(UnitNode unit, T target, Runnable then, ItemMemberStates<T> itemPlace) {
        super(unit, unit.place);
        this.target = target;
        this.then = then;
        this.itemPlace = itemPlace;
    }

    @Override
    public void update() {
        target.draw();
        if (Main.instance().isHit(unit, target)) {
            BagStates bag = unit.states.get(BagStates.class);
            bag.add(target);
            itemPlace.unregister(target);
            then.run();

        } else {
            unit.move(target.getPosition(), unit.getSpeed());
            target.marking(5);
            State.Manager.call(PickUpState.class, this);
        }
    }
}
