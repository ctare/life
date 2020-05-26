package ctare.utils;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by ctare on 2020/05/26.
 */
public class FilterIterator<E> implements Iterator<E>, Iterable<E> {
    private Iterator<E> itr;
    private Predicate<E> p;
    private E nextValue = null;

    public FilterIterator(Iterator<E> itr, Predicate<E> p) {
        this.itr = itr;
        this.p = p;
        this.nextValue = getNextValue();
    }

    private E getNextValue() {
        while (this.itr.hasNext()) {
            E value = this.next();
            if (p.test(value)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.nextValue != null;
    }

    @Override
    public E next() {
        E now = this.nextValue;
        this.nextValue = getNextValue();
        return now;
    }

    public static <T> FilterIterator<T> filter(Iterable<T> itr, Predicate<T> p) {
        return new FilterIterator<>(itr.iterator(), p);
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }
}
