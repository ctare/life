package ctare.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by ctare on 2020/05/26.
 */
public class MappedArray<K, V> implements Iterable<MappedArray.KeyValue<K, V>> {
    private final SortedArrayList<KeyValue<K, V>> array;
    private final HashMap<K, V> map = new HashMap<>();
    public static class KeyValue<Key, Value> {
        private Key key;
        private Value value;

        public KeyValue(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", key.toString(), value.toString());
        }
    }
    public MappedArray(Comparator<KeyValue<K, V>> comparator) {
        array = new SortedArrayList<>(comparator);
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        return array.iterator();
    }

    @Override
    public void forEach(Consumer<? super KeyValue<K, V>> action) {
        array.forEach(action);
    }

    public void put(K key, V value) {
        assert map.get(key) == null;
        map.put(key, value);
        array.add(new KeyValue<>(key, value));
    }

    public V get(K key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        return this.array.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
