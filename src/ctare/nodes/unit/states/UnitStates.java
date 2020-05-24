package ctare.nodes.unit.states;

import ctare.core.StaticManager;
import ctare.nodes.unit.UnitNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ctare on 2020/05/22.
 */
public abstract class UnitStates extends States<UnitNode> {
    public UnitStates(UnitNode unit) {
    }

    @Override
    public void update(UnitNode holder) {

    }

    public static class Manager extends StaticManager {
        private static final ArrayList<Class<? extends UnitStates>> activeStates = new ArrayList<>();
        public static final AddHook<UnitNode, UnitNode, UnitStates> addHook = new AddHook<>();

        public Manager(UnitNode unit, Class<? extends UnitNode> cls) {
            addHook.exec(cls, unit, this.states);

            for (Class<? extends UnitStates> activeState : activeStates) {
                states.computeIfAbsent(activeState, k -> {
                    try {
                        return activeState.getDeclaredConstructor(UnitNode.class).newInstance(unit);
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        }

        public static void register(Class<? extends UnitStates> key) {
            activeStates.add(key);
        }

        public HashMap<Class<? extends UnitStates>, UnitStates> states = new HashMap<>();
        public <E extends UnitStates> E get(Class<E> cls) {
            E states = (E) this.states.get(cls);
            assert states != null;
            return states;
        }

        public void update(UnitNode holder) {
            this.states.values().forEach(unitStates -> unitStates.update(holder));
        }
    }
}
