package ctare.mod.healthsystem;

import ctare.Main;
import ctare.core.Node;
import ctare.mod.ModLoader;
import ctare.mod.worksystem.Work;
import ctare.mod.worksystem.Working;
import ctare.nodes.VacantNode;
import ctare.nodes.unit.state.Free;
import ctare.nodes.unit.state.State;
import ctare.nodes.unit.states.UnitStates;

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

                List<RestNode> nodes = Node.execNodes(working.unit.place, RestNode.class, node -> {
                    if (!node.member.isFull()) {
                        working.unit.readyFor(node, new Rest());
                        return true;
                    }
                    return false;
                });

                if (nodes != null) {
                    VacantNode.free(working.unit);
                }
            }
        });

        State.Manager.register(Free.class, free -> {
            if (!free.unit.states.get(HealthStates.class).power.isFull()) {
                Node.execNodes(free.where, RestNode.class, node -> {
                    if (!node.member.isFull()) {
                        free.unit.readyFor(node, new Rest());
                        return true;
                    }
                    return false;
                });
            }
        });
    }

    @Override
    public void nodeRegister() {
        Main.instance().keymap.register('1', app -> app.nodeSupplier = () -> new FreeSpaceNode(5));
        Main.instance().keymap.register('4', app -> app.nodeSupplier = () -> new RestNode(5));
    }

    @Override
    public void statesRegister() {
        UnitStates.Manager.register(HealthStates.class);
    }
}
