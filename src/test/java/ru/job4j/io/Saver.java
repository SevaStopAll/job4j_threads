package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class Saver {
    private final File file;

    public Saver(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
