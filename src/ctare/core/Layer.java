package ctare.core;

import ctare.Main;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by ctare on 2020/05/24.
 */
public class Layer {
    private ArrayList<Consumer<Main>> procs = new ArrayList<>();

    public void clear() {
        procs.clear();
    }

    public void paint(Consumer<Main> proc) {
        procs.add(proc);
    }

    public void exec() {
        procs.forEach(c -> c.accept(Main.instance()));
    }
}
