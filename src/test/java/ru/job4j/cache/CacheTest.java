package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CacheTest {
    Cache testCache = new Cache();

    @Test
    void whenAdd(){
        assertThat(testCache.add(new Base(1, 1))).isTrue();
        assertThat(testCache.add(new Base(2, 1))).isTrue();
        assertThat(testCache.add(new Base(3, 1))).isTrue();
        assertThat(testCache.add(new Base(1, 1))).isFalse();
    }

    @Test
    void whenDelete(){
        Base model = new Base(1, 1);
        assertThat(testCache.add(model)).isTrue();
        assertThat(testCache.add(model)).isFalse();
        testCache.delete(model);
        assertThat(testCache.add(model)).isTrue();
    }

    @Test
    void whenUpdate(){
        Base model = new Base(1, 1);
        testCache.add(model);
        assertThat(testCache.update(new Base(1, 1))).isTrue();
    }

    @Test
    void whenUpdateOldVersion(){
        Base model = new Base(1, 2);
        testCache.add(model);
        assertThatThrownBy(() -> testCache.update(new Base(1, 1)))
                .isInstanceOf(OptimisticException.class);
    }

}