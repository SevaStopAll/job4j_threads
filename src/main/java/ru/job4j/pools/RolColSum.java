package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private final int rowSum;
        private final int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int a = 0; a < matrix.length; a++) {
            int colSum =  0;
            int rowSum = 0;
            for (int[] ints : matrix) {
                colSum += ints[a];
            }
            for (int x = 0; x < matrix.length; x++) {
                rowSum += matrix[a][x];
            }
            sums[a] = new Sums(rowSum, colSum);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sum = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sum[i] = getTask(matrix, i).get();
        }
        return sum;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int element) {
        return CompletableFuture.supplyAsync(() -> {
            int colSum =  0;
            int rowSum = 0;
            for (int[] datum : data) {
                colSum += datum[element];
            }
            for (int i = 0; i < data.length; i++) {
                rowSum += data[element][i];
            }
            return new Sums(rowSum, colSum);
        });
    }
    }
}

