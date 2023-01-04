package io.shardmc.echo.util;

import java.io.*;

@SuppressWarnings("unused")
public class ConfigUtil {

    public static void write(File file, Object object) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        try (ObjectOutputStream stream = new ObjectOutputStream(fos)) {
            stream.writeObject(object);
        }
    }

    public static Object read(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectStream = new ObjectInputStream(inputStream)) {
            return objectStream.readObject();
        }
    }

    public static Object read(File file) throws IOException, ClassNotFoundException {
        return ConfigUtil.read(new FileInputStream(file));
    }

    public static Object read(String name) throws IOException, ClassNotFoundException {
        return ConfigUtil.read(FileUtil.getResource(name));
    }
}
