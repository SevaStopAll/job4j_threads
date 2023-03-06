package ru.job4j.pools;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ParallelSearchTest {

    @Test
    void whenSearchString() {
        String[] array = {"Alla", "Lena", "Clair", "Dina", "Dickson", "Tess", "Tuta", "Olga", "Thomas", "Helga", "Lola", "Rex", "Tintin"};
        int expected = 0;
        int result = ParallelSearch.search(array, "Alla");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenSearchChar() {
        Character[] array = {'a', 'A', 'L', 'l', 'R', 'E', 'R', 'Q', 'Y', 'Q', 'u'};
        int expected = 0;
        int result = ParallelSearch.search(array, 'a');
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenIntArraySmall() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 10, 12};
        int expected = 8;
        int result = ParallelSearch.search(array, 12);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenIntArrayBig() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 10, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24};
        int expected = 8;
        int result = ParallelSearch.search(array, 12);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenElementNotFound() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 10, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24};
        int expected = -1;
        int result = ParallelSearch.search(array, 55);
        assertThat(result).isEqualTo(expected);
    }
}