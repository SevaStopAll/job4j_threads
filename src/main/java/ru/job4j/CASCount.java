package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int value;
        do {
             value = get() + 1;
        } while (!count.compareAndSet(get(), value));
    }

    public int get() {
        return count.get();
    }
}