package ru.job4j.pools;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int goal;

    public ParallelSearch(int[] array, int from, int to, int goal) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.goal = goal;
    }

    @Override
    protected Integer compute() {
        if (array.length < 10) {
            return LineSearch.search(array, from, to, goal);
        }
        if (from == to) {
            if (array[from] == goal) {
            return from;
            }
            return - 1;
        }
        int mid = (from + to) / 2;
        ParallelSearch leftSort = new ParallelSearch(array, from, mid, goal);
        ParallelSearch rightSort = new ParallelSearch(array,mid + 1, to, goal);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }

    public static int search(int[] array, int from, int to, int target) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch(array, from, to, target));
    }

}
