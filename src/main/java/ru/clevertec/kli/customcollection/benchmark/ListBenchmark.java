package ru.clevertec.kli.customcollection.benchmark;

import java.util.LinkedList;
import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public abstract class ListBenchmark {

    private static final int LIST_SIZE = 100_000;
    private static final int MIDDLE_INDEX = LIST_SIZE / 2;
    private List<Object> list;

    private ListBenchmark() {
    }

    public ListBenchmark(List<Object> list) {
        this.list = list;
    }

    @Setup(Level.Invocation)
    public void setup() {
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(new Object());
        }
    }

    @TearDown(Level.Invocation)
    public void tearDown() {
        list.clear();
    }

    @Benchmark
    public void addFirst() {
        list.add(0, new Object());
    }

    @Benchmark
    public void addMiddle() {
        list.add(MIDDLE_INDEX, new Object());
    }

    @Benchmark
    public void addLast() {
        list.add(new Object());
    }

    @Benchmark
    public void removeFirst() {
        list.remove(0);
    }

    @Benchmark
    public void removeMiddle() {
        list.remove(MIDDLE_INDEX);
    }

    @Benchmark
    public void removeLast() {
        list.remove(
            list.size() - 1);
    }

    @Benchmark
    public Object getFirst() {
        return list.get(0);
    }

    @Benchmark
    public Object getMiddle() {
        return list.get(MIDDLE_INDEX);
    }

    @Benchmark
    public Object getLast() {
        return list.get(
            list.size() - 1);
    }

    @Benchmark
    public boolean contains() {
        return list.contains(
            list.get(
                list.size() - 1));
    }
}
