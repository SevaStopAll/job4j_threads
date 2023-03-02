package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) != 1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContent() throws IOException {
        Predicate<Character> filter = ch -> (true);
        return content(filter);
    }

    public String getContentWithoutUnicode() throws IOException {
        Predicate<Character> filter = ch -> (ch < 0x80);
        return content(filter);
    }
}
