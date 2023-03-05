package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int value;
        int current;
        do {
             current = get();
             value = current + 1;
        } while (!count.compareAndSet(current, value));
    }

    public int get() {
        return count.get();
    }
}