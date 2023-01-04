package io.shardmc.echo.core.data;

public class EchoClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] bytes) {
        String binary = name.replace("/", ".");
        return super.defineClass(binary, bytes, 0, bytes.length);
    }
}
