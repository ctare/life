package ctare.mod.healthsystem;

import ctare.core.Color;
import ctare.nodes.WorkplaceNode;

/**
 * Created by ctare on 2020/05/21.
 */
public class RestNode extends WorkplaceNode {
    private static final Color color = new Color(224, 38, 162);
    public RestNode(int amount) {
        super(amount);
    }

    @Override
    public Color getColor() {
        return RestNode.color;
    }
}
