package ctare.nodes.unit.states;

import ctare.core.StaticManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ctare on 2020/05/23.
 */
public abstract class States<E> {
    public abstract void update(E holder);

    public static class Manager<TStatesType extends States<THolderType>, THolderType, TArgType> extends StaticManager {
        private final ArrayList<Class<? extends TStatesType>> activeStates = new ArrayList<>();
        public final AddHook<THolderType, TArgType, TStatesType> addHook = new AddHook<>();
        private Class<TArgType> argType;

        public Manager(Class<TArgType> argType) {
            this.argType = argType;
        }

        public void register(Class<? extends TStatesType> key) {
            this.activeStates.add(key);
        }

        public Value<TStatesType, THolderType, TArgType> getValue(TArgType arg, Class<? extends THolderType> cls) {
            return new Value<>(arg, cls, this);
        }
    }

    public static class Value<TStatesType extends States<THolderType>, THolderType, TArgType> {
        public Value(TArgType arg, Class<? extends THolderType> cls, Manager<TStatesType, THolderType, TArgType> manager) {
            manager.addHook.exec(cls, arg, this.states);

            for (Class<? extends TStatesType> activeState : manager.activeStates) {
                states.computeIfAbsent(activeState, k -> {
                    try {
                        return activeState.getDeclaredConstructor(manager.argType).newInstance(arg);
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        }

        public HashMap<Class<? extends TStatesType>, TStatesType> states = new HashMap<>();
        @SuppressWarnings("unchecked")
        public <E extends TStatesType> E get(Class<E> cls) {
            E states = (E) this.states.get(cls);
            assert states != null;
            return states;
        }

        public void update(THolderType holder) {
            this.states.values().forEach(states -> states.update(holder));
        }
    }
}
