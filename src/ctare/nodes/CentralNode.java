package ctare.nodes;

import ctare.Main;
import ctare.core.Color;
import ctare.mod.worksystem.StorageStates;
import ctare.mod.worksystem.resource.FoodResourceNode;
import ctare.nodes.unit.states.NodeInfoStates;
import ctare.task.TaskManager;

/**
 * Created by ctare on 2020/05/17.
 */
public class CentralNode extends VacantNode {
    private static final Color COLOR = new Color(193, 228, 37);
    public final TaskManager task = new TaskManager();

    public CentralNode(int amount) {
        super(amount);
    }

    static {
        WorkplaceNode.statesManager.addHook.register(CentralNode.class, amount -> new NodeInfoStates(amount * 5), NodeInfoStates.class);
    }

    @Override
    protected int getSize() {
        return super.SIZE * 2;
    }

    @Override
    public Color getColor() {
        return CentralNode.COLOR;
    }

    @Override
    protected void update() {
        super.update();
        if (this.states.get(StorageStates.class).storage.use(new FoodResourceNode.Food(300))) { // TODO: ResourceNodeをどこかへやる
            Main.instance().addUnit(this);
        }
    }
}
