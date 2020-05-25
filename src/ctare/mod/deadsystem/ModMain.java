package ctare.mod.deadsystem;

import ctare.Main;
import ctare.core.NodesManager;
import ctare.mod.ModLoader;
import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.states.AgeStates;
import ctare.mod.deadsystem.states.CorpseMemberStates;
import ctare.mod.deadsystem.states.CorpseStates;
import ctare.mod.deadsystem.unit.Corpse;
import ctare.nodes.unit.states.UnitStates;
import ctare.nodes.unit.states.WorkplaceNodeStates;

/**
 * Created by ctare on 2020/05/23.
 */
public final class ModMain extends ModLoader {
    @Override
    public void initialize() {
    }

    @Override
    public void stateRegister() {
    }

    @Override
    public void nodeRegister() {
        NodesManager.register(GraveNode.class);
        Main.instance().keymap.register('g', app -> app.nodeSupplier = () -> new GraveNode(5));
    }

    @Override
    public void statesRegister() {
        UnitStates.Manager.register(AgeStates.class);
        UnitStates.Manager.addHook.register(Corpse.class, CorpseStates::new, AgeStates.class);
        WorkplaceNodeStates.Manager.register(CorpseMemberStates.class);
    }
}
