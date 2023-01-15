package woid3.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import woid3.header.ClassHeader;

import java.util.function.Consumer;

public class SimpleClassVisitor extends ClassVisitor {

    private ClassHeader header;
    private Consumer<SimpleClassVisitor> consumer;

    public SimpleClassVisitor() {
        this(null);
    }

    public SimpleClassVisitor(ClassVisitor parent) {
        super(Opcodes.ASM9, parent);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.header = new ClassHeader(version, access, name, signature, superName, interfaces);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitEnd() {
        if (this.consumer != null) {
            this.consumer.accept(this);
        }

        super.visitEnd();
    }

    public ClassHeader getHeader() {
        return this.header;
    }

    public SimpleClassVisitor onEnd(Consumer<SimpleClassVisitor> consumer) {
        this.consumer = consumer;
        return this;
    }
}
