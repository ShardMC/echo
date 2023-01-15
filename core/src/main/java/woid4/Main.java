package woid4;

import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        ClassUtil.read(
                "woid2/test/DummyOriginal.class",
                new SimpleClassVisitor(new TraceClassVisitor(new PrintWriter(System.out))).onEnd(cv ->
                        ClassUtil.read("woid2/test/DummyEdit.class", new CopyClassVisitor(cv))
                )
        );
    }
}
