package woid8;

import org.objectweb.asm.util.TraceClassVisitor;
import woid8.core.StackClassVisitor;

import java.io.PrintWriter;

public class Example {

    public static void main(String[] args) {
        StackClassVisitor cv = new StackClassVisitor(new TraceClassVisitor(new PrintWriter(System.out)));
        cv.delay(visitor -> System.out.println(1));
    }
}
