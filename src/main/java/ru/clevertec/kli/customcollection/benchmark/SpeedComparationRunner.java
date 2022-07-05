package ru.clevertec.kli.customcollection.benchmark;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class SpeedComparationRunner {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ArrayListBenchmark.class.getSimpleName())
            .include(LinkedListBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(1)
            .measurementIterations(1)
            .timeUnit(TimeUnit.MICROSECONDS)
            .mode(Mode.AverageTime)
            .build();
        new Runner(options).run();
    }
}
