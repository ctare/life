package ctare.task;

import ctare.Main;
import ctare.core.Node;
import ctare.mod.worksystem.ResourceNode;
import ctare.nodes.WorkplaceNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ctare on 2020/05/19.
 */
public class TestTask extends Task {
    private int at = 100;

    @Override
    public List<? extends WorkplaceNode> getWorkplace() {
        List<ResourceNode> nodes = Node.execNodes(Main.instance().root, ResourceNode.class, node -> false);
        if (nodes == null) {
            return new ArrayList<>();
        } else {
            return nodes.stream()
                    .filter(node -> !node.member.isFull())
                    .collect(Collectors.toList());
        }
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
