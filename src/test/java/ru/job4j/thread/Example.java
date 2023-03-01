package ru.job4j.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Example {

    public static void main(String[] args) {
        download("https://speed.hetzner.de/100MB.bin");
    }

    public static void download(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            InputStream is = con.getInputStream();
            CustomInputStream inputStream = new CustomInputStream(is);
            byte[] buffer = new byte[2024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                System.out.println("downloaded : " + len);
                //save file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class CustomInputStream extends InputStream {

        private static final int MAX_SPEED = 8 * 1024;
        private final static long SECOND = 1000;
        private long downloadedWhithinOneSecond = 0L;
        private long lastTime = System.currentTimeMillis();

        private InputStream inputStream;

        public CustomInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            lastTime = System.currentTimeMillis();
        }

        @Override
        public int read() throws IOException {
            long currentTime;
            if (downloadedWhithinOneSecond >= MAX_SPEED
                    && (((currentTime = System.currentTimeMillis()) - lastTime) < SECOND)) {
                try {
                    Thread.sleep(SECOND - (currentTime - lastTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downloadedWhithinOneSecond = 0;
                lastTime = System.currentTimeMillis();
            }
            int res = inputStream.read();
            if (res >= 0) {
                downloadedWhithinOneSecond++;
            }
            return res;
        }

        @Override
        public int available() throws IOException {
            return inputStream.available();
        }

        @Override
        public void close() throws IOException {
            inputStream.close();
        }
    }

}
