package ctare.task;

import ctare.nodes.WorkplaceNode;

import java.util.List;

/**
 * Created by ctare on 2020/05/19.
 */
public abstract class Task {
    public abstract List<? extends WorkplaceNode> getWorkplace();
    public abstract int at();
    public abstract void contribute(int point);
}
