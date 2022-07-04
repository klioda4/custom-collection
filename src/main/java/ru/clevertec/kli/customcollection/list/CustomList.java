package ru.clevertec.kli.customcollection.list;

public interface CustomList<T> extends Iterable<T> {

    void setMaxSize(int size);

    int getCurrentMaxSize();

    void add(T item);

    void addAll(CustomList<T> items);

    T set(int index, T newItem);

    T remove(int index);

    void clear();

    int find(T item);

    T get(int index);

    T[] toArray();

    int size();

    void trim();

    InsertableIterator<T> getIterator();
}
