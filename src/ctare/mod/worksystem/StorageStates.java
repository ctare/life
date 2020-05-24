package ctare.mod.worksystem;

import ctare.mod.worksystem.resource.Storage;
import ctare.nodes.WorkplaceNode;
import ctare.nodes.unit.states.WorkplaceNodeStates;

/**
 * Created by ctare on 2020/05/24.
 */
public class StorageStates extends WorkplaceNodeStates {
    public final Storage storage = new Storage();

    public StorageStates(int amount) {
        super(amount);
    }

    @Override
    public void update(WorkplaceNode holder) {
    }
}
