package ctare.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ctare on 2020/05/21.
 */
public class SortedArrayList<E> extends ArrayList<E> {
    Comparator<E> comparator;

    public SortedArrayList(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public SortedArrayList(int initialCapacity, Comparator<E> comparator) {
        super(initialCapacity);
        this.comparator = comparator;

    }

    public SortedArrayList(Collection<? extends E> c, Comparator<E> comparator) {
        super(c);
        this.comparator = comparator;
    }

    @Override
    public boolean add(E e) {
        int index = Collections.binarySearch(this, e, this.comparator);
        if (index < 0) index = ~index;
        super.add(index, e);
        return true;
    }


}
