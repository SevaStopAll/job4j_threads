package ru.job4j.pools;

public class LineSearch {

    public static <T> int search(T[] array, int from, int to, T goal) {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (array[i] == goal) {
                result = i;
            }
        }
        return result;
    }
}
