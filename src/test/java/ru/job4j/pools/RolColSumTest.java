package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenSimpleArray() {
        int[][] array = {
                {5, 6, 7},
                {4, 7, 9},
                {6, 5, 3}
        };
        RolColSum.Sums[] sum = RolColSum.Sums.sum(array);
        assertThat(sum[0].getColSum()).isEqualTo(15);
        assertThat(sum[0].getRowSum()).isEqualTo(18);
        assertThat(sum[1].getColSum()).isEqualTo(18);
        assertThat(sum[1].getRowSum()).isEqualTo(20);
        assertThat(sum[2].getColSum()).isEqualTo(19);
        assertThat(sum[2].getRowSum()).isEqualTo(14);
    }

    @Test
    void whenSimpleArrayAsync() throws ExecutionException, InterruptedException {
        int[][] array = {
                {5, 6, 7},
                {4, 7, 9},
                {6, 5, 3}
        };
        RolColSum.Sums[] sum = RolColSum.Sums.asyncSum(array);
        assertThat(sum[0].getColSum()).isEqualTo(15);
        assertThat(sum[0].getRowSum()).isEqualTo(18);
        assertThat(sum[1].getColSum()).isEqualTo(18);
        assertThat(sum[1].getRowSum()).isEqualTo(20);
        assertThat(sum[2].getColSum()).isEqualTo(19);
        assertThat(sum[2].getRowSum()).isEqualTo(14);
    }

}