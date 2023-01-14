package woid2.test;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;
import woid2.Visitor;
import woid2.event.ClassEventer;
import woid2.util.EventedClassVisitor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        ClassReader or = new ClassReader(Main.read("woid2/test/DummyOriginal.class"));
        EventedClassVisitor sv = new EventedClassVisitor(new TraceClassVisitor(null)).onEnd(Main::onReady);

        or.accept(sv, 0);
    }

    private static void onReady(EventedClassVisitor cv) {
        try {
            for (int i = 0; i < 100; i++) {
                Main.acceptEventer(new TestVisitor(cv));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] read(String path) {
        try {
            return Files.readAllBytes(Path.of(Main.class.getClassLoader().getResource(path).toURI()));
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static void acceptEventer(Visitor visitor) throws IOException {
        ClassReader er = new ClassReader(Main.read("woid2/test/DummyEdit.class"));
        ClassEventer ce = new ClassEventer(visitor);
        er.accept(ce, 0);
    }
}
