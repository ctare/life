package ctare.mod.worksystem.resource;

/**
 * Created by ctare on 2020/05/20.
 */
public class Resource {
    public enum Type {
        FOOD, WOOD
    }

    public int amount;
    public final Type type;

    public Resource(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}
