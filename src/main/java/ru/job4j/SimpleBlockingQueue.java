package ru.job4j;

import net.jcip.annotations.*;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public final class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxElements;

    public SimpleBlockingQueue(int elements) {
        this.maxElements = elements;
    }

    public synchronized void offer(T value) throws InterruptedException {
            while (queue.size() == maxElements) {
                    wait();
                }
            queue.add(value);
            notify();
    }

    public synchronized T poll() throws InterruptedException {
            while (queue.isEmpty()) {
                    wait();
            }
            T result = queue.poll();
            notify();
            return result;
        }

}
