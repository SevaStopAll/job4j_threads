package ru.job4j;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    void whenAdd() {
        CASCount test = new CASCount();
        test.increment();
        test.increment();
        test.increment();
        assertThat(test.get()).isEqualTo(3);
    }

    @Test
    void whenAddManyTimes()  {
        CASCount test = new CASCount();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(() -> {
            for (int i = 0; i < 100; i++) {
                test.increment();
            }
        });
        pool.submit(() -> {
            for (int i = 0; i < 100; i++) {
                test.increment();
            }
        });
        pool.submit(() -> {
            for (int i = 0; i < 100; i++) {
                test.increment();
            }
        });
        pool.submit(() -> {
            for (int i = 0; i < 100; i++) {
                test.increment();
            }
        });
        pool.submit(() -> {
            for (int i = 0; i < 100; i++) {
                test.increment();
            }
        });
        pool.submit(() -> {
            for (int i = 0; i < 100; i++) {
                test.increment();
            }
        });
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertThat(test.get()).isEqualTo(600);
    }
}