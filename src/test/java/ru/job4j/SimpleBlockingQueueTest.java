package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

    @Test
    void whenOfferAndPoll() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                queue.offer(5);
                queue.offer(10);
                queue.offer(11);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            try {
                list.add(queue.poll());
                list.add(queue.poll());
                list.add(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Consumer");
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(list.size() == 3).isTrue();
    }

    @Test
    void whenOfferAndPollMoreThanSize() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                queue.offer(5);
                queue.offer(10);
                queue.offer(11);
                queue.offer(12);
                queue.offer(13);
                queue.offer(14);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            try {
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(list.size() == 6).isTrue();
    }

    @Test
    public void whenFetchAllThenGetIt() {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(6);
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        a -> {
                            try {
                                queue.offer(a);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        assertThat(buffer).isEqualTo(Arrays.asList(0, 1, 2, 3, 4));
    }
}