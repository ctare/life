package ctare.mod.bagsystem;

import ctare.mod.ModLoader;
import ctare.nodes.unit.states.UnitStates;

/**
 * Created by ctare on 2020/05/25.
 */
public class ModMain extends ModLoader {
    @Override
    public void initialize() {

    }

    @Override
    public void stateRegister() {

    }

    @Override
    public void nodeRegister() {

    }

    @Override
    public void statesRegister() {
        UnitStates.Manager.register(BagStates.class);
    }
}
