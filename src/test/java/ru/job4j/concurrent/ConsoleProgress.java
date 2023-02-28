package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        var process = new char[]{'-', '\\', '|', '/' };
        while (!Thread.currentThread().isInterrupted()) {
            for (char ch : process) {
                try {
                    Thread.sleep(500);
                    System.out.print("\r load: " + ch);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args)  {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
            progress.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
