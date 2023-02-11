package woid8.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StackMethodVisitor extends MethodVisitor {

    private final List<Consumer<MethodVisitor>> stack = new ArrayList<>();

    public StackMethodVisitor() {
        this(null);
    }

    public StackMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM9, mv);
    }

    public void delay(Consumer<MethodVisitor> consumer) {
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
