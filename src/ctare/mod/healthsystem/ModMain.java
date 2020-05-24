package ctare.mod.healthsystem;

import ctare.Main;
import ctare.core.NodesManager;
import ctare.mod.ModLoader;
import ctare.mod.worksystem.Work;
import ctare.nodes.VacantNode;
import ctare.nodes.unit.purpose.Nothing;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.mod.worksystem.Working;
import ctare.nodes.unit.states.UnitStates;
import ctare.utils.Calc;

import java.util.List;

/**
 * Created by ctare on 2020/05/23.
 */
public final class ModMain extends ModLoader {
    @Override
    public void initialize() {
        Work.policy.add(unitNode -> unitNode.states.get(HealthStates.class).power.isFull());
    }

    @Override
    public void stateRegister() {
        State.Manager.register(Working.class, working -> {
            HealthStates.damage(working.unit, 50);

            if (working.unit.states.get(HealthStates.class).power.value == 0 && !(working.unit.purpose instanceof Rest)) {
                List<RestNode> nodes = NodesManager.getVacancy(RestNode.class);
                if (nodes.size() > 0) {
                    working.unit.readyFor(Calc.getNode(nodes), new Rest());
                } else {
                    List<VacantNode> vacantNodes = NodesManager.getVacancy(VacantNode.class);
                    if (vacantNodes.size() == 0) {
                        vacantNodes = NodesManager.get(VacantNode.class);
                    }
                    working.unit.readyFor(Calc.getNode(vacantNodes), new Nothing());
                }
            }
        });

        State.Manager.register(Free.class, free -> {
            if (!free.unit.states.get(HealthStates.class).power.isFull()) {
                List<RestNode> nodes = NodesManager.getVacancy(RestNode.class);
                if (nodes.size() > 0) {
                    RestNode node = Calc.getNode(nodes);
                    free.unit.readyFor(node, new Rest());
                }
            }
        });
    }

    @Override
    public void nodeRegister() {
        NodesManager.register(RestNode.class);
        Main.instance().keymap.register('1', app -> app.nodeSupplier = () -> new FreeSpaceNode(5));
        Main.instance().keymap.register('4', app -> app.nodeSupplier = () -> new RestNode(5));
    }

    @Override
    public void statesRegister() {
        UnitStates.Manager.register(HealthStates.class);
    }
}
