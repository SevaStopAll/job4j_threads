package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}