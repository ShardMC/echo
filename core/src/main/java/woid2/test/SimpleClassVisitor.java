package woid2.test;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.util.function.Consumer;

public class SimpleClassVisitor extends ClassVisitor {

    private Consumer<SimpleClassVisitor> consumer;

    public SimpleClassVisitor(ClassVisitor parent) {
        super(Opcodes.ASM9, parent);
    }

    public void onEnd(Consumer<SimpleClassVisitor> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void visitEnd() {
        this.consumer.accept(this);
        super.visitEnd();
    }
}
