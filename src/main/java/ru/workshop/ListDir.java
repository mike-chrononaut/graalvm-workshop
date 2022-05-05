package ru.workshop;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class FileCount {
    final long size;
    final long count;

    public FileCount(final long size, final long count) {
        this.count = count;
        this.size = size;
    }

    public long getSize() {
        return this.size;
    }

    public long getCount() {
        return this.count;
    }
}

public class ListDir {

    final static Logger logger = LogManager.getLogger(ListDir.class);

    public static FileCount list(final String dirName) throws IOException {
        long[] size = {0};
        long[] count = {0};

        logger.debug("Processing : " + dirName);

        try (Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            paths.filter(Files::isRegularFile).forEach((Path p) -> {
                File f = p.toFile();

                logger.debug("Processing : " + f.getAbsolutePath());

                size[0] += f.length();
                count[0] += 1;
            });
        }

        return new FileCount(size[0], count[0]);
    }

    public static String humanReadableByteCountBin(final long bytes) {
        return FileUtils.byteCountToDisplaySize(bytes);
    }
}
