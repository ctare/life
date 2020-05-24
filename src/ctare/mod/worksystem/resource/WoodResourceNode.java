package ctare.mod.worksystem.resource;

import ctare.core.Color;
import ctare.mod.worksystem.ResourceNode;

/**
 * Created by ctare on 2020/05/20.
 */
public class WoodResourceNode extends ResourceNode {
    private static final Color resourceColor = new Color(38, 224, 121);

    public static class Wood extends Resource {
        public Wood(int amount) {
            super(Type.WOOD, amount);
        }
    }

    public WoodResourceNode(int amount) {
        super(amount, resourceColor);
    }

    @Override
    public Resource gather(int amount) {
        return super.gather(new Wood(amount));
    }
}
