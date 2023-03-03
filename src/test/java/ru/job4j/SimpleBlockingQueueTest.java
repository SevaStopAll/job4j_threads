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
            queue.offer(5);
            queue.offer(10);
            queue.offer(11);
        }, "Producer");
        Thread consumer = new Thread(() -> {
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
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
            queue.offer(5);
            queue.offer(10);
            queue.offer(11);
            queue.offer(12);
            queue.offer(13);
            queue.offer(14);
        }, "Producer");
        Thread consumer = new Thread(() -> {
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
            list.add(queue.poll());
        }, "Consumer");
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(list.size() == 6).isTrue();
    }
}