package ctare.mod.worksystem.resource;

import ctare.core.Color;
import ctare.mod.worksystem.ResourceNode;

/**
 * Created by ctare on 2020/05/20.
 */
public class FoodResourceNode extends ResourceNode {
    private static final Color resourceColor = new Color(223, 226, 38);

    public static class Food extends Resource {
        public Food(int amount) {
            super(Type.FOOD, amount);
        }
    }

    public FoodResourceNode(int amount) {
        super(amount, resourceColor);
    }

    @Override
    public Resource gather(int amount) {
        return super.gather(new Food(amount));
    }
}
