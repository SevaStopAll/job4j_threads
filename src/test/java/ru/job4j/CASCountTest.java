package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {
    CASCount test = new CASCount();

    @Test
    void whenAdd() {
        test.increment();
        test.increment();
        test.increment();
        assertThat(test.get()).isEqualTo(3);
    }

}