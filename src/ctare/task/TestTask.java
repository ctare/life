package ctare.task;

import ctare.core.NodesManager;
import ctare.mod.worksystem.ResourceNode;
import ctare.nodes.WorkplaceNode;

import java.util.List;

/**
 * Created by ctare on 2020/05/19.
 */
public class TestTask extends Task {
    private int at = 100;

    @Override
    public List<? extends WorkplaceNode> getWorkplace() {
        return NodesManager.getVacancy(ResourceNode.class);
    }

    @Override
    public int at() {
        return this.at;
    }

    @Override
    public void contribute(int point) {
        this.at -= point;
    }
}
