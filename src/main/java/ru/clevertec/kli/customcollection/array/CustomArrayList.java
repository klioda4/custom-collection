package ru.clevertec.kli.customcollection.array;


import java.util.Iterator;

public class CustomArrayList<T> implements CustomList<T> {

    private static final int INITIAL_LENGTH = 4;
    private static final int ARRAY_EXTENSION_MULTIPLIER = 2;

    private T[] inner;
    private int length;
    private int maxSize;

    public CustomArrayList() {
        inner = (T[]) new Object[INITIAL_LENGTH];
    }

    @Override
    public void setMaxSize(int newMaxSize) {
        if (newMaxSize < 0) {
            throw new IllegalArgumentException();
        }
        if (newMaxSize < inner.length && newMaxSize != 0) {
            shrinkArray(newMaxSize);
        }
        maxSize = newMaxSize;
    }

    @Override
    public int getCurrentMaxSize() {
        return maxSize;
    }

    @Override
    public void add(T item) {
        throwIfMaxSizeReached();
        if (inner.length == length) {
            extendArray();
        }
        inner[length++] = item;
    }

    @Override
    public void addAll(CustomList<T> items) {
        throwIfMaxSizeReached();
        int newSize = length + items.size();
        if (inner.length < newSize) {
            extendArray(newSize);
        }

        int destIndex = length;
        int sourceLength = items.size();
        for (int i = 0; i < sourceLength; i++) {
            inner[destIndex++] = items.get(i);
        }
        length += sourceLength;
    }

    @Override
    public T set(int index, T newItem) {
        checkIndex(index);
        T oldItem = inner[index];
        inner[index] = newItem;
        return oldItem;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldItem = inner[index];
        copyArray(inner, index + 1, inner, index, length - index - 1);
        inner[length - 1] = null;
        length--;
        return oldItem;
    }

    @Override
    public void clear() {
        clearArray(inner, 0, length - 1);
        length = 0;
    }

    @Override
    public int find(T item) {
        for (int i = 0; i < length; i++) {
            if (inner[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        return inner[index];
    }

    @Override
    public T[] toArray() {
        Object[] array = new Object[length];
        copyArray(inner, 0, array, 0, length);
        return (T[]) array;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void trim() {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                iterator.remove();
            }
        }
    }

    @Override
    public InsertableIterator<T> getIterator() {
        return new CustomArrayListIterator();
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomArrayListIterator();
    }

    private void insert(int index, T newItem) {
        throwIfMaxSizeReached();
        checkIndex(index);
        if (length == inner.length) {
            extendArray();
        }
        copyArray(inner, index, inner, index + 1, length - index);
        inner[index] = newItem;
        length++;
    }

    private void throwIfMaxSizeReached() throws IllegalStateException {
        if (maxSize != 0 && length == maxSize) {
            throw new IllegalStateException("Max size reached");
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(
                "Index is " + index + ", but list size is " + length);
        }
    }

    private void shrinkArray(int newSize) {
        if (newSize <= 0) {
            throw new IllegalArgumentException();
        }
        clearArray(inner, newSize, length - 1);
        length = newSize;
    }

    private void extendArray() {
        extendArray(length + 1);
    }

    private void extendArray(int minSize) {
        throwIfMaxSizeReached();
        int newSize;
        if (maxSize != 0) {
            newSize = maxSize;
        } else {
            newSize = length;
            while (newSize < minSize) {
                newSize *= ARRAY_EXTENSION_MULTIPLIER;
            }
        }
        inner = (T[]) extendArray(inner, newSize);
    }

    private static Object[] extendArray(Object[] array, int newLength) {
        Object[] newArray = new Object[newLength];
        copyArray(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private static void copyArray(Object[] source, int sourcePosition, Object[] destination,
        int destinationPosition, int length) {

        if (source != destination || sourcePosition > destinationPosition) {
            int destIndex = destinationPosition;
            for (int i = sourcePosition; i < sourcePosition + length; i++) {
                destination[destIndex++] = source[i];
            }
        } else {
            int destIndex = destinationPosition + length - 1;
            for (int i = sourcePosition + length - 1; i >= sourcePosition; i--) {
                destination[destIndex--] = source[i];
            }
        }
    }

    private static void clearArray(Object[] array, int fromIndex, int toIndex) {
        for (int i = fromIndex; i <= toIndex; i++) {
            array[i] = null;
        }
    }

    private class CustomArrayListIterator implements InsertableIterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return size() > index;
        }

        @Override
        public T next() {
            return inner[index++];
        }

        @Override
        public void remove() {
            CustomArrayList.this.remove(--index);
        }

        @Override
        public void addBefore(T item) {
            insert(index - 1, item);
            index++;
        }

        @Override
        public void addAfter(T item) {
            if (index == length) {
                add(item);
            } else {
                insert(index, item);
            }
            index++;
        }
    }
}
