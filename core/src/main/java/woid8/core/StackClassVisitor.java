package woid8.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class StackClassVisitor extends ClassVisitor {

    private final Map<String, MethodVisitor> methodMap = new HashMap<>();
    private final List<Consumer<ClassVisitor>> stack = new ArrayList<>();

    public StackClassVisitor() {
        this(null);
    }

    public StackClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    public MethodVisitor delayMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = new StackMethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions));
        this.delay(visitor -> visitor.visitMethod(access, name, descriptor, signature, exceptions));

        return mv;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return this.methodMap.getOrDefault(descriptor, super.visitMethod(access, name, descriptor, signature, exceptions));
    }

    public void delay(Consumer<ClassVisitor> consumer) {
        this.stack.add(consumer);
    }

    public void apply() {
        for (int i = 0; i < this.stack.size(); i++) {
            this.stack.remove(i).accept(this);
        }
    }

    @Override
    public void visitEnd() {
        this.apply();

        super.visitEnd();
    }
}
