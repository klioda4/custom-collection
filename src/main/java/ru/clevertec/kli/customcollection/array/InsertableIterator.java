package ru.clevertec.kli.customcollection.array;

import java.util.Iterator;

public interface InsertableIterator<T> extends Iterator<T> {

    void addBefore(T item);

    void addAfter(T item);
}
