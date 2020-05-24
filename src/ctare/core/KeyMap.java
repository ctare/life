package ctare.core;

import ctare.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by ctare on 2020/05/23.
 */
public class KeyMap {
    private HashMap<Character, Consumer<Main>> keymap = new HashMap<>();
    public void register(char key, Consumer<Main> proc) {
        assert keymap.get(key) == null;
        keymap.put(key, proc);
    }

    public final void keyPressed(char key, Main main) {
        Consumer<Main> proc = keymap.get(key);
        if (proc != null) {
            proc.accept(main);
        }
    }
}
