package io.shardmc.echo.core.data;

public class EchoClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] bytes) {
        String binary = name.replace("/", ".");

        try {
            return super.defineClass(binary, bytes, 0, bytes.length);
        } catch (ClassFormatError error) {
            System.out.println("Uh-oh! Seems like something is not right!");
        }

        return null;
    }
}
