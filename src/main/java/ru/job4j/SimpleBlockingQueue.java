package ru.job4j;

import net.jcip.annotations.*;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public final class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxElements;
    private int currentElements;

    public SimpleBlockingQueue(int elements) {
        this.maxElements = elements;
        currentElements = 0;
    }

    public synchronized void offer(T value) {
            while (currentElements == maxElements) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(value);
            currentElements++;
            notify();
    }

    public synchronized T poll() {
            while (queue.peek() == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            currentElements--;
            T result = queue.poll();
            notify();
            return result;
        }

}
