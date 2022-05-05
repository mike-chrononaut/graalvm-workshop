package ru.workshop;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        String root = "D:\\projects\\workshops\\graalvm-workshop";

        if (args.length > 0) {
            root = args[0];
        }

        final FileCount count = ListDir.list(root);
        final String size = ListDir.humanReadableByteCountBin(count.getSize());

        System.out.println("Counting directory: " + root);
        System.out.println("Total: "
                + count.getCount()
                + " files, total size = "
                + size);
    }
}
