package ctare.mod.worksystem.resource;

import java.util.HashMap;

/**
 * Created by ctare on 2020/05/20.
 */
public class Storage {
    private final HashMap<Resource.Type, Integer> stock = new HashMap<Resource.Type, Integer>(){
        @Override
        public Integer get(Object key) {
            Integer value = super.get(key);
            return value == null ? 0 : value;
        }
    };

    public void save(Resource resource) {
        stock.put(resource.type, stock.get(resource.type) + resource.amount);
    }

    public boolean use(Resource resource) {
        int at = stock.get(resource.type);
        if (at >= resource.amount) {
            stock.put(resource.type, at - resource.amount);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "stock=" + stock +
                '}';
    }
}
