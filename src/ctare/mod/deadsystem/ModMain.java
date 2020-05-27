package ctare.mod.deadsystem;

import ctare.Main;
import ctare.mod.ModLoader;
import ctare.mod.deadsystem.nodes.GraveNode;
import ctare.mod.deadsystem.states.AgeStates;
import ctare.mod.deadsystem.states.CorpseMemberStates;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.UnitNode;

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
        Main.instance().keymap.register('g', app -> app.nodeSupplier = () -> new GraveNode(5));
    }

    @Override
    public void statesRegister() {
        UnitNode.statesManager.register(AgeStates.class);
//        UnitNode.statesManager.addHook.register(Corpse.class, CorpseStates::new, AgeStates.class);
        WorkplaceNode.statesManager.register(CorpseMemberStates.class);
    }
}
