package ctare.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Created by ctare on 2020/05/23.
 */
public class StaticManager {
    private static class AddHookFunction<A, R> {
        public Class<? extends R> key;
        public Function<A, R> f;

        public AddHookFunction(Class<? extends R> key, Function<A, R> f) {
            this.key = key;
            this.f = f;
        }
    }

    public static class AddHook<K, A, R> {
        private HashMap<Class<? extends K>, ArrayList<AddHookFunction<A, R>>> addHooks = new HashMap<>();

        public void register(Class<? extends K> key, Function<A, R> f, Class<? extends R> stateType) {
            addHooks.computeIfAbsent(key, k -> new ArrayList<>()).add(new AddHookFunction<>(stateType, f));
        }

        public void register(Class<? extends K> key, Function<A, R> f) {
            addHooks.computeIfAbsent(key, k -> new ArrayList<>()).add(new AddHookFunction<>(null, f));
        }

        public void exec(Class<? extends K> cls, A arg, HashMap<Class<? extends R>, R> resultHolder) {
            List<AddHookFunction<A, R>> functions = addHooks.get(cls);
            if (functions != null) {
                functions.forEach(f -> {
                    R s = f.f.apply(arg);
                    resultHolder.put(f.key != null ? f.key : (Class<? extends R>) s.getClass(), s);
                });
            }
        }
    }

}
