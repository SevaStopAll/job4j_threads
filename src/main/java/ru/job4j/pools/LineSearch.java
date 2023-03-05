package ru.job4j.pools;

public class LineSearch {

    public static int search(int[] array, int from, int to, int goal) {
        for (int i = from; i < to; i++) {
            if (array[i] == goal) {
                return i;
            }
        }
        return -1;
    }
}
