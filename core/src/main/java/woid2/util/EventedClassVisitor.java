package woid2.util;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.util.function.Consumer;

public class EventedClassVisitor extends ClassVisitor {

    private int version;
    private int access;
    private String name;
    private String signature;
    private String superName;
    private String[] interfaces;

    private Consumer<EventedClassVisitor> onEndConsumer;

    public EventedClassVisitor() {
        super(Opcodes.ASM9);
    }

    public EventedClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.version = version;
        this.access = access;
        this.name = name;
        this.signature = signature;
        this.superName = superName;
        this.interfaces = interfaces;

        super.visit(version, access, name, signature, superName, interfaces);
    }

    public int getVersion() {
        return this.version;
    }

    public int getAccess() {
        return this.access;
    }

    public String getName() {
        return this.name;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getSuperName() {
        return this.superName;
    }

    public String[] getInterfaces() {
        return this.interfaces;
    }

    public EventedClassVisitor onEnd(Consumer<EventedClassVisitor> consumer) {
        this.onEndConsumer = consumer;
        return this;
    }

    @Override
    public void visitEnd() {
        this.onEndConsumer.accept(this);
        super.visitEnd();
    }
}
