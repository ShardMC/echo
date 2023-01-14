package woid3;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClassUtil {

    public static <T extends ClassVisitor> T read(String path, T visitor) {
        return ClassUtil.read(path, visitor, 0);
    }

    public static <T extends ClassVisitor> T read(String path, T visitor, int arguments) {
        ClassReader reader = new ClassReader(ClassUtil.readBytes(path));
        reader.accept(visitor, arguments);
        return visitor;
    }

    private static byte[] readBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(Main.class.getClassLoader().getResource(path).toURI()));
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
