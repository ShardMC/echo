package io.shardmc.echo.util;

import java.io.*;
import java.nio.file.Paths;

public class FileUtil {

    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");

    public static File joinPath(String first, String... path) {
        return Paths.get(first, path).toFile();
    }

    public static File joinRelativePath(String path) {
        return FileUtil.joinPath(WORKING_DIRECTORY, path.split("/"));
    }

    public static InputStream getResource(String path) {
        return FileUtil.class.getClassLoader().getResourceAsStream(path);
    }

    public static byte[] readBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = stream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }

        return result.toByteArray();
    }
}
