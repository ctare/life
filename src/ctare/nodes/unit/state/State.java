package ctare.nodes.unit.state;

import ctare.core.Node;
import ctare.core.StaticManager;
import ctare.nodes.unit.UnitNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Created by ctare on 2020/05/20.
 */
public abstract class State<E extends Node> {
    public final UnitNode unit;
    public final E where;
    public State(UnitNode unit, E where) {
        this.unit = unit;
        this.where = where;
    }

    public abstract void update();

    public static class Manager extends StaticManager {
        static HashMap<Class<? extends State>, ArrayList<Consumer<? extends State>>> procs = new HashMap<>();

        public static <E extends State> void call(Class<E> cls, E instance) {
            ArrayList<Consumer<? extends State>> list = procs.computeIfAbsent(cls, k -> new ArrayList<>());
            list.forEach(proc -> ((Consumer<E>) proc).accept(instance));
        }

        public static <E extends State> void register(Class<E> key, Consumer<E> proc) {
            ArrayList<Consumer<? extends State>> list = procs.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(proc);
        }
    }
}
