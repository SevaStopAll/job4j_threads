package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenSimpleArray() {
        int[][] array = {
                {5, 6, 7},
                {4, 7, 9},
                {6, 5, 3}
        };
        Sums[] expected = new Sums[]{new Sums(18, 15), new Sums(20, 18), new Sums(14, 19)};
        Sums[] result = RolColSum.sum(array);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenSimpleArrayAsync() throws ExecutionException, InterruptedException {
        int[][] array = {
                {5, 6, 7},
                {4, 7, 9},
                {6, 5, 3}
        };
        Sums[] expected = new Sums[]{new Sums(18, 15), new Sums(20, 18), new Sums(14, 19)};
        Sums[] result = RolColSum.asyncSum(array);
        assertThat(result).isEqualTo(expected);
    }
}