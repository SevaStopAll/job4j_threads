package ru.job4j.pools;

public class Sums {
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

}