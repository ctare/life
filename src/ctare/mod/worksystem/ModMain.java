package ctare.mod.worksystem;

import ctare.Main;
import ctare.mod.ModLoader;
import ctare.nodes.CentralNode;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.task.Task;
import ctare.utils.Calc;

import java.util.List;

/**
 * Created by ctare on 2020/05/24.
 */
public class ModMain extends ModLoader {
    @Override
    public void initialize() {

    }

    @Override
    public void stateRegister() {
        State.Manager.register(Free.class, free -> {
            if (Work.policy.stream().allMatch(e -> e.test(free.unit))) {
                Task task = Main.instance().root.task.getTask();
                List<? extends WorkplaceNode> nodes = task.getWorkplace();
                if (nodes.size() > 0) {
                    WorkplaceNode workplace = (Calc.choice(nodes)); // TODO: sorted
                    free.unit.readyFor(workplace, new Work());
                }
            }
        });
    }

    @Override
    public void nodeRegister() {
    }

    @Override
    public void statesRegister() {
        UnitNode.statesManager.register(ShippingStates.class);
//        WorkplaceNodeStates.Manager.register(StorageStates.class);
        WorkplaceNode.statesManager.addHook.register(CentralNode.class, StorageStates::new);
    }
}
