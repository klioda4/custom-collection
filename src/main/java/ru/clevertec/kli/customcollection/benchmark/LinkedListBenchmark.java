package ru.clevertec.kli.customcollection.benchmark;

import java.util.LinkedList;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class LinkedListBenchmark extends ListBenchmark {

    public LinkedListBenchmark() {
        super(new LinkedList<>());
    }
}
