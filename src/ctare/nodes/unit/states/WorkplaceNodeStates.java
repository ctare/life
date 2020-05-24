package ctare.nodes.unit.states;

import ctare.core.StaticManager;
import ctare.nodes.WorkplaceNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ctare on 2020/05/23.
 */
public abstract class WorkplaceNodeStates extends States<WorkplaceNode> {
    public WorkplaceNodeStates(int amount) {
    }

    public static class Manager extends StaticManager {
        private static final ArrayList<Class<? extends WorkplaceNodeStates>> activeStates = new ArrayList<>();
        public static final AddHook<WorkplaceNode, Integer, WorkplaceNodeStates> addHook = new AddHook<>();

        static {
            Manager.register(NodeInfoStates.class);
        }

        public Manager(int amount, Class<? extends WorkplaceNode> cls) {
            addHook.exec(cls, amount, this.states);

            for (Class<? extends WorkplaceNodeStates> activeState : activeStates) {
                states.computeIfAbsent(activeState, k -> {
                    try {
                        return activeState.getDeclaredConstructor(int.class).newInstance(amount);
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        }

        public static void register(Class<? extends WorkplaceNodeStates> key) {
            activeStates.add(key);
        }

        public HashMap<Class<? extends WorkplaceNodeStates>, WorkplaceNodeStates> states = new HashMap<>();
        public <E extends WorkplaceNodeStates> E get(Class<E> cls) {
            E states = (E) this.states.get(cls);
            assert states != null;
            return states;
        }

        public void update(WorkplaceNode holder) {
            this.states.values().forEach(workplaceNodeStates -> workplaceNodeStates.update(holder));
        }
    }
}
