package woid6.event;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import woid5.MethodHeader;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class EventfulClassVisitor extends ClassVisitor {

    private Consumer<EventfulClassVisitor> endCallback;

    public EventfulClassVisitor() {
        this(null);
    }

    public EventfulClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public void visitEnd() {
        if (this.endCallback != null) {
            this.endCallback.accept(this);
        }

        super.visitEnd();
    }

    public EventfulClassVisitor onEnd(Consumer<EventfulClassVisitor> callback) {
        this.endCallback = callback;
        return this;
    }
}
