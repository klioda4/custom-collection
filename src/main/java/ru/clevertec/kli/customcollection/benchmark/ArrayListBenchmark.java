package ru.clevertec.kli.customcollection.benchmark;

import java.util.ArrayList;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ArrayListBenchmark extends ListBenchmark {

    public ArrayListBenchmark() {
        super(new ArrayList<>());
    }
}
