package ctare.mod.deadsystem.nodes;

import ctare.core.Color;
import ctare.nodes.WorkplaceNode;

/**
 * Created by ctare on 2020/05/23.
 */
public class GraveNode extends WorkplaceNode {
    private static final Color color = new Color(30, 30, 30);
    private static int cnt = 0;

    @Override
    public Color getColor() {
        return GraveNode.color;
    }

    public GraveNode(int amount) {
        super(amount);
    }

    @Override
    public void activate() {
        super.activate();
        cnt++;
    }

    @Override
    public void deactivate() {
        super.deactivate();
        cnt--;
    }

    public static int getCnt() {
        return cnt;
    }
}
