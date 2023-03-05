package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelSearchTest {

    @Test
    void whenSearchString() {}

    @Test
    void whenSearchInt() {}

    @Test
    void whenArraySmall() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 10, 12, 13, 15, 176};
        System.out.println(ParallelSearch.search(array, 0, array.length-1, 12));
    }

    @Test
    void whenArrayBig() {}

    @Test
    void whenElementNotFound() {}

}