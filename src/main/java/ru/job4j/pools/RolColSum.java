package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int a = 0; a < matrix.length; a++) {
            int colSum =  0;
            int rowSum = 0;
            for (int i = 0; i < matrix.length; i++) {
                colSum += matrix[i][a];
                rowSum += matrix[a][i];
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
            for (int i = 0; i < data.length; i++) {
                colSum += data[i][element];
                rowSum += data[element][i];
            }
            return new Sums(rowSum, colSum);
        });
    }

}



