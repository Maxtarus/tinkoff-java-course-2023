package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final Collection<T> collection;
    private int currentIndex;

    public BackwardIterator(Collection<T> collection) {
        this.collection = collection;
        this.currentIndex = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        @SuppressWarnings("unchecked")
        T element = (T) collection.toArray()[currentIndex];
        currentIndex--;

        return element;
    }
}

