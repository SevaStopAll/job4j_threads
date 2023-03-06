package ru.job4j.pools;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T goal;

    public ParallelSearch(T[] array, int from, int to, T goal) {
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
            if (array[from].equals(goal)) {
            return from;
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ParallelSearch<T> leftSort = new ParallelSearch<>(array, from, mid, goal);
        ParallelSearch<T> rightSort = new ParallelSearch<>(array, mid + 1, to, goal);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }

    public static <T>  int search(T[] array, int from, int to, T target) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(array, from, to, target));
    }

}
